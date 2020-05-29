import Vec3.point3

class Hit_record(
    var p: point3,
    var normal: Vec3,
    var t: Double,
    var front_face: Boolean,
    var material: Material){

  def set_face_normal(r: Ray, outward_normal: Vec3): Unit = {
    front_face = Vec3.dot(r.direction, outward_normal) < 0
    normal = if (front_face) outward_normal
    else -outward_normal
  }

  def set_args(r: Hit_record): Unit = {
    this.p = r.p
    this.normal = r.normal
    this.t = r.t
    this.front_face = r.front_face
    this.material = r.material
  }
}

object Hit_record {
  def apply(p: point3, normal: Vec3, t: Double, front_face: Boolean, lambertian: Lambertian) =
    new Hit_record(p, normal, t, front_face, lambertian)
  def apply() =
    new Hit_record(new point3(0, 0, 0), new point3(0, 0, 0), 0, false, Lambertian(Vec3(1, 1, 1)))
}

trait Hittable{
  def hit(r: Ray, t_min: Double, t_max: Double, rec: Hit_record): Boolean
}

