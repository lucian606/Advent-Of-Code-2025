import io.data2viz.geojson.Line
import kotlin.math.abs
import io.data2viz.geojson.Polygon
import java.util.LinkedList
import java.util.Queue

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

    private fun getPathsFromSourceToDest(graph: Map<String, Set<String>>, from: String, destination: String): Int {
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
                    val visitedCopy = mutableSetOf<String>()
                    visitedCopy.addAll(visited)
                    queue.add(Pair(neighbour, visitedCopy))
                }
            }
        }

        return paths
    }

    override fun solvePartOne(input: List<String>): String {
        val graph = buildGraph(input)
        return getPathsFromSourceToDest(graph, "you", "out").toString()
    }

    override fun solvePartTwo(input: List<String>): String {
        return ""
    }
}

fun main() {
    val path = "src/main/inputs/day11.in"
    val day = Day09(path)
    day.printSolution()
}
