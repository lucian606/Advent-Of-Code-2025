import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day01Test {

    @Test
    fun `rotate to left should subtract position`() {
        val path = "src/test/inputs/day01_example.in"
        val day = Day01(path)
        val res = day.rotateDial(50, 'L', 10)
        assertEquals(40, res)
    }

    @Test
    fun `rotate to right should add position`() {
        val path = "src/test/inputs/day01_example.in"
        val day = Day01(path)
        val res = day.rotateDial(50, 'R', 10)
        assertEquals(60, res)
    }

    @Test
    fun `rotate to right over max should reset position`() {
        val path = "src/test/inputs/day01_example.in"
        val day = Day01(path)
        val res = day.rotateDial(99, 'R', 1)
        assertEquals(0, res)
    }

    @Test
    fun `rotate to left from zero should set position to max`() {
        val path = "src/test/inputs/day01_example.in"
        val day = Day01(path)
        val res = day.rotateDial(0, 'L', 1)
        println(-1 % 99)
        assertEquals( 99, res)
    }

    @Test
    fun `rotate to left by 100 should keep the same position`() {
        val path = "src/test/inputs/day01_example.in"
        val day = Day01(path)
        val res = day.rotateDial(20, 'L', 100)
        assertEquals( 20, res)
    }

    @Test
    fun `rotate to right by 100 should keep the same position`() {
        val path = "src/test/inputs/day01_example.in"
        val day = Day01(path)
        val res = day.rotateDial(20, 'R', 100)
        assertEquals( 20, res)
    }

    @Test
    fun `rotate to left by 220 should set position to zero`() {
        val path = "src/test/inputs/day01_example.in"
        val day = Day01(path)
        val res = day.rotateDial(20, 'L', 220)
        assertEquals( 0, res)
    }

    @Test
    fun `test example part1`() {
        val path = "src/test/inputs/day01_example.in"
        val day = Day01(path)
        val res = day.solvePartOne(day.readInput())
        assertEquals("3", res)
    }

    @Test
    fun `test example part2`() {
        val path = "src/test/inputs/day01_example.in"
        val day = Day01(path)
        val res = day.solvePartTwo(day.readInput())
        assertEquals("6", res)
    }
}