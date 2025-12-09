import java.util.PriorityQueue
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

class Day09(filePath: String) : DaySolver(filePath) {
    private data class Cell(
        val i: Int,
        val j: Int
    )

    private fun getRedTiles(input: List<String>): List<Cell> {
        val tiles = mutableListOf<Cell>()

        for (line in input) {
            val coordinates = line.split(',').map { it.toInt() }
            tiles.add(Cell(coordinates[0], coordinates[1]))
        }

        return tiles
    }

    private fun getRectangleArea(firstCorner: Cell, secondCorner: Cell): Long {
        return (abs(firstCorner.i - secondCorner.i) + 1L) * (abs(firstCorner.j - secondCorner.j) + 1L)
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
        return ""
    }
}

fun main() {
    val path = "src/main/inputs/day09.in"
    val day = Day09(path)
    day.printSolution()
}
