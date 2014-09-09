package br.ufc.scalding.jobs

import java.util.{Locale}
import com.twitter.scalding._
import _root_.cascading.tuple.Fields
/**

 * Author: Igo Brilhante
 * Date: 08/09/14
 * Time: 23:54
 */
class StateTrendsJob(args : Args) extends Job(args) {
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
    "user_screen_name",
    "state_id",
    "state_uf",
    "state_name",
    "state_region"
  )

  val format1 = new java.text.SimpleDateFormat("yyyy-MM-dd")

  // read stop words pipe
  val stopPipe = Tsv( args("stop"), ('stop)).read


  val f = Tsv( args("input"), fields=schema )
    .read
    .project('text, 'created_at_str, 'state_uf)
    .unique('text, 'created_at_str, 'state_uf)
    .flatMap('text -> 'token) { text : String => text.replaceAll("\"", "").split("[ \\[\\]\\(\\),.]") }
    .discard('text)
    .mapTo(('token, 'created_at_str, 'state_uf) -> ('token, 'created_at_str, 'state_uf)) {
    x : (String, String, String) => {
      val (token, created_at, state) = x
      (scrub(token), created_at.replace("\"", ""), state)
    }
  }
    .filter('token) { token : String => token.length > 1 && token.startsWith("#") }
    .leftJoinWithTiny('token -> 'stop, stopPipe)
    .filter('stop) { stop : String => {
    stop == null
  }
  }
    .groupBy('state_uf, 'token, 'created_at_str) { _.size('count) }
    .groupBy('state_uf, 'created_at_str){ _.sortBy('count).reverse.take( args("k").toInt ) }
    .groupAll { _.sortBy('state_uf, 'created_at_str)}
    .write( Tsv( args("output") ) )

  def scrub(token : String) : String = {
    token.trim.toLowerCase
  }

}
