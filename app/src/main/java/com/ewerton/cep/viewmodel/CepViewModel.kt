package com.ewerton.cep.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.ewerton.cep.model.Cep
import com.ewerton.cep.repository.CepRepository

class CepViewModel(private val repository: CepRepository): ViewModel() {

    private var liveData = MutableLiveData<Cep?>()

    fun handle(): LiveData<Cep?> = liveData

    fun buscaCep(cep: String) {
        repository.vaiNaApiCep(cep).observeForever {
            liveData.value = it
        }
    }

    private fun validCep(cep: String): Boolean {
        if(cep.isEmpty()) {
            //retorna erro
            return false
        }

        if(cep.length != 8) {
            //retorna erro
            return false
        }

        return true
    }

    private fun unmask(){

    }

}