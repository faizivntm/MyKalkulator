package com.example.mykalkulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.mykalkulator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        //listeners untuk nomor
        binding.btn0.setOnClickListener { appendOnClick(true, "0") }
        binding.btn1.setOnClickListener { appendOnClick(true, "1") }
        val btn2 = findViewById<Button>(R.id.btn2).setOnClickListener { appendOnClick(true, "2") }
        val btn3 = findViewById<Button>(R.id.btn3).setOnClickListener { appendOnClick(true, "3") }
        val btn4 = findViewById<Button>(R.id.btn4).setOnClickListener { appendOnClick(true, "4") }
        val btn5 = findViewById<Button>(R.id.btn5).setOnClickListener { appendOnClick(true, "5") }
        val btn6 = findViewById<Button>(R.id.btn6).setOnClickListener { appendOnClick(true, "6") }
        val btn7 = findViewById<Button>(R.id.btn7).setOnClickListener { appendOnClick(true, "7") }
        val btn8 = findViewById<Button>(R.id.btn8).setOnClickListener { appendOnClick(true, "8") }
        val btn9 = findViewById<Button>(R.id.btn9).setOnClickListener { appendOnClick(true, "9") }
        val btnDot = findViewById<Button>(R.id.btnDot).setOnClickListener { appendOnClick(true, ".") }

        //listeners untuk operatpr
        val btnPlus = findViewById<Button>(R.id.btnPlus).setOnClickListener { appendOnClick(false, "+") }
        val btnMinus = findViewById<Button>(R.id.btnMinus).setOnClickListener { appendOnClick(false, "-") }
        val btnMultiply = findViewById<Button>(R.id.btnMultiply).setOnClickListener { appendOnClick(false, "*") }
        val btnDivide = findViewById<Button>(R.id.btnDivide).setOnClickListener { appendOnClick(false, "/") }
        val btnLeftB = findViewById<Button>(R.id.btnLeftB).setOnClickListener {appendOnClick(false, "(")}
        val btnRightB = findViewById<Button>(R.id.btnRightB).setOnClickListener { appendOnClick(false, ")") }

        val btnClear = findViewById<Button>(R.id.btnClear).setOnClickListener {
            clear()
        }

        val btnEqual = findViewById<Button>(R.id.btnEqual).setOnClickListener {
            calculate()
        }


    }

    private fun appendOnClick(clear: Boolean, string: String) {
        val tvOutput = findViewById<TextView>(R.id.tvOutput)
        val tvInput = findViewById<TextView>(R.id.tvInput)
        if (clear) {
            tvOutput.text = ""
            tvInput.append(string)
        } else {
            tvInput.append(tvOutput.text)
            tvInput.append(string)
            tvOutput.text = ""
        }

    }


    private fun clear() {
        val tvOutput = findViewById<TextView>(R.id.tvOutput)
        val tvInput = findViewById<TextView>(R.id.tvInput)
        tvInput.text = ""
        tvOutput.text = ""
    }

    private fun calculate() {
        val tvOutput = findViewById<TextView>(R.id.tvOutput)
        val tvInput = findViewById<TextView>(R.id.tvInput)
        try {
            val input = ExpressionBuilder(tvInput.text.toString()).build()
            val output = input.evaluate()
            val longOutput = output.toLong()
            if (output == longOutput.toDouble()) {
                tvOutput.text = longOutput.toString()
            } else {
                tvOutput.text = output.toString()
            }
        } catch (e: Exception) {
            Toast.makeText(
                this@MainActivity,
                "Masukkan Operasi Yang Benar!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
