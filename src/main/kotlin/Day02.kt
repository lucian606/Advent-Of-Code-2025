class Day02(filePath: String) : DaySolver(filePath) {

    private fun isIdInvalid(id: Long): Boolean {
        if (id < 10) {
            return false
        }
        val idStr = id.toString()

        return idStr.slice(0 until idStr.length / 2) == idStr.slice(idStr.length / 2 until  idStr.length)
    }

    private fun isIdInvalidPart2(id: Long): Boolean {
        if (id < 10) {
            return false
        }
        val idStr = id.toString()

        for (chunkSize in 1..idStr.length / 2) {
            val chunks = idStr.chunked(chunkSize)
            if (chunks.areAllElementsEqual()) {
                return true
            }

        }

        return false
    }

    override fun solvePartOne(input: List<String>): String {
        val intervals = input[0].split(",")
        val invalidIds = mutableSetOf<Long>()
        for (interval in intervals) {
            val intervalPieces = interval.split("-")
            val start = intervalPieces[0].toLong()
            val end = intervalPieces[1].toLong()


            for (num in start..end) {
                if (isIdInvalid(num)) {
                    invalidIds.add(num)
                }
            }

        }
        return invalidIds.sum().toString()
    }

    override fun solvePartTwo(input: List<String>): String {
        val intervals = input[0].split(",")
        val invalidIds = mutableSetOf<Long>()
        for (interval in intervals) {
            val intervalPieces = interval.split("-")
            val start = intervalPieces[0].toLong()
            val end = intervalPieces[1].toLong()


            for (num in start..end) {
                if (isIdInvalidPart2(num)) {
                    invalidIds.add(num)
                }
            }

        }
        return invalidIds.sum().toString()
    }

    private fun List<Any>.areAllElementsEqual() = this.all { it == this[0] }
}

fun main() {
    val path = "src/main/inputs/day02.in"
    val day = Day02(path)
    day.printSolution()
}