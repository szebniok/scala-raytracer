import scala.annotation.tailrec

class Vec3(val x: Double, val y: Double, val z: Double) {
  def +(other: Vec3) = new Vec3(x + other.x, y + other.y, z + other.z)
  def -(other: Vec3) = new Vec3(x - other.x, y - other.y, z - other.z)
  def unary_- = new Vec3(-x, -y, -z)
  def *=(t: Double) = new Vec3(x * t, y * t, z * t)
  def /=(t: Double) = new Vec3(x / t, y / t, z / t)
  def lengthSquared(): Double = (x * x) + (y * y) + (z * z)
  def length(): Double = Math.sqrt(lengthSquared())
  def castToColor(): Color = {
    val scale = 1D / Utils.samples_per_pixel
    val r = math.sqrt(x * scale)
    val g = math.sqrt(y * scale)
    val b = math.sqrt(z * scale)
    Color(
      (256 * Utils.clamp(r, 0.0, 0.999)).toInt,
      (256 * Utils.clamp(g, 0.0, 0.999)).toInt,
      (256 * Utils.clamp(b, 0.0, 0.999)).toInt
    )
  }

}

object Vec3 {
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
    new Vec3(
      u.y * v.z - u.z * v.y,
      u.z * v.x - u.x * v.z,
      u.x * v.y - u.y * v.x
    )
  def unitVector(u: Vec3): Vec3 = u /= u.length()

  def random(): Vec3 =
    new Vec3(
      Utils.random_double(),
      Utils.random_double(),
      Utils.random_double()
    )

  def random(min: Double, max: Double): Vec3 =
    new Vec3(
      Utils.random_double(min, max),
      Utils.random_double(min, max),
      Utils.random_double(min, max)
    )

  def randomUnitVector(): Vec3 = {
    val a = Utils.random_double(0, 2 * math.Pi)
    val z = Utils.random_double(-1, 1)
    val r = math.sqrt(1 - z*z)
    new Vec3(r * math.cos(a), r * math.sin(a), z)
  }

  def reflect(v: Vec3, n: Vec3): Vec3 =
    v - Vec3.*(2 * dot(v, n), n)

  @tailrec
  def randomInUnitSphere(): Vec3 = {
    val p = Vec3.random(-1, 1)
    if (p.lengthSquared() >= 1) randomInUnitSphere()
    else p
  }

  def randomPointInHemisphere(normal: Vec3): Vec3 = {
    val point_in_sphere = Vec3.randomInUnitSphere()
    if(Vec3.dot(point_in_sphere, normal) > 0.0){
      point_in_sphere
    }
    else{
      -point_in_sphere
    }
  }

  def refract(uv: Vec3, n: Vec3, etaiOverEtat: Double): Vec3 = {
    val cosTheta = dot(-uv, n)
    val rOutParallel = Vec3.*(etaiOverEtat, (uv + Vec3.*(cosTheta , n)))
    val rOutPerp = Vec3.*(n, -Math.sqrt(1.0 - rOutParallel.lengthSquared()))
    rOutParallel + rOutPerp
  }

  @tailrec
  def randomInUnitDisk(): Vec3 ={
    val p = Vec3(Utils.random_double(-1.0, 1.0), Utils.random_double(-1.0, 1.0), 0)
    if(p.lengthSquared() >= 1) randomInUnitDisk()
    else p
  }

  type point3 = Vec3

}
