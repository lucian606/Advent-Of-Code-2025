import java.util.*

class Day10(filePath: String) : DaySolver(filePath) {

    fun getLeastButtonPresses(lights: String, buttons: List<List<Int>>): Long {
        val expectedLights = lights.map {
            it == '#'
        }.toBooleanArray()

        val startingLights = BooleanArray(lights.length) { false }


        val partialAnswers: Queue<Pair<BooleanArray, Long>> = LinkedList()
        partialAnswers.add(Pair(startingLights, 0L))

        while (partialAnswers.isNotEmpty()) {
            val partialAnswer = partialAnswers.poll()
            val currentLights = partialAnswer.first
            val buttonsPressed = partialAnswer.second

            if (currentLights.contentEquals(expectedLights)) {
                return buttonsPressed
            }

            for (button in buttons) {
                val newAnswer = currentLights.copyOf()
                for (index in button) {
                    newAnswer[index] = !newAnswer[index]
                }

                partialAnswers.add(Pair(newAnswer, buttonsPressed + 1))
            }

        }

        return 0;
    }

    fun getLeastButtonPresses(joltages: List<Int>, buttons: List<List<Int>>): Long {
        val initialState = mutableListOf<Int>()
        for (i in joltages.indices) {
            initialState.add(0)
        }

        var currentState = mutableSetOf<List<Int>>()
        currentState.add(initialState)

        val maxIterations = 10000000

        for (buttonPress in 0L until maxIterations) {

            if (joltages in currentState) {
                return buttonPress
            }

            val newState = mutableSetOf<List<Int>>()

            for (currentJoltages in currentState) {
                for (button in buttons) {
                    val newJoltages = mutableListOf<Int>()
                    for (i in currentJoltages.indices) {
                        newJoltages.add(currentJoltages[i])
                    }

                    for (index in button) {
                        newJoltages[index]++
                    }

                    newState.add(newJoltages)
                }
            }

            currentState = newState
        }


        return 0
    }


    override fun solvePartOne(input: List<String>): String {
        var sum = 0L

        for (line in input) {
            val lineParts = line.split(" ")
            val buttons = lineParts.slice(1 until lineParts.size - 1).map {
                it.slice(1 until it.length - 1).split(",").map { num -> num.toInt() }
            }
            val lights = lineParts.first().slice(1 until lineParts.first().length - 1)

            sum += getLeastButtonPresses(lights, buttons)
        }
        return sum.toString()
    }

    override fun solvePartTwo(input: List<String>): String {
        var sum = 0L

        var idx = 0

        for (line in input) {
            val lineParts = line.split(" ")
            val buttons = lineParts.slice(1 until lineParts.size - 1).map {
                it.slice(1 until it.length - 1).split(",").map { num -> num.toInt() }
            }
            val joltages = lineParts
                .last()
                .slice(1 until lineParts.last().length - 1)
                .split(',')
                .map { it.toInt() }

            sum += getLeastButtonPresses(joltages, buttons)
            println("Done with line $idx")
        }
        return sum.toString()
    }
}

fun main() {
    val path = "src/main/inputs/day10.in"
    val day = Day10(path)
    day.printSolution()
}
