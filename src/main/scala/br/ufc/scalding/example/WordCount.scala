package br.ufc.scalding.example
import com.twitter.scalding._


/**

 * Author: Igo Brilhante
 * Date: 22/07/14
 * Time: 11:47
 */

class WordCountJob(args : Args) extends Job(args) {
  TextLine( args("input") )
    .flatMap('line -> 'word) { line : String => line.split("""\s+""") }
    .groupBy('word) { _.size }
    .write( Tsv( args("output") ) )
}
