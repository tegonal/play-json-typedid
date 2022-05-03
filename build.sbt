
name := "play-json-typedid"

organization := "com.tegonal"

version := "1.0.3"

scalaVersion := "2.13.8"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

resolvers += "Typesafe Repository" at "https://repo.typesafe.com/typesafe/releases/"

resolvers += "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies ++= Seq(
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % "test"
)

resolvers ++= Seq(
  Resolver.url("Typesafe repository", url("https://repo.typesafe.com/typesafe/releases/"))(Resolver.ivyStylePatterns),
  "Typesafe Releases Maven " at "https://repo.typesafe.com/typesafe/releases/")
  
  
initialCommands := "import com.tegonal.play.genericid._"
