class Ray(val origin: Vec3, val direction: Vec3) {
  def at(t: Double): Vec3 = Vec3(origin + (direction *= t))
}

object Ray {
  def apply(origin: Vec3, direction: Vec3) = new Ray(origin, direction)

  def rayColor(r: Ray, world: Hittable, depth: Int): Vec3 = {
    val rec: Hit_record = Hit_record()

    if(depth <= 0){
      return Vec3(0.0, 0.0, 0.0)
    }

    if(world.hit(r, 0.001, Double.PositiveInfinity, rec)){
      val point = rec.p + Vec3.random_point_in_hemisphere(rec.normal)
      rayColor(Ray(rec.p, point - rec.p), world, depth - 1) *= 0.5
    }
    else{
      val unitDirection = Vec3.unitVector(r.direction)
      val t = 0.5 * (unitDirection.y + 1.0)
      (Vec3(1.0, 1.0, 1.0) *= (1.0 - t)) + (Vec3(0.5, 0.7, 1.0) *= t)
    }
  }
}
