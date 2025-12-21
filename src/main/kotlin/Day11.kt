import java.util.*

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

    private fun getPathsFromSourceToDest(graph: Map<String, Set<String>>, from: String, destination: String, canVisit: Map<String, Set<String>> = emptyMap()): Int {
        val queue: Queue<Pair<String, MutableSet<String>>> = LinkedList()
        var paths = 0
        queue.add(Pair(from, mutableSetOf()))

        while (queue.isNotEmpty()) {
            val element = queue.poll()
            val vertex = element.first
            val visited = element.second

            if (vertex in visited) {
                continue
            }

            if (vertex == destination) {
                paths++
                continue
            }

            visited.add(vertex)

            for (neighbour in graph[vertex]!!) {
                if (neighbour !in visited) {
                    var shouldVisit = true
                    for (mustVisit in canVisit.keys) {
                        if (neighbour in canVisit.keys) {
                            break
                        }

                        if (neighbour !in canVisit[mustVisit]!! && mustVisit !in visited) {
                            shouldVisit = false
                        }
                    }

                    if (shouldVisit) {
                        val visitedCopy = mutableSetOf<String>()
                        visitedCopy.addAll(visited)
                        queue.add(Pair(neighbour, visitedCopy))
                    }
                }
            }
        }

        return paths
    }

    private fun canGetFromToDest(graph: Map<String, Set<String>>, from: String, destination: String): Boolean {
        val stack: LinkedList<String> = LinkedList()
        val visited: MutableSet<String> = mutableSetOf()
        stack.add(from)

        while (stack.isNotEmpty()) {
            val vertex = stack.removeLast()

            if (vertex in visited) {
                continue
            }

            if (vertex == destination) {
                return true
            }

            visited.add(vertex)

            if (vertex !in graph) {
                continue
            }

            for (neighbour in graph[vertex]!!) {
                if (neighbour !in visited) {
                    stack.add(neighbour)
                }
            }
        }

        return false
    }


    override fun solvePartOne(input: List<String>): String {
        val graph = buildGraph(input)
        return getPathsFromSourceToDest(graph, "you", "out").toString()
    }

    override fun solvePartTwo(input: List<String>): String {
        val graph = buildGraph(input)
        val canVisitDac = mutableSetOf<String>()
        val canVisitFft = mutableSetOf<String>()
        val canVisitOut = mutableSetOf<String>()
        for (vertex in graph.keys) {
            if (canGetFromToDest(graph, vertex, "dac")) {
                canVisitDac.add(vertex)
            }
            if (canGetFromToDest(graph, vertex, "fft")) {
                canVisitFft.add(vertex)
            }
            if (canGetFromToDest(graph, vertex, "out")) {
                canVisitOut.add(vertex)
            }
        }


//        val svrTofft = getPathsFromSourceToDest(graph, "svr", "fft", mutableMapOf("fft" to canVisitFft))
        val filteredGraph = mutableMapOf<String, Set<String>>()
        for (key in graph.keys) {
            if (key !in canVisitDac) {
                continue
            }
            val filteredNeighbors = graph[key]!!.filter { neighbor ->
                canGetFromToDest(graph, neighbor, "dac")
            }.toSet()
            filteredGraph[key] = filteredNeighbors
        }
        val fftTodac = getPathsFromSourceToDest(filteredGraph, "fft", "dac")
//        val dacTofft = getPathsFromSourceToDest(graph, "dac", "fft", mutableMapOf("fft" to canVisitFft))

        println(canGetFromToDest(graph, "fft", "dac"))
        return "ree"
    }
}

fun main() {
    val path = "src/main/inputs/day11.in"
    val day = Day11(path)
    day.printSolution()
}
