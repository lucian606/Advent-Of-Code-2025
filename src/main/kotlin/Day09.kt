import io.data2viz.geojson.Line
import kotlin.math.abs
import io.data2viz.geojson.Polygon

class Day09(filePath: String) : DaySolver(filePath) {
    private data class CardinalPoint(
        val x: Int,
        val y: Int
    ) {
        override fun toString(): String {
            return "($x,$y)"
        }
    }

    private fun getRedTiles(input: List<String>): List<CardinalPoint> {
        val tiles = mutableListOf<CardinalPoint>()

        for (line in input) {
            val coordinates = line.split(',').map { it.toInt() }
            tiles.add(CardinalPoint(coordinates[0], coordinates[1]))
        }

        return tiles
    }

    private fun getRectangleArea(firstCorner: CardinalPoint, secondCorner: CardinalPoint): Long {
        return (abs(firstCorner.x - secondCorner.x) + 1L) * (abs(firstCorner.y - secondCorner.y) + 1L)
    }

    override fun solvePartOne(input: List<String>): String {
        val redTiles = getRedTiles(input)
        var maxArea = 0L

        for (i in redTiles.indices) {
            for (j in i + 1 until redTiles.size) {
                val area = getRectangleArea(redTiles[i], redTiles[j])

                if (area > maxArea) {
                    maxArea = area
                }
            }
        }

        return maxArea.toString()
    }

    override fun solvePartTwo(input: List<String>): String {
        val redTiles = getRedTiles(input)
        val inside = mutableSetOf<CardinalPoint>()
        inside.addAll(redTiles)
        val outside = mutableSetOf<CardinalPoint>()

        val lines = mutableListOf<Line>()

        for (i in redTiles.indices) {
            var nextVertex: CardinalPoint? = null
            val vertex = redTiles[i]
            if (i == redTiles.size - 1) {
                nextVertex = redTiles[0]
            } else {
                nextVertex = redTiles[i + 1]
            }
            val line = Line(
                arrayOf(vertex.x, nextVertex.x),
                arrayOf(vertex.y, nextVertex.y)
            )
        }

        var maxArea = 0L

//        for (i in redTiles.indices) {
//            for (j in i + 1 until redTiles.size) {
//                val minX = Math.min(redTiles[i].x, redTiles[j].x)
//                val maxX = Math.max(redTiles[i].x, redTiles[j].x)
//                val minY = Math.min(redTiles[i].y, redTiles[j].y)
//                val maxY = Math.max(redTiles[i].y, redTiles[j].y)
//
//                if (minX == maxX || minY == maxY) {
//                    continue
//                }
//
//                var isValid = true
//
//                for (x in minX..maxX) {
//                    for (y in minY..maxY) {
//                        val cardinalPoint = CardinalPoint(x, y)
//
//                        if (cardinalPoint in outside) {
//                            isValid = false
//                            break
//                        } else if (cardinalPoint in inside || isPointInsidePolygon(redTiles, CardinalPoint(x,y))) {
//                            inside.add(cardinalPoint)
//                        } else {
//                            outside.add(cardinalPoint)
//                        }
//                    }
//                }
//
//                if (isValid) {
//                    val area = getRectangleArea(redTiles[i], redTiles[j])
//                    if (maxArea < area) {
//                        maxArea = area
//
//                        println(redTiles[i].toString() + " - " + println(redTiles[j]).toString())
//                    }
//                }
//            }
//        }

        println(redTiles)

        return maxArea.toString()
    }
}

fun main() {
    val path = "src/main/inputs/day09.in"
    val day = Day09(path)
    day.printSolution()
}
