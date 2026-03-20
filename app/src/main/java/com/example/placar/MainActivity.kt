package com.example.placar

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.placar.ui.theme.PlacarTheme

class MainActivity : ComponentActivity() {
    private var pontuacaoTimeA: Int = 0
    private var pontuacaoTimeB: Int = 0

    private lateinit var pTimeA: TextView
    private lateinit var pTimeB: TextView

    private var ultimaJogada: Pair<Int, String>? = null
    private lateinit var txtHistorico: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pTimeA = findViewById(R.id.placarTimeA)
        pTimeB = findViewById(R.id.placarTimeB)

        txtHistorico = findViewById(R.id.txtHistorico)

        val bTresPontosTimeA: Button = findViewById(R.id.tresPontosA)
        val bDoisPontosTimeA: Button = findViewById(R.id.doisPontosA)
        val bTLivreTimeA: Button = findViewById(R.id.tiroLivreA)
        val bTresPontosTimeB: Button = findViewById(R.id.tresPontosB)
        val bDoisPontosTimeB: Button = findViewById(R.id.doisPontosB)
        val bTLivreTimeB: Button = findViewById(R.id.tiroLivreB)
        val bReiniciar: Button = findViewById(R.id.reiniciarPartida)
        val bDesfazer: Button = findViewById(R.id.btnDesfazer)

        bDesfazer.setOnClickListener { desfazer() }

        bTresPontosTimeA.setOnClickListener { adicionarPontos(3, "A") }

        bDoisPontosTimeA.setOnClickListener { adicionarPontos(2, "A") }

        bTLivreTimeA.setOnClickListener { adicionarPontos(1, "A") }

        bTresPontosTimeB.setOnClickListener { adicionarPontos(3, "B") }

        bDoisPontosTimeB.setOnClickListener { adicionarPontos(2, "B") }

        bTLivreTimeB.setOnClickListener { adicionarPontos(1, "B") }

        bReiniciar.setOnClickListener { reiniciarPartida() }


    }

    fun adicionarPontos(pontos: Int, time: String) {
        if(time == "A"){
            pontuacaoTimeA += pontos

        }else {
            pontuacaoTimeB += pontos

        }
        atualizaPlacar(time)

        ultimaJogada = Pair(pontos, time)

        txtHistorico.text = "Última jogada: Time $time +$pontos"
    }

    fun atualizaPlacar(time: String){
        if(time == "A"){
            pTimeA.setText(pontuacaoTimeA.toString())
        }else {
            pTimeB.setText(pontuacaoTimeB.toString())
        }
    }

    fun desfazer() {
        ultimaJogada?.let { (pontos, time) ->
            if(time == "A") pontuacaoTimeA -= pontos else pontuacaoTimeB -= pontos
            atualizaPlacar(time)
            txtHistorico.text = "Jogada desfeita!"
            ultimaJogada = null // Limpa para não desfazer a mesma jogada duas vezes
        }
    }

    fun reiniciarPartida() {
        pontuacaoTimeA = 0
        pTimeA.setText(pontuacaoTimeA.toString())
        pontuacaoTimeB = 0
        pTimeB.setText(pontuacaoTimeB.toString())
        Toast.makeText(this,"Placar reiniciado",Toast.LENGTH_SHORT).show()

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PlacarTheme {
        Greeting("Android")
    }
}