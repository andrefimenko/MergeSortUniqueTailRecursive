ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "AkkaHttp5mins"
  )
val akkaVersion = "2.7.0"
val akkaHttpVersion = "10.4.0"

libraryDependencies ++= Seq(
  // akka streams
  "com.typesafe.akka" %% "akka-stream"          % akkaVersion,
  // akka http
  "com.typesafe.akka" %% "akka-http"            % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.4.0"
)