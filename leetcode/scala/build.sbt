lazy val root = (project in file("."))
  .settings(
    name := "tredontho-leetcode",
    scalaVersion := "2.13.7",
    libraryDependencies += "com.disneystreaming" %% "weaver-cats" % "0.8.3" % Test,
    testFrameworks += new TestFramework("weaver.framework.CatsEffect")
  )
