class Day12(filePath: String) : DaySolver(filePath) {
    override fun solvePartOne(input: List<String>): String {
        val sizes = mutableListOf<Int>()
        var valid = 0
        var currentSize = 0
        for (line in input) {
            if (line.contains("x")) {
                val parts = line.split(": ")
                val size = parts[0].split("x").map {it.toInt()}
                val totalArea = size[0] * size[1]
                var giftsArea = 0
                val giftCounts = parts[1].split(" ").map { it.toInt() }
                for (i in giftCounts.indices) {
                    giftsArea += (sizes[i] * giftCounts[i])
                }

                if (giftsArea <= totalArea) {
                    valid++
                }
            } else if (line.contains('#')){
                for (char in line) {
                    if (char == '#') {
                        currentSize++
                    }
                }
            } else if (line.contains(":")) {
                currentSize = 0
            } else if (line.isBlank()) {
                sizes.add(currentSize)
            }
        }

        return valid.toString()
    }

    override fun solvePartTwo(input: List<String>): String {
        return "GG, WP!"
    }
}

fun main() {
    val path = "src/main/inputs/day12.in"
    val day = Day12(path)
    day.printSolution()
}
