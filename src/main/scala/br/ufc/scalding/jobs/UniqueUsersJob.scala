package br.ufc.scalding.jobs

import _root_.cascading.tuple.Fields
import com.twitter.scalding._
import scala.collection.JavaConversions._
import scala.collection.mutable.ListBuffer
import scala.Predef

/**

 * Author: Igo Brilhante
 * Date: 23/07/14
 * Time: 16:51
 */
class UniqueUsersJob (args : Args) extends Job(args) {

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
    "user_friends_count",
    "user_created_at",
    "user_screen_name"
  )


  val f = Csv( args("input"), fields=schema )
    .read
    .project("user_id_str")
    .unique('user_id_str)
    .groupAll { _.size }
    .write( Tsv( args("output") ) )

}
