object Main extends App {
  val writer = new CanvasImageWriter()

  val ratio = 16.0 / 9.0
  val width = 384
  val height = (width / ratio).toInt

  val samplesPerPixel = 50

  writer.saveImage(Utils.generateBackground(samplesPerPixel))
}
