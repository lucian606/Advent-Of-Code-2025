import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day07Test {
    @Test
    fun `test example part1`() {
        val path = "src/test/inputs/day07_example.in"
        val day = Day07(path)
        val res = day.solvePartOne(day.readInput())
        assertEquals("21", res)
    }

    @Test
    fun `test example part2`() {
        val path = "src/test/inputs/day07_example.in"
        val day = Day07(path)
        val res = day.solvePartTwo(day.readInput())
        assertEquals("40", res)
    }
}
