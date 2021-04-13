package com.ewerton.cep

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://viacep.com.br/ws/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val service: CepService = retrofit.create(CepService::class.java)
        btn_buscar.setOnClickListener {
            service.getCep(ed_cep.text.toString().trim()).enqueue(object : Callback<Cep> {
                @SuppressLint("SetTextI18n")
                override fun onResponse(call: Call<Cep>, response: Response<Cep>) {
                    val cep = response.body()
                    if (cep != null) {
                        response_area.visibility = View.VISIBLE
                        tv_cep.text = "CEP: ${cep.cep}"
                        tv_logradouro.text = "LOGRADOURO: ${cep.logradouro}"
                        tv_complemento.text = "COMPLEMENTO: ${cep.complemento}"
                        tv_bairro.text = "BAIRRO: ${cep.bairro}"
                        tv_localidade.text = "LOCALIDADE: ${cep.localidade}"
                        tv_uf.text = "UF: ${cep.uf}"
                    } else {
                        response_area.visibility = View.GONE
                    }
                }
                override fun onFailure(call: Call<Cep>, t: Throwable) {
                    response_area.visibility = View.GONE
                }
            })
        }
    }
}
