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

}
