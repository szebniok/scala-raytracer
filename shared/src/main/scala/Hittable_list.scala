import java.util

import Vec3.point3

class Hittable_list extends Hittable {

  var objects: util.LinkedList[Hittable] = new util.LinkedList[Hittable]()

  def this(o: Hittable) = {
    this()
    add(o)
  }

  def clear(): Unit = {
    objects.clear()
  }

  def add(o: Hittable): Unit = {
    objects.add(o)
  }

  def hit(r: Ray, t_min: Double, t_max: Double, rec: Hit_record): Boolean = {
    val temp_rec: Hit_record = Hit_record()
    var hit_anything: Boolean = false
    var closest_so_far = t_max

    objects.forEach(o =>
      if(o.hit(r, t_min, closest_so_far, temp_rec)){
        hit_anything = true
        closest_so_far = temp_rec.t
        rec.set_args(temp_rec)
      })

    hit_anything
  }
}
object Hittable_list{
  def randomScene(): Hittable_list = {
    val groundMaterial = Lambertian(Vec3(0.5, 0.5, 0.5))
    val world = new Hittable_list()
    world.add(new Sphere(Vec3(0, -1000, 0), 1000, groundMaterial))

    for(x <- (-11) to 11) {
      for(y <- (-11) to 11) {
        val chooseMaterial = Utils.random_double()
        val center = new point3(x + 0.9*Utils.random_double(), 0.2, y + 0.9*Utils.random_double())

        if(chooseMaterial < 0.8) {
          val albedo = Vec3.*(Vec3.random(0.0, 1.0), Vec3.random(0.0, 1.0))
          val sphereMaterial = Lambertian(albedo)
          world.add(new Sphere(center, 0.2, sphereMaterial))
        }
        else if(chooseMaterial < 0.95) {
          val albedo = Vec3.random(0.5, 1.0)
          val fuzz = Utils.random_double()
          val sphereMaterial = Metal(albedo, fuzz)
          world.add(new Sphere(center, 0.2, sphereMaterial))
        }
        else {
          val sphereMaterial = Dielectric(1.5)
          world.add(new Sphere(center, 0.2, sphereMaterial))
        }
      }
    }

    val material1 = Dielectric(1.5)
    world.add(new Sphere(new point3(0, 1, 0), 1.0, material1))

    val material2 = Lambertian(Vec3(0.4, 0.2, 0.1))
    world.add(new Sphere(new point3(-4, 1, 0), 1.0, material2))

    val material3 = Metal(Vec3(0.7, 0.6, 0.5), 0.0)
    world.add(new Sphere(new point3(4, 1, 0), 1.0, material3))

    world
  }

}