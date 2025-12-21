import java.util.LinkedList
import java.util.Queue
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class Day09(filePath: String) : DaySolver(filePath) {
    private data class CardinalPoint(
        val x: Int,
        val y: Int
    ) {
        override fun toString(): String {
            return "($x,$y)"
        }
    }

    private fun CardinalPoint.add(other: CardinalPoint): CardinalPoint = CardinalPoint(this.x + other.x, this.y + other.y)

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

    private fun getOutsidePoints(border: Set<CardinalPoint>, limit: Int): Set<CardinalPoint> {

        val queue: Queue<CardinalPoint> = LinkedList()
        queue.add(CardinalPoint(0, 0))

        val outside = mutableSetOf<CardinalPoint>()
        var step = 0

        while (queue.isNotEmpty()) {
            step++
            println(step)
            val point = queue.poll()

            if (point in outside) {
                continue
            }

            outside.add(point)

            val north = point.add(CardinalPoint(0, 1))
            if (north.y < limit && north !in border && north !in outside) {
                queue.add(north)
            }

            val south = point.add(CardinalPoint(0, -1))

            if (south.y > 0 && south !in border && south !in outside) {
                queue.add(south)
            }

            val east = point.add(CardinalPoint(1, 0))

            if (east.x < limit && east !in border && east !in outside) {
                queue.add(east)
            }

            val west = point.add(CardinalPoint(-1, 0))

            if (west.x > 0 && west !in border && west !in outside) {
                queue.add(west)
            }


        }

        return outside
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
        // TODO: Since there is a giant area, just do a BFS/DFS and add all the points you can touch
        // Note this won't work if you thing touches corners
        val redTiles = getRedTiles(input)
        val borders = mutableSetOf<CardinalPoint>()

        for (i in redTiles.indices) {
            val tile = redTiles[i]
            var nextTile: CardinalPoint? = null
            if (i == redTiles.size - 1) {
                nextTile = redTiles[0]
            } else {
                nextTile = redTiles[i + 1]
            }

            if (tile.x == nextTile.x) {
                val maxY = max(tile.y, nextTile.y)
                val minY = min(tile.y, nextTile.y)

                for (y in minY..maxY) {
                    borders.add(CardinalPoint(tile.x, y))
                }
            } else {
                val maxX = max(tile.x, nextTile.x)
                val minX = min(tile.x, nextTile.x)

                for (x in minX..maxX) {
                    borders.add(CardinalPoint(x, tile.y))
                }
            }

        }


        val outside = getOutsidePoints(borders, 100000)
        println("Finished flooding outside")

//        for (i in redTiles.indices) {
//            for (j in i + 1 until redTiles.size) {
//                val area = getRectangleArea(redTiles[i], redTiles[j])
//
//                if (area > maxArea) {
//                    maxArea = area
//                }
//            }
//        }


        return "TODO"
    }
}

fun main() {
    val path = "src/main/inputs/day09.in"
    val day = Day09(path)
    day.printSolution()
}
