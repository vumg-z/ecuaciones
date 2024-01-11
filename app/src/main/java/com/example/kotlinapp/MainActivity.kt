package com.example.kotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinapp.controller.CalculatorController
import com.example.kotlinapp.model.CalculatorModel

class MainActivity : AppCompatActivity() {
    private lateinit var calculatorModel: CalculatorModel
    private lateinit var calculatorController: CalculatorController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calculatorModel = CalculatorModel()
        calculatorController = CalculatorController(this, calculatorModel)
    }
}
