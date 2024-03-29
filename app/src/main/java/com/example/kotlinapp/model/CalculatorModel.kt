package com.example.kotlinapp.model

class CalculatorModel {

    private var currentOperation: StringBuilder = StringBuilder()
    private var secondaryOperation: StringBuilder = StringBuilder()
    var isPrimaryActive = true
    var resultOperation = "";


    fun appendToCurrentOperation(buttonText: String) {
        if (isPrimaryActive) {
            currentOperation.append(buttonText)
        } else {
            secondaryOperation.append(buttonText)
        }
    }


    fun calculate() {
        // Parse the equations
        val coefficients1 = parseEquation(currentOperation.toString())
        val coefficients2 = parseEquation(secondaryOperation.toString())

        // Check if parsing was successful
        if (coefficients1 != null && coefficients2 != null) {
            // Solve the system
            val solution = solveSystem(coefficients1, coefficients2)

            // Handle the solution
            if (solution != null) {
                // Update the result operation with the solution
                resultOperation = "x = ${solution.first}, y = ${solution.second}"
            } else {
                // Handle the case when there's no unique solution
                resultOperation = "No unique solution"
            }
        } else {
            // Handle parsing error
            resultOperation = "Error in input format"
        }
    }


    fun parseEquation(equation: String): Triple<Double, Double, Double>? {
        val pattern = Regex("\\s*([+-]?\\s*\\d*\\.?\\d*\\s*x)?\\s*([+-]?\\s*\\d*\\.?\\d*\\s*y)?\\s*=\\s*([+-]?\\s*\\d*\\.?\\d*)\\s*")
        val match = pattern.matchEntire(equation.trim())

        return if (match != null) {
            val (xTerm, yTerm, c) = match.destructured

            val aCoeff = when {
                xTerm.contains('x') -> if (xTerm.filter { it.isDigit() || it == '.' || it == '-' || it == '+' }.isEmpty()) 1.0 else xTerm.filter { it.isDigit() || it == '.' || it == '-' || it == '+' }.toDoubleOrNull() ?: 0.0
                else -> 0.0
            }
            val bCoeff = when {
                yTerm.contains('y') -> if (yTerm.filter { it.isDigit() || it == '.' || it == '-' || it == '+' }.isEmpty()) 1.0 else yTerm.filter { it.isDigit() || it == '.' || it == '-' || it == '+' }.toDoubleOrNull() ?: 0.0
                else -> 0.0
            }
            val cCoeff = c.filter { it.isDigit() || it == '.' || it == '-' || it == '+' }.toDoubleOrNull() ?: 0.0

            Triple(aCoeff, bCoeff, cCoeff)
        } else {
            null
        }
    }


    fun solveSystem(coefficients1: Triple<Double, Double, Double>, coefficients2: Triple<Double, Double, Double>): Pair<Double, Double>? {
        val (a1, b1, c1) = coefficients1
        val (a2, b2, c2) = coefficients2

        val determinant = a1 * b2 - a2 * b1
        if (determinant == 0.0) {
            // The system of equations is either inconsistent or has infinitely many solutions
            return null
        }

        val x = (c1 * b2 - c2 * b1) / determinant
        val y = (a1 * c2 - a2 * c1) / determinant

        return Pair(x, y)
    }


    fun clear() {
        if (isPrimaryActive) {
            currentOperation.clear()
        } else {
            secondaryOperation.clear()
        }
    }
    fun getCurrentOperation(): String {
        return if (isPrimaryActive) currentOperation.toString() else secondaryOperation.toString()
    }

    fun swapTextView() {
        isPrimaryActive = !isPrimaryActive
    }



}
