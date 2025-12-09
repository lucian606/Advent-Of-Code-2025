import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day09Test {
    @Test
    fun `test example part1`() {
        val path = "src/test/inputs/day09_example.in"
        val day = Day09(path)
        val res = day.solvePartOne(day.readInput())
        assertEquals("50", res)
    }

    @Test
    fun `test example part2`() {
        val path = "src/test/inputs/day09_example.in"
        val day = Day09(path)
        val res = day.solvePartTwo(day.readInput())
        assertEquals("25272", res)
    }
}
