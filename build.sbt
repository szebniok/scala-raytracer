ThisBuild / scalaVersion := "2.13.2"

lazy val root = project.in(file(".")).
  aggregate(raytracer.js, raytracer.jvm).
  settings(
    publishLocal := {},
    publish := {},
  )

lazy val raytracer = crossProject(JSPlatform, JVMPlatform).in(file(".")).
  settings(
    name := "raytracer",
    version := "0.1",
  ).
  jvmSettings(
  ).
  jsSettings(
    libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "1.0.0",
    scalaJSUseMainModuleInitializer := true,
  )