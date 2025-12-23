import java.util.*
import kotlin.math.min

class Day10(filePath: String) : DaySolver(filePath) {

    fun getLeastButtonPresses(lights: BooleanArray, buttons: List<List<Int>>): Long {
        val target = BitSet(lights.size)
        for (i in lights.indices) if (lights[i]) target.set(i)

        val start = BitSet(lights.size)

        val buttonBits = buttons.map { b ->
            val bs = BitSet(lights.size)
            for (idx in b) bs.set(idx)
            bs
        }

        val queue: ArrayDeque<Pair<BitSet, Long>> = ArrayDeque()
        val visited = HashSet<BitSet>()
        queue.addLast(Pair(start, 0L))
        visited.add(start)

        while (queue.isNotEmpty()) {
            val (current, steps) = queue.removeFirst()
            if (current == target) return steps

            for (btn in buttonBits) {
                val next = (current.clone() as BitSet)
                next.xor(btn)
                if (visited.add(next)) {
                    queue.addLast(Pair(next, steps + 1))
                }
            }
        }

        return 0
    }

    fun getAllPossibleButtonCombos(lights: BooleanArray, buttons: List<List<Int>>): Set<Set<BitSet>> {
        val target = BitSet(lights.size)
        for (i in lights.indices) if (lights[i]) target.set(i)

        val start = BitSet(lights.size)

        val buttonBits = buttons.map { b ->
            val bs = BitSet(lights.size)
            for (idx in b) bs.set(idx)
            bs
        }

        val queue: ArrayDeque<Pair<BitSet, BitSet>> = ArrayDeque() // state, used-button-indices
        val validCombos = mutableSetOf<Set<BitSet>>()

        val emptyUsed = BitSet(buttonBits.size)
        queue.addLast(Pair(start, emptyUsed))

        val visited = HashMap<BitSet, MutableSet<BitSet>>() // state -> set of used-patterns seen
        visited[start] = mutableSetOf(emptyUsed)

        var tryAgain = false
        if (target.isEmpty()) {
            tryAgain = true
        }

        while (queue.isNotEmpty()) {
            val (current, used) = queue.removeFirst()
            if (current == target) {
                val combo = mutableSetOf<BitSet>()
                var i = used.nextSetBit(0)
                while (i >= 0) {
                    combo.add(buttonBits[i])
                    i = used.nextSetBit(i + 1)
                }
                validCombos.add(combo)
                if (tryAgain) {
                    tryAgain = false
                } else {
                    continue
                }
            }

            for (i in buttonBits.indices) {
                if (used.get(i)) continue // don't reuse same button
                val nextState = (current.clone() as BitSet)
                nextState.xor(buttonBits[i])

                val nextUsed = (used.clone() as BitSet)
                nextUsed.set(i)

                val seen = visited[nextState]
                if (seen == null) {
                    visited[nextState.clone() as BitSet] = mutableSetOf(nextUsed)
                    queue.addLast(Pair(nextState, nextUsed))
                } else if (seen.add(nextUsed)) {
                    queue.addLast(Pair(nextState, nextUsed))
                }
            }
        }

        return validCombos
    }

    fun getLeastButtonPresses(joltages: List<Int>, buttons: List<List<Int>>, cache: MutableMap<BooleanArray, Set<Set<BitSet>>>): Long {
        if (joltages.all { it == 0 })
            return 0
        else {
            val ligths = joltages.map { it % 2 == 1}.toBooleanArray()
            var viableCombos: Set<Set<BitSet>>?
            if (ligths in cache)
                viableCombos = cache[ligths]!!
            else {
                viableCombos = getAllPossibleButtonCombos(ligths, buttons)
            }
            var minPresses = Int.MAX_VALUE + 1L
            for (combination in viableCombos) {
                val newJoltages = joltages.toMutableList()
                for (button in combination) {
                    var i = button.nextSetBit(0)
                    while (i >= 0) {
                        newJoltages[i] -= 1
                        i = button.nextSetBit(i + 1)
                    }
                }

                if (newJoltages.any { it < 0 }) {
                    continue
                }

                val presses = combination.size
                minPresses = min(minPresses, presses + 2 * getLeastButtonPresses(newJoltages.map { it / 2 }, buttons, cache))

            }

            return minPresses
        }
    }


    override fun solvePartOne(input: List<String>): String {
        var sum = 0L

        for (line in input) {
            val lineParts = line.split(" ")
            val buttons = lineParts.slice(1 until lineParts.size - 1).map {
                it.slice(1 until it.length - 1).split(",").map { num -> num.toInt() }
            }
            val lights = lineParts.first().slice(1 until lineParts.first().length - 1).map { it == '#' }.toBooleanArray()

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

            val res = getLeastButtonPresses(joltages, buttons, mutableMapOf())
            sum += res
        }
        return sum.toString()
    }
}

fun main() {
    val path = "src/main/inputs/day10.in"
    val day = Day10(path)
    day.printSolution()
}
