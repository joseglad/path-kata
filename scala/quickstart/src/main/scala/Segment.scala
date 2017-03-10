case class Point(name: String, x: Long, y: Long) {
}

case class Segment(f: Point, t: Point) {
  def distance: Double = math.sqrt((f.x - t.x) * (f.x - t.x) + (f.y - t.y) * (f.y - t.y))
}

case class Path(segments: List[Segment]) {
  def distance: Double = segments.map(_.distance).sum
  def stops: Set[Point] = segments.flatMap(seg => Set(seg.f, seg.t)).toSet
}

case class PathList(paths: List[Path]) {
  def shortestPath: Path = paths.minBy(_.distance)
  def filter(paths: List[Path], stop: Point): List[Path] =
    paths.filter(p => p.stops.contains(stop))
  def filter(paths: List[Path], stops: List[Point]): List[Path] =
    paths.filter(path => stops.forall(path.stops.contains))
}
