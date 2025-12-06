import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day06Test {
    @Test
    fun `test example part1`() {
        val path = "src/test/inputs/day06_example.in"
        val day = Day06(path)
        val res = day.solvePartOne(day.readInput())
        assertEquals("4277556", res)
    }

    @Test
    fun `test example part2`() {
        val path = "src/test/inputs/day06_example.in"
        val day = Day06(path)
        val res = day.solvePartTwo(day.readInput())
        assertEquals("3263827", res)
    }
}
