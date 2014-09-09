package br.ufc.scalding.jobs

import java.util.{Locale}
import com.twitter.scalding._
import _root_.cascading.tuple.Fields
/**

 * Author: Igo Brilhante
 * Date: 08/09/14
 * Time: 23:54
 */
class BrasilTrendsJob(args : Args) extends Job(args) {
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
    .project('text, 'created_at_str)
    .unique('text, 'created_at_str)
    .flatMap('text -> 'token) { text : String => text.replaceAll("\"", "").split("[ \\[\\]\\(\\),.]") }
    .discard('text)
    .mapTo(('token, 'created_at_str) -> ('token, 'created_at_str)) {
    x : (String, String) => {
        val (token, created_at) = x
        (scrub(token), created_at.replace("\"", ""))
      }
    }
    .filter('token) { token : String => token.length > 1 && token.startsWith("#") }
    .leftJoinWithTiny('token -> 'stop, stopPipe)
    .filter('stop) { stop : String => {
        stop == null
      }
    }
    .groupBy('token, 'created_at_str) { _.size('count) }
    .groupAll( _.sortBy('count).reverse )
    .write( Tsv( args("output") ) )

  def scrub(token : String) : String = {
    token.trim.toLowerCase
  }

}
