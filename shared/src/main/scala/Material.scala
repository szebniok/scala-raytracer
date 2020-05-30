trait Material {
  // produce a scattered ray and attenuation if not absorbed
  def scatter(in: Ray, rec: Hit_record): Option[(Ray, Vec3)]
}

case class Lambertian(albedo: Vec3) extends Material {
  override def scatter(in: Ray, rec: Hit_record): Option[(Ray, Vec3)] = {
    val scatteredDirection = rec.normal + Vec3.randomUnitVector()
    val scattered = Ray(rec.p, scatteredDirection)
    val attenuation = albedo
    Some((scattered, attenuation))
  }
}

case class Metal(albedo: Vec3, fuzz: Double) extends Material {
  require(0 <= fuzz && fuzz <= 1)

  override def scatter(in: Ray, rec: Hit_record): Option[(Ray, Vec3)] = {
    val reflected = Vec3.reflect(Vec3.unitVector(in.direction), rec.normal)
    val scattered = Ray(rec.p, reflected + Vec3.*(Vec3.randomInUnitSphere(), fuzz))
    val attenuation = albedo
    if (Vec3.dot(scattered.direction, rec.normal) > 0)
      Some((scattered, attenuation))
    else None
  }
}

case class Light(albedo: Vec3) extends Material {
  override def scatter(in: Ray, rec: Hit_record): Option[(Ray, Vec3)] = {
    val scattered = Ray(rec.p, Vec3(0, 0, 0))
    val attenuation = albedo
    Some((scattered, attenuation))
  }
}