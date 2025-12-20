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
        return "Todo in kotlin"
    }
}

fun main() {
    val path = "src/main/inputs/day09.in"
    val day = Day09(path)
    day.printSolution()
}
