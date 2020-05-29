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