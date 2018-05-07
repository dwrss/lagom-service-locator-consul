import sbt.Keys.{licenses, version}

name := "lagom-service-locator-consul"

val lagomVersion = "1.4.4"

val typesafeConfig = "com.typesafe" % "config" % "1.3.1"
val lagomJavadslClient = "com.lightbend.lagom" %% "lagom-javadsl-client" % lagomVersion
val lagomScaladslClient = "com.lightbend.lagom" %% "lagom-scaladsl-client" % lagomVersion
val consulApi = "com.ecwid.consul" % "consul-api" % "1.3.0"
val scalatest = "org.scalatest" %% "scalatest" % "3.0.4" % Test

lazy val `lagom-service-locator-consul` = (project in file("."))
  .aggregate(
    `lagom-service-locator-javadsl-consul`,
    `lagom-service-locator-scaladsl-consul`
  )

common

def common = Seq(
  scalaVersion := "2.12.6",
  version := "2.0.2",
  organization := "com.lightbend.lagom",
  licenses += ("Apache-2.0", url("http://apache.org/licenses/LICENSE-2.0")),
  bintrayRepository := "lagom"
)

lazy val `lagom-service-locator-javadsl-consul` = (project in file("lagom-service-locator-javadsl-consul"))
  .settings(common)
  .settings(
    libraryDependencies ++= Seq(
      lagomJavadslClient,
      consulApi,
      scalatest
    )
  )

lazy val `lagom-service-locator-scaladsl-consul` = (project in file("lagom-service-locator-scaladsl-consul"))
  .settings(common)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslClient,
      consulApi,
      scalatest
    )
  )