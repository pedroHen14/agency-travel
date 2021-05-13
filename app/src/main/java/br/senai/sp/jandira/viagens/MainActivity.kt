package br.senai.sp.jandira.viagens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.senai.sp.jandira.viagens.adapter.DestinoRecenteAdapter
import br.senai.sp.jandira.viagens.api.DestinosRecentesCall
import br.senai.sp.jandira.viagens.api.RetrofitApi
import br.senai.sp.jandira.viagens.model.DestinosRecentes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var rvDestinosRecentes: RecyclerView
    lateinit var adapterDestinosRecentes: DestinoRecenteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvDestinosRecentes = findViewById(R.id.rv_destinos_recentes)

        rvDestinosRecentes.layoutManager =
            LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL, false)

        adapterDestinosRecentes = DestinoRecenteAdapter(this)

        rvDestinosRecentes.adapter = adapterDestinosRecentes

        carregarListaDestinosRecentes()
    }

    private fun carregarListaDestinosRecentes()  {

        var destinosRecentes: List<DestinosRecentes>

        val retrofit = RetrofitApi.getRetrofit()
        val destinosRecentesCall = retrofit.create(DestinosRecentesCall::class.java)

        val call = destinosRecentesCall.getDestinosRecentes()

        call.enqueue(object : Callback<List<DestinosRecentes>> {
            override fun onFailure(call: Call<List<DestinosRecentes>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "A conexão falhou!", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<List<DestinosRecentes>>,
                response: Response<List<DestinosRecentes>>
            ) {
                destinosRecentes = response.body()!!
                adapterDestinosRecentes.updateListaRecente(destinosRecentes)
            }
        })
    }
}