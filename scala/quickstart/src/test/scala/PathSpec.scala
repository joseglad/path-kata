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

  "a path list" should "find its shortest path" in {
    val shortestPath = pathList.shortestPath
    Some(shortestPath) shouldBe Some(path)
  }

}