package com.bergsonnobrega.alcoolgasolina

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var textInputAlcool: TextInputLayout
    private lateinit var editAlcool: TextInputEditText

    private lateinit var textInputgasolina: TextInputLayout
    private lateinit var editgasolina: TextInputEditText

    private lateinit var btnCalcular: Button
    private lateinit var textResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        inicializarComponenteInterface()
        btnCalcular.setOnClickListener {
            calcularMelhorPreco()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun calcularMelhorPreco() {
        val precoAlcool = editAlcool.text.toString()
        val precoGasolina = editgasolina.text.toString()
        
        val resultadoValidacao = validarCampos(precoAlcool, precoGasolina)
        if (resultadoValidacao){
            val precoAlcoolNumero = precoAlcool.toDouble()
            val precoGasolinaNumero = precoGasolina.toDouble()
            val resultado = precoAlcoolNumero / precoGasolinaNumero
            if (resultado >= 0.7){
                textResultado.text = "Melhor utilizar Gazolina"

            }else{
                textResultado.text = "Melhor utilizar Álcool"
            }

        }
    }

    private fun validarCampos(pAlcool: String, pGasolina: String): Boolean {

        textInputAlcool.error = null
        textInputgasolina.error = null

        if (pAlcool.isEmpty()){
            textInputAlcool.error = "Digite o preço do álcool"
            return false

        }else if (pGasolina.isEmpty()){
            textInputgasolina.error = "Digite o preço da gasolina"
            return false

        }
        return true
    }

    private fun inicializarComponenteInterface() {

        textInputAlcool = findViewById(R.id.text_input_alcool)
        editAlcool = findViewById(R.id.edit_alcool)

        textInputgasolina = findViewById(R.id.text_input_gasolina)
        editgasolina = findViewById(R.id.edit_gasolina)

        btnCalcular = findViewById(R.id.btn_calcular)
        textResultado = findViewById(R.id.text_resultado)

    }
}