package br.ufc.scalding.jobs

import com.twitter.scalding._
import _root_.cascading.tuple.Fields
import java.util.{Locale, Date}

/**

 * Author: Igo Brilhante
 * Date: 24/08/14
 * Time: 14:47
 */
class CitizenTouristJob (args : Args) extends Job(args) {

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
  val format2 = new java.text.SimpleDateFormat("EEE MMM dd HH:mm:ss +0000 yyyy", Locale.ENGLISH)  // Mon Jun 09 21:59:59 +0000 2014

  val f = Tsv( args("input"), fields=schema, skipHeader=true )
    .read
    .project("user_place", "state_uf")
    .groupAll { _.sortBy('user_place, 'state_uf) }
    .write( Tsv( args("output") ) )
}