resolvers += "Sonatype releases" at "https://oss.sonatype.org/content/repositories/releases/"

resolvers += "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

resolvers += "http://conjars.org/repo" at "http://conjars.org/repo/"

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.6.0")
