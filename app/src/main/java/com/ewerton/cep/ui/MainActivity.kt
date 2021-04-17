package com.ewerton.cep.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.ewerton.cep.model.Cep
import com.ewerton.cep.R
import com.ewerton.cep.viewmodel.CepViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: CepViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_buscar.setOnClickListener {
            val cepDigitado = ed_cep.text.toString()
            viewModel.buscaCep(cepDigitado)

        }

        viewModel.handle().observe(this, Observer { cepRetornado ->
            preencherCampos(cepRetornado)
        })

    }

    private fun preencherCampos(cep: Cep?) {
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
}
