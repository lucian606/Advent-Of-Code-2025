class Day07(filePath: String) : DaySolver(filePath) {

    private data class Cell(
        val row: Int,
        val col: Int
    )

    private fun getStartPos(input: List<String>): Cell {
        for (row in input.indices) {
            for (col in input[0].indices) {
                if (input[row][col] == 'S') {
                    return Cell(row, col)
                }
            }
        }

        return Cell(-1, -1)
    }

    override fun solvePartOne(input: List<String>): String {
        val startPos = getStartPos(input)
        var beams = mutableSetOf<Cell>()
        val rowsCount = input.size

        beams.add(startPos)

        var splits = 0

        while (beams.isNotEmpty()) {
            val newBeams = mutableSetOf<Cell>()

            for (beam in beams) {
                val row = beam.row
                val col = beam.col

                if (input[row + 1][col] == '^') {
                    newBeams.add(Cell(row + 1, col + 1))
                    newBeams.add(Cell(row + 1, col - 1))
                    splits += 1
                } else if (row + 1 != rowsCount - 1){
                    newBeams.add(Cell(row + 1, col))
                }

                beams = newBeams
            }
        }

        return splits.toString()
    }

    override fun solvePartTwo(input: List<String>): String {
        val startPos = getStartPos(input)
        var beams = mutableSetOf<Cell>()
        val timelinesMap = mutableMapOf<Cell, Long>()
        val rowsCount = input.size

        beams.add(startPos)

        var timelines = 0L

        while (beams.isNotEmpty()) {
            val newBeams = mutableSetOf<Cell>()

            for (beam in beams) {
                val row = beam.row
                val col = beam.col

                if (input[row + 1][col] == '^') {
                    val firstHalf = Cell(row + 1, col + 1)
                    val secondHalf = Cell(row + 1, col - 1)
                    newBeams.add(firstHalf)
                    newBeams.add(secondHalf)
                    timelinesMap[firstHalf] = timelinesMap.getOrDefault(firstHalf, 0) + timelinesMap.getOrDefault(beam, 1)
                    timelinesMap[secondHalf] = timelinesMap.getOrDefault(secondHalf, 0) + timelinesMap.getOrDefault(beam, 1)
                } else if (row + 1 != rowsCount - 1) {
                    val newBeam = Cell(row + 1, col)
                    newBeams.add(newBeam)
                    timelinesMap[newBeam] = timelinesMap.getOrDefault(newBeam, 0) + timelinesMap.getOrDefault(beam, 1)
                } else {
                    timelines += timelinesMap[beam]!!
                }

                beams = newBeams
            }
        }

        return timelines.toString()
    }
}

fun main() {
    val path = "src/main/inputs/day07.in"
    val day = Day07(path)
    day.printSolution()
}