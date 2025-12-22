import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day10Test {
    @Test
    fun `test example part1`() {
        val path = "src/test/inputs/day10_example.in"
        val day = Day10(path)
        val res = day.solvePartOne(day.readInput())
        assertEquals("7", res)
    }

    @Test
    fun `test example part2`() {
        val path = "src/test/inputs/day10_example.in"
        val day = Day10(path)
        val res = day.solvePartTwo(day.readInput())
        assertEquals("33", res)
    }
}
