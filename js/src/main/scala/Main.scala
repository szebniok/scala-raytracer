object Main extends App {
  val writer = new CanvasImageWriter()
  val image = Utils.getImage(HittableList.randomScene(), Camera.exampleCamera(), 384)

  writer.saveImage(Utils.generateBackground())
  println("sbt")
}
