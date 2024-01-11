package com.example.kotlinapp.controller

import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinapp.R
import com.example.kotlinapp.model.CalculatorModel
import org.w3c.dom.Text

class CalculatorController(private val activity: AppCompatActivity, private val model: CalculatorModel) {

    private val display: TextView = activity.findViewById(R.id.display1);
    private val display2: TextView = activity.findViewById(R.id.display2);
    private val display3: TextView = activity.findViewById(R.id.display3);

    init {
        setUpButtonListeners()
    }

    private fun setUpButtonListeners() {
        // Configuración de los listeners para los botones numéricos y de operación
        val buttons = listOf(
            R.id.button1, R.id.button2, R.id.button3,
            R.id.button4, R.id.button5, R.id.button6,
            R.id.button7, R.id.button8, R.id.button9,
            R.id.button_x, R.id.button_y, R.id.button_plus, R.id.button_minus, R.id.button_multiply
        )

        buttons.forEach { buttonId ->
            val button: Button = activity.findViewById(buttonId)
            button.setOnClickListener {
                val buttonText = button.text.toString()
                model.appendToCurrentOperation(buttonText)
                updateDisplay()
            }
        }

        // Listener for the swap button
        val buttonSwap: Button = activity.findViewById(R.id.button_arrows)
        buttonSwap.setOnClickListener {
            model.swapTextView()
            updateDisplay()
        }

        // Listener for the clear button
        val buttonClear: Button = activity.findViewById(R.id.button_clear)
        buttonClear.setOnClickListener {
            model.clear()
            updateDisplay()
        }

        val buttonResult: Button = activity.findViewById(R.id.button_result)
        buttonResult.setOnClickListener {
            model.calculate()
            updateDisplay()
        }
    }

    private fun updateDisplay() {
        // Update the active display with the current operation
        if (model.isPrimaryActive) {
            display.text = model.getCurrentOperation()
        } else {
            display2.text = model.getCurrentOperation()
        }

        // Always update display3 with the result
        display3.text = model.resultOperation
    }

}
