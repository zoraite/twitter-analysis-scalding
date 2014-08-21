package br.ufc.scalding.jobs
import com.twitter.scalding._
import _root_.cascading.tuple.Fields
import java.util.{Locale, Date}

/**

 * Author: Igo Brilhante
 * Date: 20/08/14
 * Time: 17:34
 */
class ODMatrixJob(args : Args) extends Job(args) {

  val schema = new Fields(
    "id",
    "text",
    "latitude",
    "longitude",
    "retweeted",
    "id_tweet",
    "id_tweet_str",
    "created_at",
    "created_at_str",
    "in_reply_to_user_id",
    "lang",
    "place_name",
    "place_id",
    "place_bounding_box_00",
    "place_bounding_box_01",
    "place_bounding_box_10",
    "place_bounding_box_11",
    "place_bounding_box_20",
    "place_bounding_box_21",
    "place_bounding_box_30",
    "place_bounding_box_31",
    "place_type",
    "place_country_code",
    "place_country",
    "place_full_name",
    "user_name",
    "user_id",
    "user_id_str",
    "user_place",
    "user_friends_count",
    "user_created_at",
    "user_screen_name"
  )

  val estadosSchema = new Fields("id", "geom", "uf", "name", "region")

  val format1 = new java.text.SimpleDateFormat("yyyy-MM-dd")
  val format2 = new java.text.SimpleDateFormat("EEE MMM dd HH:mm:ss +0000 yyyy", Locale.ENGLISH)  // Mon Jun 09 21:59:59 +0000 2014

  var stateList = TextLine( args("input"))
    .pipe
    .mapTo('line -> ('id, 'geom, 'uf, 'name, 'region)) {
      line : String => {
        var l = line.split(";")
        (l(0), l(1), l(2), l(3), l(4))
      }
    }
    stateList.write( Tsv( args("output") ) )

  println(stateList)

  //  val f = Tsv( args("input"), fields=schema, writeHeader=true )
  //    .read
  //    .project("user_id", "place_name", "created_at", "created_at_str")
  //    // remove duplicates
  //    .map ('created_at_str-> 'created_at_str_date) {x: String => format1.parse(x.replace("\"", "")) }
  //    .map ('created_at-> 'created_at_date) {x: String => format2.parse(x.replace("\"", "")) }
  //    .groupAll { _.sortBy('user_id, 'created_at_date) }
  //    .groupBy( 'user_id, 'created_at_str_date ) { _.mkString('place_name, ";")  }
  //    .filter( 'place_name ) { s : String => ( s.split(";").length > 1) }
  //    .flatMap('place_name -> 'pair) {
  //    line : String =>
  //    // create scala list
  //      val list = line.split(";").toList
  //
  //      // merge the two list
  //      val pairs = list zip list.tail
  //
  //      // create list of pairs a-b
  //      pairs.map { case (a, b) => a + "-" + b }
  //  }
  //    .groupBy('pair) { _.size }
  //    .groupAll { _.sortBy('size).reverse }
  //    .write( Tsv( args("output") ) )
}
