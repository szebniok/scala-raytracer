class Vec3(val x: Double, val y: Double, val z: Double) {
  def +(other: Vec3) = new Vec3(x + other.x, y + other.y, z + other.z)
  def -(other: Vec3) = new Vec3(x - other.x, y - other.y, z - other.z)
  def unary_- = new Vec3(-x, -y, -z)
  def *=(t: Double) = new Vec3(x * t, y * t, z * t)
  def /=(t: Double) = new Vec3(x / t, y / t, z / t)
  def lengthSquared(): Double = (x * x) + (y * y) + (z * z)
  def length(): Double = Math.sqrt(lengthSquared())
  def castToColor(): Color = Color((x*255.999).toInt, (y*255.999).toInt, (z*255.999).toInt)
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
  def unitVector(u: Vec3): Vec3 = Vec3(Vec3./(u, u.length()))
  type point3 = Vec3
}
