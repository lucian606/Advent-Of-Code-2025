import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day11Test {
    @Test
    fun `test example part1`() {
        val path = "src/test/inputs/day11_example.in"
        val day = Day11(path)
        val res = day.solvePartOne(day.readInput())
        assertEquals("5", res)
    }

    @Test
    fun `test example part2`() {
        val path = "src/test/inputs/day11_example.in"
        val day = Day11(path)
        val res = day.solvePartTwo(day.readInput())
        assertEquals("24", res)
    }
}
