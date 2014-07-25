package br.ufc.scalding.example
import com.twitter.scalding._
import scala.util.parsing.json._

/**

 * Author: Igo Brilhante
 * Date: 23/07/14
 * Time: 17:13
 */
class ReadJsonLine (args : Args) extends Job(args) {
  TextLine(args("input"))
    .flatMap('line -> 'word) {
    line : String =>  println(line)
    line.split("""\s+""")
  }
    .write(Tsv(args("output")))
}