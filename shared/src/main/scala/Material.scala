trait Material {
  // produce a scattered ray and attenuation if not absorbed
  def scatter(in: Ray, rec: HitRecord): Option[(Ray, Vec3)]
}

case class Lambertian(albedo: Vec3) extends Material {
  override def scatter(in: Ray, rec: HitRecord): Option[(Ray, Vec3)] = {
    val scatteredDirection = rec.normal + Vec3.randomUnitVector()
    val scattered = Ray(rec.p, scatteredDirection)
    val attenuation = albedo
    Some((scattered, attenuation))
  }
}

case class Metal(albedo: Vec3, fuzz: Double) extends Material {
  require(0 <= fuzz && fuzz <= 1)

  override def scatter(in: Ray, rec: HitRecord): Option[(Ray, Vec3)] = {
    val reflected = Vec3.reflect(Vec3.unitVector(in.direction), rec.normal)
    val scattered = Ray(rec.p, reflected + Vec3.*(Vec3.randomInUnitSphere(), fuzz))
    val attenuation = albedo
    if (Vec3.dot(scattered.direction, rec.normal) > 0)
      Some((scattered, attenuation))
    else None
  }
}

case class Dielectric(refIdx: Double) extends Material {

  def schlick(cosine: Double, refIdx: Double): Double = {
    val r0 = (1 - refIdx) / (1 + refIdx)
    r0 * r0 + (1 - r0 * r0) * Math.pow((1 - cosine), 5)
  }

  override def scatter(in: Ray, rec: HitRecord): Option[(Ray, Vec3)] = {
    val attenuation = Vec3(1.0, 1.0, 1.0)
    val etaiOverEtat = if (rec.front_face) (1.0 / refIdx) else refIdx
    val unitDirection = Vec3.unitVector(in.direction)

    val cosTheta = Math.min(Vec3.dot(-unitDirection, rec.normal), 1.0)
    val sinTheta = Math.sqrt(1.0 - cosTheta * cosTheta)
    val reflectProb = schlick(cosTheta, etaiOverEtat)

    if(etaiOverEtat * sinTheta > 1.0) {
      val reflected = Vec3.reflect(unitDirection, rec.normal)
      val scattered = Ray(rec.p, reflected)
      Some((scattered, attenuation))
    }
    else if(Utils.random_double() < reflectProb){
      val reflected = Vec3.reflect(unitDirection, rec.normal)
      val scattered = Ray(rec.p, reflected)
      Some((scattered, attenuation))
    }
    else {
      val refracted = Vec3.refract(unitDirection, rec.normal, etaiOverEtat)
      val scattered = Ray(rec.p, refracted)
      Some((scattered, attenuation))
    }
  }
}