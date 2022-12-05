lazy val root = project
  .in(file("."))
  .settings(
    name := "AdventOfCode2022",
    version := "0.0.1",
    scalaVersion := "3.2.1",
    scalacOptions ++=
      Seq(
        "-deprecation"
      )
  )