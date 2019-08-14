name := "play-json-typedid"

organization := "com.tegonal"

version := "1.0.2"

scalaVersion := "2.12.8"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies ++= Seq(
  "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.3" % "test"
)

resolvers ++= Seq(
  Resolver.url("Typesafe repository", url("http://repo.typesafe.com/typesafe/releases/"))(Resolver.ivyStylePatterns),
  "Typesafe Releases Maven " at "http://repo.typesafe.com/typesafe/releases/")
  
  
initialCommands := "import com.tegonal.play.genericid._"
