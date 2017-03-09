case class Point(name: String, x: Long, y: Long) {
}

case class Segment(f: Point, t: Point) {
  def distance: Double = math.sqrt((f.x - t.x) * (f.x - t.x) + (f.y - t.y) * (f.y - t.y))
}

case class Path(segments: List[Segment]) {
  def distance: Double = segments.map(_.distance).sum
  def stops: Set[Point] = segments.flatMap(s => Set(s.f, s.t)).toSet
}

case class PathList(paths: List[Path]) {
  def shortestPath: Path = paths.minBy(_.distance)
}
