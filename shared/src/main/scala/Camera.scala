import Vec3.point3

class Camera(lookfrom: point3, lookat: point3, vup: Vec3, vfov: Double, aspectRatio: Double) {

  val theta: Double = Math.toRadians(vfov)
  val h: Double = Math.tan(theta/2)
  val viewportHeight: Double = 2.0 * h
  val viewportWidth: Double = aspectRatio * viewportHeight

  val w: Vec3 = Vec3.unitVector(lookfrom - lookat)
  val u: Vec3 = Vec3.unitVector(Vec3.cross(vup, w))
  val v: Vec3 = Vec3.cross(w, u)

  private val origin: point3 = lookfrom
  private var horizontal: Vec3 = Vec3.*(viewportWidth, u)
  private var vertical: Vec3 = Vec3.*(viewportHeight, v)
  private val lowerLeftCorner: point3 = origin - (horizontal /= 2) - (vertical /= 2) - w

  def get_ray(u: Double, v: Double): Ray =
    Ray(origin, lowerLeftCorner + (horizontal *= u) + (vertical *= v) - origin)
}