name := "Scala Days"

scalaVersion := "2.11.8"

version := "0.0.1-SNAPSHOT"

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

scalacOptions ++= Seq("-feature","-deprecation", "-unchecked", "-Xlint")
scalacOptions in Test ++= Seq("-Yrangepos")

testOptions in Test := Seq(
  Tests.Argument(TestFrameworks.Specs2, "-xonly")
)


libraryDependencies ++= Seq(
  "org.specs2" %% "specs2-core" % "3.6" % "test",
  "org.specs2" %% "specs2-mock" % "3.6" % "test",
  "org.scalameta" %% "scalameta" % "0.20.0"
)

tutSettings

