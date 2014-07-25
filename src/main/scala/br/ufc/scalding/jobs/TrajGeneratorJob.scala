package br.ufc.scalding.jobs
import com.twitter.scalding._


/**
 *
 * Generate trajectories from tweets
 */
class TrajJob(args : Args) extends Job(args) {

  val f = TextLine( args("input") )
    .flatMap('line -> 'pair) {
        line : String =>
        // create scala list
        val list = line.split(",").toList

        // merge the two list
        val pairs = list zip list.tail

        // create list of pairs a-b
        pairs.map { case (a, b) => a + "-" + b }
    }
    .groupBy('pair) { _.size }
    .write( Tsv( args("output") ) )
}
