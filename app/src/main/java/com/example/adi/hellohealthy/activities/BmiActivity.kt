package com.example.adi.hellohealthy.activities

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback
import com.example.adi.hellohealthy.R
import com.example.adi.hellohealthy.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.pow

class BmiActivity : AppCompatActivity() {

    private var binding: ActivityBmiBinding? = null
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)

        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        if (Build.VERSION.SDK_INT >= 33) {
            onBackInvokedDispatcher.registerOnBackInvokedCallback(
                OnBackInvokedDispatcher.PRIORITY_DEFAULT
            ) {
                finish()
            }
        } else {
            onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    finish()
                }
            })
        }

        binding?.lLYourBmiAndDescription?.visibility = View.GONE
        
        setSupportActionBar(binding?.bmiCalcActivityToolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding?.bmiCalcActivityToolBar?.setNavigationOnClickListener {
            finish()
        }

        binding?.radioGrpUnit?.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.rbMetricUnits) {
                binding?.lLYourBmiAndDescription?.visibility = View.GONE
                binding?.eTInches?.text?.clear()
                binding?.eTWeight?.text?.clear()
                binding?.eTHeightOrFeet?.text?.clear()
                binding?.tILInches?.visibility = View.GONE
                binding?.tIWeight?.setHint(R.string.weight_in_kg)
                binding?.tILHeight0rFeet?.setHint(R.string.height_in_cm)
            } else {
                binding?.lLYourBmiAndDescription?.visibility = View.GONE
                binding?.tILInches?.visibility = View.VISIBLE
                binding?.eTInches?.text?.clear()
                binding?.eTWeight?.text?.clear()
                binding?.eTHeightOrFeet?.text?.clear()
                binding?.tIWeight?.setHint(R.string.weight_in_pounds)
                binding?.tILHeight0rFeet?.setHint(R.string.feet)
            }
        }
        
        binding?.btnCalcBmi?.setOnClickListener { 
            if (validateMetricUnit()) {
                binding?.lLYourBmiAndDescription?.visibility = View.VISIBLE
                val heightInMeter: Float
                val feet: Float
                val inch: Float
                val weightInKg: Float
                val weightInPounds: Float
                if (binding?.rbMetricUnits?.isChecked!!) {
                    heightInMeter = binding?.eTHeightOrFeet?.text.toString().toFloat() / 100
                    weightInKg = binding?.eTWeight?.text.toString().toFloat()
                } else {
                    feet = binding?.eTHeightOrFeet?.text.toString().toFloat()
                    inch = if (binding?.eTInches?.text.isNullOrEmpty()) {
                        0f
                    } else {
                        binding?.eTInches?.text.toString().toFloat()
                    }
                    weightInPounds = binding?.eTWeight?.text.toString().toFloat()
                    weightInKg = 0.45359236f * weightInPounds
                    heightInMeter = ((feet * 12f) + inch) * 0.0254f
                }
                val bmi: Float = weightInKg / heightInMeter.pow(2)

                binding?.bmiValueTV?.text = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
                binding?.bmiTypeTV?.text = when (bmi) {
                    in 0f..15f-> "Very severely underweight"
                    in 15f..16f -> "Severely underweight"
                    in 16f..18.5f -> "Underweight"
                    in 18.5f..25f -> "Normal"
                    in 25f..30f -> "Overweight"
                    in 30f..35f -> "Obese class 1 (Moderately obese)"
                    in 35f..40f -> "Obese class 2 (Severely obese)"
                    else -> "Obese class 3 (Very severely obese)"
                }
                binding?.bmiDescriptionTV?.text = when (bmi) {
                    in 0f..18.5f-> "Oops! you really need to take better care of yourself! Eat more!"
                    in 18.5f..25f -> "Congratulations! You are in good shape!"
                    in 25f..35f-> "Oops! you really need to take better care of yourself! Workout!"
                    else -> "OMG! You are in a very dangerous condition! Act now!"
                }
            } else {
                binding?.lLYourBmiAndDescription?.visibility = View.GONE
                binding?.eTHeightOrFeet?.text?.clear()
                binding?.eTWeight?.text?.clear()
                binding?.eTInches?.text?.clear()
                Toast.makeText(this, "Please enter valid values", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateMetricUnit(): Boolean {
        var valid = true

        if (binding?.eTWeight?.text.isNullOrEmpty() || binding?.eTHeightOrFeet?.text.isNullOrEmpty() || binding?.eTWeight?.text.toString().toFloat() == 0f || binding?.eTHeightOrFeet?.text.toString().toFloat() == 0f) {
            valid = false
        }

        return valid
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}