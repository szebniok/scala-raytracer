import Vec3.point3

class Camera {

  val aspect_ratio: Double = 16.0 / 9.0
  val viewport_height: Double = 2.0
  val viewport_width: Double = aspect_ratio * viewport_height
  val focal_length: Double = 1.0

  private val origin: point3 = new point3(0, 0, 0)
  private var horizontal: Vec3 = Vec3(viewport_width, 0.0, 0.0)
  private var vertical: Vec3 = Vec3(0.0, viewport_height, 0.0)
  private val lower_left_corner: point3 = origin - (horizontal /= 2) - (vertical /= 2) - Vec3(0, 0, focal_length)

  def get_ray(u: Double, v: Double): Ray =
    Ray(origin, lower_left_corner + (horizontal *= u) + (vertical *= v) - origin)
}