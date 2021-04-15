package com.ewerton.cep.core.di

import com.ewerton.cep.network.CepService
import com.ewerton.cep.repository.CepRepository
import com.ewerton.cep.viewmodel.CepViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel {
        CepViewModel(get())
    }
}

val repositoryModule = module {

    fun provideRepository(cepService: CepService): CepRepository {
        return CepRepository(cepService)
    }

    single {
        provideRepository(get())
    }
}

val netModule = module {

    fun provideRefrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://viacep.com.br/ws/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    single {
        provideRefrofit()
    }

}


val apiModule = module {
    fun provideCepApi(retrofit: Retrofit): CepService {
        return retrofit.create(
            CepService::class.java)
    }
    single {
        provideCepApi(get())
    }
}