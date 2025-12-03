class Day03(filePath: String) : DaySolver(filePath) {

    override fun solvePartOne(input: List<String>): String {
        var sum = 0
        for (line in input) {
            var max = 0
            for (i in 0 until line.length) {
                for (j in i + 1 until line.length) {
                    val power = line[i].digitToInt() * 10 +  line[j].digitToInt()
                    if (power > max) {
                        max = power
                    }
                }
            }

            sum += max
        }
        return sum.toString()
    }

    override fun solvePartTwo(input: List<String>): String {
        var sum = 0L
        for (line in input) {
            val start = line.length - 12
            var max = line.slice(start until line.length).toLong()

            for (i in start - 1 downTo 0) {

                val maxStr = max.toString()
                val firstDigit = maxStr[0].digitToInt()
                val digitToAdd = line[i].digitToInt()

                if (digitToAdd >= firstDigit) {
                    var num = digitToAdd.toString() + maxStr

                    for (k in 1 until num.length) {
                        var tmp = num.slice(0 until k) + num.slice(k + 1 until num.length)
                        if (tmp.toLong() > max)
                            max = tmp.toLong()
                    }
                }
            }

            sum += max
        }
        return sum.toString()
    }
}

fun main() {
    val path = "src/main/inputs/day03.in"
    val day = Day03(path)
    day.printSolution()
}