libraryDependencies ++= Seq(
    "com.twitter" %% "scalding-core" % "0.11.1"
)

initialCommands in console := """
  | import com.twitter.scalding._
  | import com.twitter.scalding.mathematics._
   """.stripMargin
