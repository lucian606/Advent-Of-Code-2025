import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day02Test {
    @Test
    fun `test example part1`() {
        val path = "src/test/inputs/day02_example.in"
        val day = Day02(path)
        val res = day.solvePartOne(day.readInput())
        assertEquals("1227775554", res)
    }

    @Test
    fun `test example part2`() {
        val path = "src/test/inputs/day02_example.in"
        val day = Day02(path)
        val res = day.solvePartTwo(day.readInput())
        assertEquals("4174379265", res)
    }
}