import java.util

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