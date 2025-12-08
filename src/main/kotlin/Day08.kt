import java.util.PriorityQueue
import kotlin.math.pow
import kotlin.math.sqrt

class Day08(filePath: String) : DaySolver(filePath) {

    private data class Box(
        val x: Int,
        val y: Int,
        val z: Int
    )

    private data class Edge(
        val a: Box,
        val b: Box,
        val length: Int
    )

    private fun getDistanceBetweenBoxes(b1: Box, b2: Box): Int = sqrt(
        (b1.x - b2.x).toDouble().pow(2.0) + (b1.y - b2.y).toDouble().pow(2.0) + (b1.z - b2.z).toDouble().pow(2.0)
    ).toInt()

    private fun getBoxes(input: List<String>): List<Box> {
        val boxes = mutableListOf<Box>()

        for (line in input) {
            val coordinates = line.split(',').map { it.toInt() }
            boxes.add(Box(coordinates[0], coordinates[1], coordinates[2]))
        }

        return boxes
    }

    private fun getEdges(boxes: List<Box>): PriorityQueue<Edge> {
        val edges = PriorityQueue<Edge> { b1, b2 -> (b1.length - b2.length) }

        for (i in boxes.indices) {
            for (j in i + 1 until boxes.size) {
                val distance = getDistanceBetweenBoxes(boxes[i], boxes[j])
                edges.add(Edge(boxes[i], boxes[j], distance))
            }
        }

        return edges
    }

    private fun connectTheClosestBoxes(
        boxes: List<Box>,
        edges: PriorityQueue<Edge>,
        graphs: MutableMap<Box, MutableSet<Box>>
    ) {
        val edge = edges.poll()

        graphs[edge.a]!!.addAll(graphs[edge.b]!!)
        graphs[edge.b] = graphs[edge.a]!!

        for (box in boxes) {
            if (box != edge.a && box != edge.b) {
                if (edge.a in graphs[box]!!) {
                    graphs[box] = graphs[edge.a]!!
                }
                if (edge.b in graphs[box]!!) {
                    graphs[box] = graphs[edge.b]!!
                }
            }
        }
    }

    fun solvePartOne(input: List<String>, capacity: Int): String {
        val boxes = getBoxes(input)
        val edges = getEdges(boxes)

        var polledElements = 0

        val graphs = mutableMapOf<Box, MutableSet<Box>>()
        for (box in boxes) {
            graphs[box] = mutableSetOf(box)
        }

        while (polledElements < capacity) {
            connectTheClosestBoxes(boxes, edges, graphs)
            polledElements++
        }

        return graphs.values.toSet().map { it.size }.sortedDescending().take(3).reduce{ acc, num -> acc * num }.toString()
    }

    override fun solvePartOne(input: List<String>): String {
        return solvePartOne(input, 1000)
    }

    override fun solvePartTwo(input: List<String>): String {
        val boxes = getBoxes(input)
        val edges = getEdges(boxes)

        val graphs = mutableMapOf<Box, MutableSet<Box>>()
        for (box in boxes) {
            graphs[box] = mutableSetOf(box)
        }

        while (edges.isNotEmpty()) {
            val edgeToConnect = edges.peek()
            connectTheClosestBoxes(boxes, edges, graphs)

            if (graphs[edgeToConnect.a]?.size == boxes.size) {
                return (edgeToConnect.a.x * edgeToConnect.b.x).toString()
            }
        }

        return "Error connecting graph"
    }
}

fun main() {
    val path = "src/main/inputs/day08.in"
    val day = Day08(path)
    day.printSolution()
}