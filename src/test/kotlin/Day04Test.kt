import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day04Test {
    @Test
    fun `test example part1`() {
        val path = "src/test/inputs/day04_example.in"
        val day = Day04(path)
        val res = day.solvePartOne(day.readInput())
        assertEquals("13", res)
    }

    @Test
    fun `test example part2`() {
        val path = "src/test/inputs/day04_example.in"
        val day = Day04(path)
        val res = day.solvePartTwo(day.readInput())
        assertEquals("43", res)
    }
}
