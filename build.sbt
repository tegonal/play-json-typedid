name := "play-json-typedid"

organization := "com.tegonal"

version := "1.0.1"

scalaVersion := "2.11.1"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies ++= Seq(
  "org.scalatestplus" %% "play" % "1.1.0" % "test"
)

resolvers ++= Seq(
  Resolver.url("Typesafe repository", url("http://repo.typesafe.com/typesafe/releases/"))(Resolver.ivyStylePatterns),
  "Typesafe Releases Maven " at "http://repo.typesafe.com/typesafe/releases/")
  
  
initialCommands := "import com.tegonal.play.genericid._"
