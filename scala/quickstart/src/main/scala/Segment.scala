case class Point(name: String, x: Long, y: Long) {
}

case class Segment(f: Point, t: Point) {
  def distance = math.sqrt((f.x - t.x) * (f.x - t.x) + (f.y - t.y) * (f.y - t.y))
}

case class Path(segments: List[Segment]) {
  def distance = segments.map(_.distance).sum
}

case class PathList(paths: List[Path]) {
  def shortestPath = paths.minBy(_.distance)
}
