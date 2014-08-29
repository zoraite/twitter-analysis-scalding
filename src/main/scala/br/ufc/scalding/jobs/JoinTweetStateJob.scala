package br.ufc.scalding.jobs
import com.twitter.scalding._
import _root_.cascading.tuple.Fields
import java.util.{Locale, Date}
import com.vividsolutions.jts.io.WKTReader
import com.vividsolutions.jts.geom.{Point, Coordinate, GeometryFactory, Geometry}

/**

 * Author: Igo Brilhante
 * Date: 20/08/14
 * Time: 17:34
 */
class JoinTweetStateJob(args : Args) extends Job(args) {

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


  def pointWithinPolygon( lng: String, lat: String, wkt: String ) : Boolean = {

    val point = createPoint(lng, lat)
    val multipolygon = getPolygonFromWKT(wkt)

    multipolygon.contains(point);
//    val n = polygon.length;
//    var c = false
//    var i = 0
//    val (x,y) = point
//    var j = n-1
//
//    while (i < n) {
//      val (px_i, py_i) = polygon(i)
//      val (px_j, py_j) = polygon(j)
//
//      if (((( py_i <= y ) && (y < py_j)) || ((py_j <= y) && (y < py_i))) && (x < (px_j - px_i) * (y - py_i) / (py_j - py_i) + px_i))
//        c = !c;
//
//      j = i
//      i += 1
//    }
//
//    return c
  }

  def getPolygonFromWKT( wkt : String ) : Geometry = {

    val wktReader = new WKTReader()

    return wktReader.read(wkt);

    //    val t = wkt.replace("MULTIPOLYGON", "").replace(")", "").replace("(", "")
    //    val coordinates = t.split(",")
    //
    //    coordinates.map( s => {
    //      val coordinate = s.split(" ");
    //      ( coordinate(0).toDouble, coordinate(1).toDouble )
    //    })
  }

  def createPoint( lng: String, lat: String ) : Point = {
    val fact = new GeometryFactory();
    val coordinates = new Coordinate(lng.toDouble, lat.toDouble)

    fact.createPoint(coordinates)
  }

  var stateList = TextLine( args("estados") )
    .pipe
    .mapTo('line -> ('id_state, 'geom, 'uf, 'name_state, 'region)) {
    line : String => {
      var l = line.split(";")
      (l(0), l(1), l(2), l(3), l(4))
    }
  }


  val f = Tsv( args("input"), fields=schema, writeHeader=true )
    .read
    .crossWithTiny(stateList)
    .filter('longitude, 'latitude, 'geom) {
    fields : (String, String, String) => {
      val (lng, lat, polygon) = fields

      pointWithinPolygon( lng, lat, polygon )
    }
  }
    //      .project('place_name, 'name_state)
    .discard('geom)
    .write( Tsv( args("output") ) )
}
