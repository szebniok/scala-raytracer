object Utils {
  def generateSampleGradient(): Image = {
    val width = 256
    val height = 256

    val pixels = (height - 1 to 0 by -1)
      .map(j => (0 until width).map(i => Vec3(i.toDouble/width, j.toDouble/height, 0.25).castToColor()).toVector)
      .toVector

    Image(width, height, pixels)
  }

  def generateBackground(): Image = {
    val ratio = 16.0/9.0
    val width = 384
    val height = (width / ratio).toInt

    val viewportHeight = 2.0
    val viewportWidth = ratio * viewportHeight
    val focalLength = 1.0

    val origin = Vec3(0, 0, 0)
    val horizontal = Vec3(viewportWidth, 0, 0)
    val vertical = Vec3(0, viewportHeight, 0)
    val LowerLeftCorner = origin - (horizontal /= 2) - (vertical /= 2) - Vec3(0, 0, focalLength)

    val world = new Hittable_list()
    world.add(new Sphere(Vec3(0,0,-1), 0.5))
    world.add(new Sphere(Vec3(0,-100.5,-1), 100))

    val pixels = (height - 1 to 0 by -1)
      .map(j => (0 until width).map(i => Ray.rayColor(
        Ray(
          origin,
          LowerLeftCorner + (horizontal *= (i.toDouble/(width-1))) + (vertical *= (j.toDouble/(height-1))) - origin
        ), world)).toVector)
      .toVector

    Image(width, height, pixels)
  }
}
