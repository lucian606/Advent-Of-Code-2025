class Day11(filePath: String) : DaySolver(filePath) {
    private fun buildGraph(input: List<String>): Map<String, Set<String>> {
        val graph = mutableMapOf<String, Set<String>>()

        for (line in input) {
            val lineParts = line.split(": ")
            val source = lineParts[0]
            val destinations = lineParts[1].split(" ").toSet()
            graph[source] = destinations
        }

        return graph
    }

    private fun getPathsFromSourceToDestRec(cache: MutableMap<String, Long>, graph: Map<String, Set<String>>, from: String, destination: String): Long {
        if (from == destination) {
            return 1L
        }

        if (cache.containsKey(from)) {
            return cache[from]!!
        }

        var sum = 0L
        for (neighbour in graph[from] ?: emptySet()) {
            sum += getPathsFromSourceToDestRec(cache, graph, neighbour, destination)
        }

        cache[from] = sum
        return sum
    }

    override fun solvePartOne(input: List<String>): String {
        val graph = buildGraph(input)
        return getPathsFromSourceToDestRec(mutableMapOf(), graph, "you", "out").toString()
    }

    override fun solvePartTwo(input: List<String>): String {
        val graph = buildGraph(input)
        val dacTofft = getPathsFromSourceToDestRec(mutableMapOf(), graph, "dac", "fft")

        if (dacTofft == 0L) {
            val svrTofft = getPathsFromSourceToDestRec(mutableMapOf(), graph, "svr", "fft")
            val fftTodac = getPathsFromSourceToDestRec(mutableMapOf(), graph, "fft", "dac")
            val dacToout = getPathsFromSourceToDestRec(mutableMapOf(), graph, "dac", "out")

            return (svrTofft * fftTodac * dacToout).toString()
        } else {
            val svrTodac = getPathsFromSourceToDestRec(mutableMapOf(), graph, "svr", "dac")
            val fftToout = getPathsFromSourceToDestRec(mutableMapOf(), graph, "fft", "out")
            return (svrTodac * dacTofft * fftToout).toString()
        }
    }
}

fun main() {
    val path = "src/main/inputs/day11.in"
    val day = Day11(path)
    day.printSolution()
}
