package br.senai.sp.jandira.viagens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.senai.sp.jandira.viagens.adapter.DestinoRecenteAdapter
import br.senai.sp.jandira.viagens.model.DestinosRecentes

class MainActivity : AppCompatActivity() {

    lateinit var rvDestinosRecentes: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvDestinosRecentes = findViewById(R.id.rv_destinos_recentes)

        rvDestinosRecentes.layoutManager =
            LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL, false)

        val adapterDestinosRecentes =
            DestinoRecenteAdapter(setListaDestinosRecentes(), this)

        rvDestinosRecentes.adapter = adapterDestinosRecentes

    }

    private fun setListaDestinosRecentes() : List<DestinosRecentes> {

        val lista = listOf(
            DestinosRecentes("Porto de Galinhas", "Pernambuco", "R$ 1.500,00"),
            DestinosRecentes("Cristo Redentor", "Rio de Janeiro", "R$ 600,00"),
            DestinosRecentes("Praia das Joaquinas", "Santa Catarina", "R$ 800,00"),
            DestinosRecentes("Gramado", "Rio G. do Sul", "R$ 2.500,00"),
            DestinosRecentes("Campos do Jordão", "São Paulo", "R$ 500,00"),
            DestinosRecentes("Porto Seguro", "Bahia", "R$ 500,00"))

        return lista

    }
}