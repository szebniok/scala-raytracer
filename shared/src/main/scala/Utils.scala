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

    val world = new Hittable_list()
    world.add(new Sphere(Vec3(0,0,-1), 0.5))
    world.add(new Sphere(Vec3(0,-100.5,-1), 100))

    val camera = new Camera

    val pixels = (height - 1 to 0 by -1)
      .map(j => (0 until width)
        .map(i => (0 until Utils.samples)
          .map(_ => Ray.rayColor(camera.get_ray((i + Utils.random_double()) / (width - 1),
            (j + Utils.random_double()) / (height - 1)), world, Utils.max_depth)).foldLeft(Vec3(0,0,0))(_ + _).castToColor()).toVector).toVector

    Image(width, height, pixels)
  }

  val samples : Int = 100
  val max_depth : Int = 50

  def clamp(x: Double, min: Double, max: Double): Double = {
    if (x < min) {
      return min
    }
    if (x > max) {
      return max
    }
    x
  }

  def random_double(): Double = {
    val r = scala.util.Random
    r.nextDouble()
  }

  def random_double(min: Double, max: Double): Double = {
    min + (max - min) * random_double()
  }
}
