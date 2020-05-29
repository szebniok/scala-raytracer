class Vec3(val x: Double, val y: Double, val z: Double) {
  def +(other: Vec3) = new Vec3(x + other.x, y + other.y, z + other.z)
  def -(other: Vec3) = new Vec3(x - other.x, y - other.y, z - other.z)
  def unary_- = new Vec3(-x, -y, -z)
  def *=(t: Double) = new Vec3(x * t, y * t, z * t)
  def /=(t: Double) = new Vec3(x / t, y / t, z / t)
  def lengthSquared(): Double = (x * x) + (y * y) + (z * z)
  def length(): Double = Math.sqrt(lengthSquared())
  def castToColor(): Color = {
    val r = Math.sqrt(1.0 / Utils.samples * x)
    val g = Math.sqrt(1.0 / Utils.samples * y)
    val b = Math.sqrt(1.0 / Utils.samples * z)
    Color(
      (256 * Utils.clamp(r, 0.0, 0.999)).toInt,
      (256 * Utils.clamp(g, 0.0, 0.999)).toInt,
      (256 * Utils.clamp(b, 0.0, 0.999)).toInt)
  }

}

object Vec3{
  def apply(x: Double, y: Double, z: Double) = new Vec3(x, y, z)
  def apply() = new Vec3(0, 0, 0)
  def apply(prototype: Vec3) = new Vec3(prototype.x, prototype.y, prototype.z)
  def +(u: Vec3, v: Vec3): Vec3 = new Vec3(u.x + v.x, u.y + v.y, u.z + v.z)
  def -(u: Vec3, v: Vec3): Vec3 = new Vec3(u.x - v.x, u.y - v.y, u.z - v.z)
  def *(u: Vec3, v: Vec3): Vec3 = new Vec3(u.x * v.x, u.y * v.y, u.z * v.z)
  def /(u: Vec3, v: Vec3): Vec3 = new Vec3(u.x / v.x, u.y / v.y, u.z / v.z)
  def *(u: Vec3, t: Double): Vec3 = u *= t
  def *(t: Double, u: Vec3): Vec3 = u *= t
  def /(u: Vec3, t: Double): Vec3 = new Vec3(u.x / t, u.y / t, u.z / t)
  def dot(u: Vec3, v: Vec3): Double = (u.x * v.x) + (u.y * v.y) + (u.z * v.z)
  def cross(u: Vec3, v: Vec3): Vec3 =
    new Vec3(u.y * v.z - u.z * v.y, u.z * v.x - u.x * v.z, u.x * v.y - u.y * v.x)
  def unitVector(u: Vec3): Vec3 = u /= u.length()
  def random() = new Vec3(Utils.random_double(), Utils.random_double(), Utils.random_double())
  def random(min: Double, max: Double) = new Vec3(Utils.random_double(min, max), Utils.random_double(min, max), Utils.random_double(min, max))
  def random_point_in_sphere(): Vec3 = {
    var vec = Vec3.random(-1.0, 1.0)
    while (vec.lengthSquared() >= 1){
      vec = Vec3.random(-1.0, 1.0)
    }
    vec
  }
  def random_unit_vector(): Vec3 = {
    val a = Utils.random_double(0, 2 * Math.PI)
    val z = Utils.random_double(-1.0, 1.0)
    val r = Math.sqrt(1 - z * z)
    Vec3(r * Math.cos(a), r * Math.sin(a), z)
  }
  def random_point_in_hemisphere(normal: Vec3): Vec3 = {
    val point_in_sphere = Vec3.random_point_in_sphere()
    if(Vec3.dot(point_in_sphere, normal) > 0.0){
      point_in_sphere
    }
    else{
      -point_in_sphere
    }
  }
  type point3 = Vec3
}
