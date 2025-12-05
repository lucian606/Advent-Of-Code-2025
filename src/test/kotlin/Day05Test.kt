import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day05Test {
    @Test
    fun `test example part1`() {
        val path = "src/test/inputs/day05_example.in"
        val day = Day05(path)
        val res = day.solvePartOne(day.readInput())
        assertEquals("3", res)
    }

    @Test
    fun `test example part2`() {
        val path = "src/test/inputs/day05_example.in"
        val day = Day05(path)
        val res = day.solvePartTwo(day.readInput())
        assertEquals("14", res)
    }
}
