class Day01(filePath: String) : DaySolver(filePath) {

    data class ClickCounter(var clicks: Int)

    fun rotateDial(pos: Int, direction: Char, offset: Int): Int {
        val newOffset = offset % 100

        return when (direction) {
            'R' -> (pos + newOffset) % 100
            'L' -> {
                val result = (pos - newOffset)
                if (result < 0) {
                    100 + result
                } else {
                    result
                }
            }
            else -> throw IllegalArgumentException("Invalid direction: $direction")
        }
    }

    fun rotateDialWithClicks(pos: Int, direction: Char, offset: Int, clickCounter: ClickCounter): Int {
        var result = pos

        return when (direction) {
            'R' -> {
                for (i in 1..offset) {
                    result++
                    result %= 100
                    if (result == 0) {
                        clickCounter.clicks++
                    }
                }

                result
            }


            'L' -> {
                for (i in 1..offset) {
                    result--

                    if (result == -1)
                        result = 99

                    if (result == 0) {
                        clickCounter.clicks++
                    }
                }

                result
            }
            else -> throw IllegalArgumentException("Invalid direction: $direction")
        }
    }

    override fun solvePartOne(input: List<String>): String {
        var pos = 50
        var resets = 0
        for(line in input) {
            val direction = line[0]
            val offset = line.split(direction)[1].toInt()
            pos = rotateDial(pos, direction, offset)
            if (pos == 0) {
                resets++
            }
        }
        return resets.toString()
    }

    override fun solvePartTwo(input: List<String>): String {
        var pos = 50
        val clickCounter = ClickCounter(0)
        for(line in input) {
            val direction = line[0]
            val offset = line.split(direction)[1].toInt()
            pos = rotateDialWithClicks(pos, direction, offset, clickCounter)
        }
        return clickCounter.clicks.toString()
    }
}

fun main() {
    val path = "src/main/inputs/day01.in"
    val day = Day01(path)
    day.printSolution()
}