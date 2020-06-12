import Vec3.point3

class Camera(lookfrom: point3, lookat: point3, vup: Vec3, vfov: Double, aspectRatio: Double, aperture: Double, focusDist: Double) {

  val theta: Double = Math.toRadians(vfov)
  val h: Double = Math.tan(theta/2)
  val viewportHeight: Double = 2.0 * h
  val viewportWidth: Double = aspectRatio * viewportHeight

  private val w: Vec3 = Vec3.unitVector(lookfrom - lookat)
  private val u: Vec3 = Vec3.unitVector(Vec3.cross(vup, w))
  private val v: Vec3 = Vec3.cross(w, u)

  private val origin: point3 = lookfrom
  private val horizontal: Vec3 = Vec3.*(focusDist, Vec3.*(viewportWidth, u))
  private val vertical: Vec3 = Vec3.*(focusDist, Vec3.*(viewportHeight, v))
  private val lowerLeftCorner: point3 = origin - (horizontal /= 2) - (vertical /= 2) - Vec3.*(w, focusDist)

  private val lensRadius = aperture / 2

  def get_ray(s: Double, t: Double): Ray = {
    val rd = Vec3.*(lensRadius, Vec3.randomInUnitDisk())
    val offset = Vec3.*(u, rd.x) + Vec3.*(v, rd.y)
    Ray(origin + offset, lowerLeftCorner + (horizontal *= s) + (vertical *= t) - origin - offset)
  }
}

object Camera {
  def exampleCamera(): Camera = {
    val lookfrom = new point3(13, 2, 3)
    val lookat = new point3(0, 0, 0)
    val vup = Vec3(0, 1, 0)
    val distToFocus = 10.0
    val aperture = 0.1

    new Camera(lookfrom, lookat, vup, 20, 16.0 / 9.0, aperture, distToFocus)
  }
}