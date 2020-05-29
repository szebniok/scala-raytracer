class Ray(val origin: Vec3, val direction: Vec3) {
  def at(t: Double): Vec3 = Vec3(origin + (direction *= t))
}

object Ray {
  def apply(origin: Vec3, direction: Vec3) = new Ray(origin, direction)

  def rayColor(r: Ray): Color = {
    var t = hitSphere(Vec3(0, 0, -1), 0.5, r)
    if(t > 0.0){
      val N = Vec3.unitVector(r.at(t) - Vec3(0, 0, -1))
      (Vec3(N.x + 1.0, N.y + 1.0, N.z + 1.0) *= 0.5).castToColor()
    }
    else{
      val unitDirection = Vec3.unitVector(r.direction)
      t = 0.5 * (unitDirection.y + 1.0)
      ((Vec3(1.0, 1.0, 1.0) *= (1.0 - t)) + (Vec3(0.5, 0.7, 1.0) *= t)).castToColor()
    }
  }

  def hitSphere(center: Vec3, radius: Double, r: Ray): Double = {
    val oc = r.origin - center
    val a = Vec3.dot(r.direction, r.direction)
    val b = 2.0 * Vec3.dot(oc, r.direction)
    val c = Vec3.dot(oc, oc) - radius * radius
    val delta = (b * b) - (4 * a * c)
    if(delta < 0){
      -1.0
    }
    else{
      (-b - Math.sqrt(delta)) / (2.0 * a)
    }
  }
}
