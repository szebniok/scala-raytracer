object Main extends App {
  val writer = new PPMImageWriter()
  writer.saveImage(Utils.generateBackground())
}
