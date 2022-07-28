@file:Suppress("UNUSED_PARAMETER") // <- REMOVE
package galaxyraiders.core.physics

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties("unit", "normal", "degree", "magnitude")
data class Vector2D(val dx: Double, val dy: Double) {
  override fun toString(): String {
    return "Vector2D(dx=$dx, dy=$dy)"
  }

  val magnitude: Double
    get() = Math.sqrt(Math.pow(dx, 2.0) + Math.pow(dy, 2.0))

  val radiant: Double
    get() = Math.atan2(dy, dx)

  val degree: Double
    get() = Math.toDegrees(radiant)

  val unit: Vector2D
    get() = Vector2D(dx / this.magnitude, dy / this.magnitude)

  val normal: Vector2D
    get() = Vector2D(dy / this.magnitude, (-dx / this.magnitude))

  operator fun times(scalar: Double): Vector2D {
    return Vector2D((scalar * dx), (scalar * dy))
  }

  operator fun div(scalar: Double): Vector2D {
    return Vector2D(dx / scalar, dy / scalar)
  }

  operator fun times(v: Vector2D): Double {
    return dx * v.dx + dy * v.dy
  }

  operator fun plus(v: Vector2D): Vector2D {
    return Vector2D(dx + v.dx, dy + v.dy)
  }

  operator fun plus(p: Point2D): Point2D {
    return Point2D(dx + p.x, dy + p.y)
  }

  operator fun unaryMinus(): Vector2D {
    return Vector2D(-dx, -dy)
  }

  operator fun minus(v: Vector2D): Vector2D {
    return Vector2D(dx - v.dx, dy - v.dy)
  }

  fun scalarProject(target: Vector2D): Double {
    return (this * target) / target.magnitude
  }

  fun vectorProject(target: Vector2D): Vector2D {
    var finalVector = target * ((this * target) / Math.pow(target.magnitude, 2.0))
    if (target.dy == 0.0) {
      finalVector = Vector2D(dx, 0.0)
    } else if (target.dx == 0.0) {
      finalVector = Vector2D(0.0, dy)
    }
    return finalVector
  }
}

operator fun Double.times(v: Vector2D): Vector2D {
  return Vector2D((this * v.dx), (this * v.dy))
}
