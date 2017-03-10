import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.PropertyChecks

@RunWith(classOf[JUnitRunner])
class PathSpec extends FlatSpec with Matchers {

  val pa = Point("a", 0, 0)
  val pb = Point("b", 10, 10)
  val pc = Point("c", 20, 0)
  val pd = Point("d", 30, 0)
  val pe = Point("e", 40, 0)

  val ac = Segment(pa, pc)
  val bc = Segment(pb, pc)
  val ab = Segment(pa, pb)
  val cd = Segment(pc, pd)
  val de = Segment(pd, pe)
  // a---c--d--e
  //  \ /
  //   b

  val path = Path(List(ac, cd, de))
  val bigPath = Path(List(ac, cd, de, ab, bc))
  val pathList = PathList(List(path, bigPath))

  "a segment" should "compute its distance" in {
    val distance = ac.distance
    distance shouldBe 20.0 +- 0.0001
  }

  "a path" should "compute its distance" in {
    val distance = path.distance
    distance shouldBe 40.0 +- 0.0001
  }

  "a pathList" should "find its shortest path" in {
    val shortestPath = pathList.shortestPath
    shortestPath shouldBe path
  }

  "a path" should "provide a set of stops" in {
    val stops = path.stops
    stops shouldBe Set(pa, pc, pd, pe)
  }

  "a pathList" should "filter paths keeping only those which include a stop" in {
    val paths = pathList.filter(List(path, bigPath), pb)
    paths shouldBe List(bigPath)
  }

  "a pathList" should "filter paths keeping only those which include a given list of stops" in {
    val paths = pathList.filter(List(path, bigPath), List(pb))
    println(paths)
    paths shouldBe List(bigPath)
  }

}