
ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.14"

lazy val root = (project in file("."))
  .settings(
    name := "pekko-learning-labs",
    idePackagePrefix := Some("cyapex")
  )
libraryDependencies ++= Seq(
  "org.apache.pekko" %% "pekko-actor-typed" % "1.1.2",
  "org.apache.pekko" %% "pekko-slf4j" % "1.1.2",
  "ch.qos.logback" % "logback-classic" % "1.4.11" // for logging output
)