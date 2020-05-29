class Ray(val origin: Vec3, val direction: Vec3) {
  def at(t: Double): Vec3 = Vec3(origin + (direction *= t))
}

object Ray {
  def apply(origin: Vec3, direction: Vec3) = new Ray(origin, direction)

  def rayColor(r: Ray, world: Hittable): Vec3 = {
    val rec: Hit_record = Hit_record()
    if(world.hit(r, 0, Double.PositiveInfinity, rec)){
      (Vec3(1.0, 1.0, 1.0) + rec.normal) *= 0.5
    }
    else{
      val unitDirection = Vec3.unitVector(r.direction)
      val t = 0.5 * (unitDirection.y + 1.0)
      (Vec3(1.0, 1.0, 1.0) *= (1.0 - t)) + (Vec3(0.5, 0.7, 1.0) *= t)
    }
  }
}
