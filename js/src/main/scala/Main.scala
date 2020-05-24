object Main extends App {
  val writer = new CanvasImageWriter()

  writer.saveImage(Utils.generateBackground())
  println("sbt")
}
