import Dependencies._

ThisBuild / scalaVersion     := "2.13.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "dev.tredontho"
ThisBuild / organizationName := "tredontho"
ThisBuild / semanticdbEnabled := true
ThisBuild / semanticdbVersion := scalafixSemanticdb.revision


ThisBuild / scalafixDependencies ++= Seq(
  "com.github.liancheng" %% "organize-imports" % "0.6.0"
)
ThisBuild / scalafixScalaBinaryVersion := scalaBinaryVersion.value

lazy val aoc = (project in file("."))
  .settings(
    name := "AdventOfCode",
    libraryDependencies += scalaTest % Test,
    scalacOptions += "-Ywarn-unused"
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
