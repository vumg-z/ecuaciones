import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import com.example.kotlinapp.model.CalculatorModel

class ExampleUnitTest {
    companion object {
        private lateinit var model: CalculatorModel

        @BeforeAll
        @JvmStatic
        fun setUp() {
            // Initialize your CalculatorModel here
            model = CalculatorModel()
        }
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun parseEquation_isCorrect() {
        val equation = "3x+2y=16"
        val expected = Triple(3.0, 2.0, 16.0)
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
    fun myTest() {
        println("Starting myTest...")
        // Perform test steps
        println("Test step 1 completed...")
        // More test steps
        println("Test step 2 completed...")
        // Assertions and further steps
        println("Test finished.")
    }

}
