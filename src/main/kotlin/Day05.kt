import kotlin.math.max


class Day05(filePath: String) : DaySolver(filePath) {

    override fun solvePartOne(input: List<String>): String {

        val ranges = mutableListOf<LongRange>()
        var parseRanges = true
        var freshCount = 0

        for (line in input) {
            if (line.isEmpty()) {
                parseRanges = false
                continue
            }

            if (parseRanges) {
                val intervalPieces = line.split('-')
                ranges.add(LongRange(intervalPieces[0].toLong(), intervalPieces[1].toLong()))
            } else {
                val ingredient = line.toLong()
                for (range in ranges) {
                    if (ingredient in range) {
                        freshCount++
                        break
                    }
                }
            }
        }

        return freshCount.toString()
    }

    override fun solvePartTwo(input: List<String>): String {
        val ranges = mutableListOf<LongRange>()

        for (line in input) {
            if (line.isEmpty()) {
                break
            }

            val intervalPieces = line.split('-')
            ranges.add(LongRange(intervalPieces[0].toLong(), intervalPieces[1].toLong()))
        }

        ranges.sortBy { it.first }

        var compressedRanges = ranges

        while (true) {

            val usedInCompression = mutableSetOf<LongRange>()
            val newRanges = mutableListOf<LongRange>()
            var compressionDone = false

            for (i in compressedRanges.indices) {
                for (j in i + 1 until compressedRanges.size) {
                    if (compressedRanges[j] !in usedInCompression) {

                        if (compressedRanges[i].last >= compressedRanges[j].first) {

                            usedInCompression.add(compressedRanges[i])
                            usedInCompression.add(compressedRanges[j])

                            newRanges.add(
                                LongRange(
                                    compressedRanges[i].first,
                                    max(compressedRanges[i].last, compressedRanges[j].last)
                                )
                            )

                            compressionDone = true
                            break
                        }
                    }
                }
            }

            if (!compressionDone)
                break

            newRanges.addAll(compressedRanges.filter { it !in usedInCompression })
            compressedRanges = newRanges
            compressedRanges.sortBy { it.first }
        }

        return compressedRanges.map { it.last - it.first + 1}.sum().toString()
    }
}

fun main() {
    val path = "src/main/inputs/day05.in"
    val day = Day05(path)
    day.printSolution()
}