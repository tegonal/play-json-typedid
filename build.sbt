
name := "play-json-typedid"

organization := "com.tegonal"

version := "2.0.1"

scalaVersion := "3.3.6"

crossScalaVersions := Seq("3.3.6", "2.13.18")

lazy val root = (project in file(".")).enablePlugins(PlayScala)

resolvers += "Typesafe Repository" at "https://repo.typesafe.com/typesafe/releases/"

resolvers += "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies ++= Seq(
  "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.1" % "test"
)

resolvers ++= Seq(
  Resolver.url("Typesafe repository", url("https://repo.typesafe.com/typesafe/releases/"))(Resolver.ivyStylePatterns),
  "Typesafe Releases Maven " at "https://repo.typesafe.com/typesafe/releases/")
  
  
initialCommands := "import com.tegonal.play.genericid._"
