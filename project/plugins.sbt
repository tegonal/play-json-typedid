resolvers += Classpaths.sbtPluginReleases

// Use the Play sbt plugin for Play projects
addSbtPlugin("org.playframework" % "sbt-plugin" % "3.0.11")

// Check for dependency and plugin updates
addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.6.4")
