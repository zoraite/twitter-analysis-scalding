name := "twitter-scalding"

version := "1.0"

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
	"com.twitter" % "scalding-core_2.10" % "0.11.1"
)

initialCommands in console := """
  | import com.twitter.scalding._
  | import com.twitter.scalding.mathematics._
   """.stripMargin
