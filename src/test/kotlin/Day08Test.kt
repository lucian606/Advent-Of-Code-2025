import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day08Test {
    @Test
    fun `test example part1`() {
        val path = "src/test/inputs/day08_example.in"
        val day = Day08(path)
        val res = day.solvePartOne(day.readInput(), 10)
        assertEquals("40", res)
    }

    @Test
    fun `test example part2`() {
        val path = "src/test/inputs/day08_example.in"
        val day = Day08(path)
        val res = day.solvePartTwo(day.readInput())
        assertEquals("25272", res)
    }
}
