package com.anmol.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? = null
    var lastNumeric : Boolean = false
    var lastDot : Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)

    }

    fun onDigit(view: View){
        tvInput?.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }

    fun onClear(view: View){
        tvInput?.text=""
    }

    fun onDecimalPoint(view: View){
        if(lastNumeric && !lastDot){
            tvInput?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onOperator(view: View) {
        tvInput?.text?.let {
//            it means when one operator is added we cant add another operator
            if (lastNumeric && !isOperatedAdded(it.toString())) {
                tvInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }

    }

        fun onEqual(view: View){
            if(lastNumeric){
                var tvValue = tvInput?.text.toString()
                var prefix = ""

                try {
                    if(tvValue.startsWith("-")){
                        prefix = "-"
//                        it gives for example "-99" to "99"
                        tvValue = tvValue.substring(1)
                    }
                    if(tvValue.contains("-")){
                        val splitValue = tvValue.split("-")

                        var one = splitValue[0] //99
                        var two = splitValue[1] //1

//                        for example if "-33" and "-66" it gives "-99"
                        if(prefix.isNotEmpty()){
                            one = prefix+one
                        }

                        tvInput?.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                    }
                    else if(tvValue.contains("+")){
                        val splitValue = tvValue.split("+")

                        var one = splitValue[0] //99
                        var two = splitValue[1] //1

//                        for example if "-33" and "-66" it gives "-99"
                        if(prefix.isNotEmpty()){
                            one = prefix+one
                        }

                        tvInput?.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                    }
                    else if(tvValue.contains("/")){
                        val splitValue = tvValue.split("/")

                        var one = splitValue[0] //99
                        var two = splitValue[1] //1

//                        for example if "-33" and "-66" it gives "-99"
                        if(prefix.isNotEmpty()){
                            one = prefix+one
                        }

                        tvInput?.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                    }
                    else if(tvValue.contains("*")){
                        val splitValue = tvValue.split("*")

                        var one = splitValue[0] //99
                        var two = splitValue[1] //1

//                        for example if "-33" and "-66" it gives "-99"
                        if(prefix.isNotEmpty()){
                            one = prefix+one
                        }

                        tvInput?.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                    }

                }catch (e: ArithmeticException){
                    e.printStackTrace()
                }

            }
        }
//    remove zero after dot
    private fun removeZeroAfterDot(result: String) : String{
        var value = result
        if(result.contains("0"))
            value = result.substring(0, result.length - 2)

        return value
    }


    private fun isOperatedAdded(value: String) : Boolean{
        return if(value.startsWith("-")){
            false
        }else{
//            if value anyone it returns true otherwise also return false
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
        }
    }



}