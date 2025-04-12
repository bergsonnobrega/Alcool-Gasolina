package com.bergsonnobrega.alcoolgasolina

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.button.MaterialButton

class ResultadoActivity : AppCompatActivity() {
    private lateinit var textResultado: TextView
    private lateinit var textDicas: TextView
    private lateinit var btnVoltar: MaterialButton
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        textResultado = findViewById(R.id.text_resultado_detalhado)
        textDicas = findViewById(R.id.text_dicas)
        btnVoltar = findViewById(R.id.btn_voltar)

        val melhorOpcao = intent.getStringExtra("melhor_opcao")
        val precoAlcool = intent.getDoubleExtra("preco_alcool", 0.0)
        val precoGasolina = intent.getDoubleExtra("preco_gasolina", 0.0)
        val razao = intent.getDoubleExtra("razao", 0.0)

        mostrarResultado(melhorOpcao, precoAlcool, precoGasolina, razao)
        mostrarDicas(melhorOpcao)

        btnVoltar.setOnClickListener {
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun mostrarResultado(melhorOpcao: String?, precoAlcool: Double, precoGasolina: Double, razao: Double) {
        val resultado = """
            Análise Detalhada:
            
            Preço do Álcool: R$ ${String.format("%.2f", precoAlcool)}
            Preço da Gasolina: R$ ${String.format("%.2f", precoGasolina)}
            Razão Álcool/Gasolina: ${String.format("%.2f", razao)}
            
            Recomendação: $melhorOpcao
        """.trimIndent()

        textResultado.text = resultado
    }

    private fun mostrarDicas(melhorOpcao: String?) {
        val dicas = if (melhorOpcao == "Melhor utilizar Álcool") {
            """
            Dicas para Economizar com Álcool:
            
            1. Abasteça em postos de confiança
            2. Evite andar com o tanque cheio
            3. Mantenha a manutenção do veículo em dia
            4. Verifique a pressão dos pneus regularmente
            5. Evite acelerações bruscas
            6. Use o ar condicionado com moderação
            7. Planeje seus trajetos para evitar congestionamentos
            """.trimIndent()
        } else {
            """
            Dicas para Economizar com Gasolina:
            
            1. Abasteça em postos de confiança
            2. Evite andar com o tanque cheio
            3. Mantenha a manutenção do veículo em dia
            4. Verifique a pressão dos pneus regularmente
            5. Evite acelerações bruscas
            6. Use o ar condicionado com moderação
            7. Planeje seus trajetos para evitar congestionamentos
            """.trimIndent()
        }

        textDicas.text = dicas
    }
}