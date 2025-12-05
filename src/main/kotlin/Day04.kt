class Day04(filePath: String) : DaySolver(filePath) {

    private fun getCellValue(grid: List<String>, row: Int, col: Int): Char {
        if (row !in grid.indices || col !in grid[0].indices) {
            return '.'
        }
        return grid[row][col]
    }

    private fun neighbourCount(grid: List<String>, row: Int, col: Int): Int {
        var neighbours = 0
        if (getCellValue(grid, row - 1, col) == '@') {
            neighbours++
        }
        if (getCellValue(grid, row - 1, col + 1) == '@') {
            neighbours++
        }
        if (getCellValue(grid, row, col + 1)  == '@') {
            neighbours++
        }
        if (getCellValue(grid, row + 1, col + 1)  == '@') {
            neighbours++
        }
        if (getCellValue(grid, row + 1, col) == '@') {
            neighbours++
        }
        if (getCellValue(grid, row + 1, col - 1) == '@') {
            neighbours++
        }
        if (getCellValue(grid, row, col - 1) == '@') {
            neighbours++
        }
        if (getCellValue(grid, row - 1, col - 1) == '@') {
            neighbours++
        }

        return neighbours
    }

    override fun solvePartOne(input: List<String>): String {
        var accessible = 0
        for (row in input.indices) {
            for (col in input[0].indices) {
                if (input[row][col] == '@' && neighbourCount(input, row, col) < 4) {
                    accessible++
                }
            }
        }
        return accessible.toString()
    }

    override fun solvePartTwo(input: List<String>): String {
        var accessible = 0
        val mutableGrid = input.toMutableList()
        while (true) {
            var removed = 0
            for (row in mutableGrid.indices) {
                for (col in mutableGrid[0].indices) {
                    if (mutableGrid[row][col] == '@' && neighbourCount(mutableGrid, row, col) < 4) {
                        removed++
                        mutableGrid[row] =
                            mutableGrid[row].slice(0 until col) +
                                "." +
                                mutableGrid[row].slice(col + 1 until mutableGrid[row].length)
                    }
                }
            }

            if (removed == 0) {
                break
            }

            accessible += removed
        }
        return accessible.toString()
    }
}

fun main() {
    val path = "src/main/inputs/day04.in"
    val day = Day04(path)
    day.printSolution()
}
