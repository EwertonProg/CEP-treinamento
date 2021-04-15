package com.ewerton.cep.repository

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ewerton.cep.model.Cep
import com.ewerton.cep.network.CepService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CepRepository(private val service: CepService) {

    private val liveDataCepApi = MutableLiveData<Cep?>()

    fun vaiNaApiCep(cep: String): LiveData<Cep?> {

        service.getCep(cep).enqueue(object : Callback<Cep> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<Cep>, response: Response<Cep>) {
                Log.d("AQUI","Chamou aqui")
                val cep = response.body()
                liveDataCepApi.value = cep
            }
            override fun onFailure(call: Call<Cep>, t: Throwable) {

            }
        })

        return liveDataCepApi
    }
}