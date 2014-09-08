package br.ufc.scalding.jobs

import java.util.{Locale}
import com.twitter.scalding._
import _root_.cascading.tuple.Fields

/**

 * Author: Igo Brilhante
 * Date: 08/09/14
 * Time: 22:39
 */
class CheckInPerStateJob(args : Args) extends Job(args) {
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
  val checkinPrefix = "I\'m at"

  val f = Tsv( args("input"), fields=schema )
    .read
    .filter('text) {
      tweet : String => { isCheckIn(tweet) }
    }
    .map ('text -> 'checkinPlace) { x: String => getCheckinPlace( x ) }
    .write( Tsv( args("output") ) )

  def isCheckIn( tweet : String) : Boolean = {
    val p = tweet.replaceAll("\"", "")
    p.startsWith(checkinPrefix) && p.contains("https")
  }

  def getCheckinPlace( tweet:String ) : String = {
    val p = tweet.replaceAll("\"", "")
    val indexOfWith = p.indexOf("w/")
    val indexOfHTTP = p.indexOf("https")
    var res = ""
    if ( indexOfWith > -1 ) {
      res = p.substring(6, indexOfWith).trim
    } else {
      res = p.substring(6, indexOfHTTP).trim
    }

    "\"" + res + "\"";

  }

}

