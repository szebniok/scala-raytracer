object Utils {
  def generateSampleGradient(): Image = {
    val width = 256
    val height = 256

//    val pixels = (height - 1 to 0 by -1)
//      .map(j => (0 to width - 1).map(i => Color(i, j, 255 / 4)).toVector)
//      .toVector

    val pixels = (height - 1 to 0 by -1)
      .map(j => (0 to width - 1).map(i => Vec3(i.toDouble/width, j.toDouble/height, 0.25).castToColor()).toVector)
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

    val origin = Vec3()
    val horizontal = Vec3(viewportWidth, 0, 0)
    val vertical = Vec3(0, viewportHeight, 0)
    val LowerLeftCorner = origin - (horizontal /= 2) - (vertical /= 2) - Vec3(0, 0, focalLength)

    val pixels = (height - 1 to 0 by -1)
      .map(j => (0 to width - 1).map(i => Ray.rayColor(
        Ray(
          origin,
          LowerLeftCorner + (horizontal *= i.toDouble/width-1) + (vertical *= j.toDouble/height-1) - origin
        ))).toVector)
      .toVector

    Image(width, height, pixels)
  }
}
