package br.ufc.scalding.jobs
import com.twitter.scalding._
import _root_.cascading.tuple.Fields

/**

 * Author: Igo Brilhante
 * Date: 20/08/14
 * Time: 16:58
 */
class TrajsPerDayJob(args : Args) extends Job(args) {

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


  val f = Tsv( args("input"), fields=schema, writeHeader=true )
    .read
    .project("user_id", "id_tweet", "created_at_str")
    // remove duplicates
    .groupAll { _.sortBy('user_id, 'created_at_str) }
    .groupBy( 'user_id, 'created_at_str ) { _.size  }
    .filter( 'size ) { size : Int => (size > 1)}
    .groupBy( 'created_at_str ) { _.size  }
    .write( Tsv( args("output") ) )
}
