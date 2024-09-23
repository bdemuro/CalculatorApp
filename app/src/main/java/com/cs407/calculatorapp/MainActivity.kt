package com.cs407.calculatorapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val etNumber1: EditText = findViewById(R.id.Number1)
        val etNumber2: EditText = findViewById(R.id.Number2)
        val add: Button = findViewById(R.id.add_button)
        val subtract: Button = findViewById(R.id.subtract_button)
        val multiply: Button = findViewById(R.id.multiply_button)
        val divide: Button = findViewById(R.id.divide_button)


        fun calculate(operation: String) {
            val num1Str = etNumber1.text.toString()
            val num2Str = etNumber2.text.toString()
            val num1 = num1Str.toInt()
            val num2 = num2Str.toInt()

            val result = when (operation) {
                "add" -> num1 + num2
                "subtract" -> num1 - num2
                "multiply" -> num1 * num2
                "divide" -> {
                    if (num2 == 0) {
                        Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show()
                        return
                    }
                    num1 / num2
                }
                else -> 0
            }


            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("RESULT", result.toString())
            startActivity(intent)
        }

        add.setOnClickListener { calculate("add") }
        subtract.setOnClickListener { calculate("subtract") }
        multiply.setOnClickListener { calculate("multiply") }
        divide.setOnClickListener { calculate("divide") }
    }
}




