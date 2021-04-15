package com.ewerton.cep.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ewerton.cep.model.Cep
import com.ewerton.cep.repository.CepRepository

class CepViewModel(private val repository: CepRepository): ViewModel() {



    fun buscaCep(cep: String): LiveData<Cep?> {

        if(validCep(cep)) {

        }

        return repository.vaiNaApiCep(cep)
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