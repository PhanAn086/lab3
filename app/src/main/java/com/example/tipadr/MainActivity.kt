package com.example.tipadr

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tipadr.databinding.ActivityMainBinding

import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        var stringInTextField = binding.plainTextInput.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null) {
            stringInTextField = ""
            return
        }

        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        var tip = tipPercentage * cost
        if (binding.roundUpSwitch.isChecked) {
            tip =  ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.display.text = getString(R.string.tip_amount,formattedTip)
    }
}