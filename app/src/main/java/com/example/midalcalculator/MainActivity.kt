package com.example.midalcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.Toast
import com.example.midalcalculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initView()

    }

    private fun initView() {

        binding.txtResult.text = "0"
        bindNumbers()
        bindOperators()
    }

    private fun bindOperators() {

        binding.btnDivide.setOnClickListener {
            if (binding.txtExpression.text.isNotEmpty()) {
                var char = binding.txtExpression.text.last()
                if (
                    char != '+' &&
                    char != '-' &&
                    char != '*' &&
                    char != '/'){
                    appendNumbers("/")
                }


            }
        }

        binding.btnMinus.setOnClickListener {
            if (binding.txtExpression.text.isNotEmpty()) {
                var char = binding.txtExpression.text.last()
                if (
                    char != '+' &&
                    char != '-' &&
                    char != '*' &&
                    char != '/'){
                    appendNumbers("-")
                }
            }
        }

        binding.btnMultiple.setOnClickListener {
            if (binding.txtExpression.text.isNotEmpty()) {
                var char = binding.txtExpression.text.last()
                if (
                    char != '+' &&
                    char != '-' &&
                    char != '*' &&
                    char != '/'){
                    appendNumbers("*")
                }
            }
        }

        binding.btnPlus.setOnClickListener {
            if (binding.txtExpression.text.isNotEmpty()) {
                var char = binding.txtExpression.text.last()
                if (
                    char != '+' &&
                    char != '-' &&
                    char != '*' &&
                    char != '/'){
                    appendNumbers("+")
                }

            }

            binding.btnEqual.setOnClickListener {
                try {
                    val expression = ExpressionBuilder(binding.txtExpression.text.toString()).build()
                    val result = expression.evaluate()

                    val longResult = result.toLong()

                    if (result == longResult.toDouble()) {
                        binding.txtResult.text = "= " + longResult.toString()
                    }else
                        binding.txtResult.text = "= " + result.toString()

                    binding.txtExpression.text = ""
                }catch (e : Exception){
                    binding.txtExpression.text = ""
                    binding.txtResult.text = ""
                    Toast.makeText(this, "ارور رخ داد !", Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.btnOpenBracket.setOnClickListener {
            appendNumbers("(")
        }

        binding.btnBracket.setOnClickListener {
            appendNumbers(")")
        }

        binding.btnRemove.setOnClickListener {
            if (binding.txtExpression.text.isNotEmpty()) {
                binding.txtExpression.text =
                    binding.txtExpression.text.substring(0, binding.txtExpression.length() - 1)
            }
        }
    }

    private fun bindNumbers() {
        binding.btnAc.setOnClickListener {
            binding.txtExpression.text = ""
            binding.txtResult.text = "0"
        }

        binding.btn0.setOnClickListener {
            if (binding.txtExpression.text.isNotEmpty()) {
                binding.txtResult.text = "0"
                appendNumbers("0")
            }
        }

        binding.btn1.setOnClickListener {
            binding.txtResult.text = "0"
            appendNumbers("1")
        }

        binding.btn2.setOnClickListener {
            binding.txtResult.text = "0"
            appendNumbers("2")
        }

        binding.btn3.setOnClickListener {
            binding.txtResult.text = "0"
            appendNumbers("3")

        }

        binding.btn4.setOnClickListener {
            binding.txtResult.text = "0"
            appendNumbers("4")

        }

        binding.btn5.setOnClickListener {
            binding.txtResult.text = "0"
            appendNumbers("5")

        }

        binding.btn6.setOnClickListener {
            binding.txtResult.text = "0"
            appendNumbers("6")

        }

        binding.btn7.setOnClickListener {
            binding.txtResult.text = "0"
            appendNumbers("7")

        }

        binding.btn8.setOnClickListener {
            binding.txtResult.text = "0"
            appendNumbers("8")

        }

        binding.btn9.setOnClickListener {
            binding.txtResult.text = "0"
            appendNumbers("9")
        }

        binding.btnDot.setOnClickListener {
            if (binding.txtExpression.text.isEmpty()) appendNumbers("0.")
            else if (binding.txtExpression.text.last() != '.') {
                var countOperators = 0
                var countDots = 0
                binding.txtExpression.text.forEach {
                    if (it == '+' || it == '-' || it == '*' || it == '/') countOperators++
                    if (it == '.') countDots++
                }
                if (countDots <= countOperators) appendNumbers(".")
            }
        }


    }

    private fun appendNumbers(number: String) {
        binding.txtExpression.append(number)

        val viewTree: ViewTreeObserver = binding.horizontalScrollViewTxtExpression.viewTreeObserver
        viewTree.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.horizontalScrollViewTxtExpression.viewTreeObserver.removeOnGlobalLayoutListener(
                    this
                )
                binding.horizontalScrollViewTxtExpression.scrollTo(binding.txtExpression.width, 0)
            }
        })

    }


}