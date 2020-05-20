import java.io.PrintWriter

class PPMImageWriter extends ImageWriter {
  private def drawPixel(pw: PrintWriter, color: Color): Unit =
    pw.printf("%d %d %d\n", color.r, color.g, color.b)

  override def saveImage(image: Image): Unit = {
    val pw = new PrintWriter("output.ppm")

    pw.println("P3")
    pw.printf("%d %d\n", image.width, image.height)
    pw.println("255")
    for {
      y <- 0 until image.height
      x <- 0 until image.width
    } drawPixel(pw, image.pixels(y)(x))

    pw.close()
  }
}
