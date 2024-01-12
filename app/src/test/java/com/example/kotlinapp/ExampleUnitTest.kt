import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import com.example.kotlinapp.model.CalculatorModel
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull

class ExampleUnitTest {
    private lateinit var model: CalculatorModel

    @Before
    fun setUp() {
        model = CalculatorModel()
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testStandardIntegerFormat() {
        assertNotNull(model.parseEquation("3x+2y=16"))
    }

    @Test
    fun testNegativeCoefficients() {
        assertNotNull(model.parseEquation("-3x-4y=10"))
    }

    @Test
    fun testFloatingPointCoefficients() {
        assertNotNull(model.parseEquation("3.5x+2.2y=7.8"))
    }

    @Test
    fun testMissingCoefficients() {
        assertNotNull(model.parseEquation("x+y=3")) // Should interpret as 1x+1y=3
    }

    @Test
    fun testSingleVariable() {
        assertNotNull(model.parseEquation("3x=9")) // Only x present
        assertNotNull(model.parseEquation("4y=12")) // Only y present
    }

    @Test
    fun testInvalidFormat() {
        assertNull(model.parseEquation("This is not an equation"))
        assertNull(model.parseEquation("3x + 2y")) // Missing equals and constant
    }

    @Test
    fun testEdgeCases() {
        assertNotNull(model.parseEquation("1000000x+1000000y=2000000")) // Large numbers
        assertNotNull(model.parseEquation(" 3x + 2y = 5 ")) // Spaces in equation
        assertNull(model.parseEquation("x3+2y=16")) // Invalid coefficient placement
    }
    @Test
    fun parseEquation_isCorrect() {
        val equation = "3x+2y=16"
        val expected = Triple(3.0, 2.0, 16.0)
        assertEquals(expected, model.parseEquation(equation))
    }

    @Test
    fun parseEquation_standardCase() {
        val equation = "3x+2y=16"
        val expected = Triple(3.0, 2.0, 16.0)
        assertEquals(expected, model.parseEquation(equation))
    }

    @Test
    fun parseEquation_negativeCoefficients() {
        val equation = "-3x-4y=10"
        val expected = Triple(-3.0, -4.0, 10.0)
        assertEquals(expected, model.parseEquation(equation))
    }

    @Test
    fun parseEquation_zeroCoefficients() {
        val equation = "0x+5y=15"
        val expected = Triple(0.0, 5.0, 15.0)
        assertEquals(expected, model.parseEquation(equation))
    }

    @Test
    fun parseEquation_missingTerm() {
        val equation = "5y=20"
        val expected = Triple(0.0, 5.0, 20.0) // Assuming your model treats missing x term as 0x
        assertEquals(expected, model.parseEquation(equation))
    }

    @Test
    fun parseEquation_malformedEquation() {
        val equation = "3x+2y"
        assertNull(model.parseEquation(equation)) // Expecting an exception due to malformed equation
    }

    @Test
    fun parseEquation_floatingPointCoefficients() {
        val equation = "3.5x+2.2y=7.8"
        val expected = Triple(3.5, 2.2, 7.8)
        assertEquals(expected, model.parseEquation(equation))
    }

    @Test
    fun parseEquation_largeCoefficients() {
        val equation = "3000x+2000y=16000"
        val expected = Triple(3000.0, 2000.0, 16000.0)
        assertEquals(expected, model.parseEquation(equation))
    }


    @Test
    fun solveSystem_isCorrect() {
        val coefficients1 = Triple(3.0, 2.0, 16.0)
        val coefficients2 = Triple(1.0, 1.0, 8.0)
        val expected = Pair(0.0, 8.0)
        assertEquals(expected, model.solveSystem(coefficients1, coefficients2))
    }

    @Test
    fun solveSystem_dependentSystem() {
        val coefficients1 = Triple(2.0, 3.0, 6.0)
        val coefficients2 = Triple(4.0, 6.0, 12.0)
        val expected: Pair<Double, Double>? = null
        assertEquals(expected, model.solveSystem(coefficients1, coefficients2))
    }

    @Test
    fun solveSystem_inconsistentSystem() {
        val coefficients1 = Triple(1.0, 1.0, 2.0)
        val coefficients2 = Triple(1.0, 1.0, 3.0)
        val expected: Pair<Double, Double>? = null
        assertEquals(expected, model.solveSystem(coefficients1, coefficients2))
    }

    @Test
    fun solveSystem_zeroCoefficients() {
        val coefficients1 = Triple(0.0, 2.0, 4.0)
        val coefficients2 = Triple(1.0, 3.0, 7.0)
        val expected = Pair(1.0, 2.0)
        assertEquals(expected, model.solveSystem(coefficients1, coefficients2))
    }


    @Test
    fun solveSystem_negativeCoefficients() {
        val coefficients1 = Triple(-2.0, 4.0, -6.0)
        val coefficients2 = Triple(1.0, -2.0, 3.0)
        val expected: Pair<Double, Double>? = null // Expect null due to dependent system
        assertEquals(expected, model.solveSystem(coefficients1, coefficients2))
    }


    @Test
    fun solveSystem_largeCoefficients() {
        val coefficients1 = Triple(1000000.0, 2000000.0, 3000000.0)
        val coefficients2 = Triple(4000000.0, 5000000.0, 6000000.0)
        val expected = Pair(-1.0, 2.0) // Updated expected solution based on actual calculation
        assertEquals(expected, model.solveSystem(coefficients1, coefficients2))
    }


    @Test
    fun solveSystem_floatingPointCoefficients() {
        val coefficients1 = Triple(3.5, 2.2, 16.8)
        val coefficients2 = Triple(1.1, 4.4, 8.8)
        val expectedX = 4.203
        val expectedY = 0.949
        val delta = 0.001 // Tolerance for the difference

        val result = model.solveSystem(coefficients1, coefficients2)

        assertNotNull(result)
        assertEquals(expectedX, result!!.first, delta)
        assertEquals(expectedY, result.second, delta)
    }


    @Test
    fun solveSystem_zeroConstants() {
        val coefficients1 = Triple(1.0, 2.0, 0.0)
        val coefficients2 = Triple(3.0, 4.0, 0.0)
        val result = model.solveSystem(coefficients1, coefficients2)
        val expected = Pair(0.0, 0.0)

        assertNotNull(result)
        assertEquals(expected.first, Math.abs(result!!.first), 0.0)
        assertEquals(expected.second, Math.abs(result.second), 0.0)
    }




}
