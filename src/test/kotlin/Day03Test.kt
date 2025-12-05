import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day03Test {
    @Test
    fun `test example part1`() {
        val path = "src/test/inputs/day03_example.in"
        val day = Day03(path)
        val res = day.solvePartOne(day.readInput())
        assertEquals("357", res)
    }

    @Test
    fun `test example part2`() {
        val path = "src/test/inputs/day03_example.in"
        val day = Day03(path)
        val res = day.solvePartTwo(day.readInput())
        assertEquals("3121910778619", res)
    }
}