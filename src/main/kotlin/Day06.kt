class Day06(filePath: String) : DaySolver(filePath) {

    override fun solvePartOne(input: List<String>): String {

        val numbers = input.dropLast(1).map { convertStringToNumberList(it) }
        val operations = input.last().split(" ").filter { it.isNotBlank() }

        var total = 0L

        for (i in numbers[0].indices) {
            when (operations[i]) {
                "+" -> {
                    total += numbers.sumOf { it[i] }
                }
                "*" -> {
                    total += numbers.map { it[i] }.reduce{acc, num -> acc * num}
                }
            }
        }

        return total.toString()
    }

    override fun solvePartTwo(input: List<String>): String {
        val numberLines = input.dropLast(1)
        val operations = input.last().split(" ").filter { it.isNotBlank() }

        var currentOperation = operations.size - 1
        val currentNumbers = mutableListOf<Long>()

        var total = 0L

        for (i in numberLines[0].indices.reversed()) {
            if (numberLines.map { it[i] }.all { it.isWhitespace() }) {
                when (operations[currentOperation]) {
                    "+" -> {
                        total += currentNumbers.sum()
                    }
                    "*" -> {
                        total += currentNumbers.reduce{acc, num -> acc * num}
                    }
                }
                currentOperation--
                currentNumbers.clear()
                continue
            }

            var number = 0L
            for (line in numberLines) {
                if (line[i].isDigit()) {
                    number = number * 10 + line[i].digitToInt()
                }

            }

            currentNumbers.add(number)

            if (i == 0) {
                when (operations[currentOperation]) {
                    "+" -> {
                        total += currentNumbers.sum()
                    }
                    "*" -> {
                        total += currentNumbers.reduce{acc, num -> acc * num}
                    }
                }
            }
        }

        return total.toString()
    }
}

fun main() {
    val path = "src/main/inputs/day06.in"
    val day = Day06(path)
    day.printSolution()
}