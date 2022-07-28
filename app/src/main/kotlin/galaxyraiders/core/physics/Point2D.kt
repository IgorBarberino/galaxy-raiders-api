@file:Suppress("UNUSED_PARAMETER") // <- REMOVE
package galaxyraiders.core.physics

data class Point2D(val x: Double, val y: Double) {
  operator fun plus(p: Point2D): Point2D {
    return Point2D(x + p.x, y + p.y)
  }

  operator fun plus(v: Vector2D): Point2D {
    return Point2D(x + v.dx, y + v.dy)
  }

  override fun toString(): String {
    return "Point2D(x=$x, y=$y)"
  }

  fun toVector(): Vector2D {
    return Vector2D(x, y)
  }

  fun impactVector(p: Point2D): Vector2D {
    return Vector2D(Math.abs(x - p.x), Math.abs(y - p.y))
  }

  fun impactDirection(p: Point2D): Vector2D {
    return Vector2D(Math.abs(x - p.x), Math.abs(y - p.y)).unit
  }

  fun contactVector(p: Point2D): Vector2D {
    return this.impactVector(p).normal
  }

  fun contactDirection(p: Point2D): Vector2D {
    return this.impactDirection(p).normal
  }

  fun distance(p: Point2D): Double {
    return Math.sqrt(Math.pow(p.x - x, 2.0) + Math.pow(p.y - y, 2.0))
  }
}
