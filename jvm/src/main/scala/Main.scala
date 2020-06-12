import Vec3.point3

object Main extends App {
  val writer = new PPMImageWriter()
  writer.saveImage(Utils.getImage(HittableList.randomScene(), Camera.exampleCamera(), 384))
}
