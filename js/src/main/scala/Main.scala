object Main extends App {
  val writer = new CanvasImageWriter()

  writer.saveImage(Utils.generateSampleGradient())
  println("sbt")
}
