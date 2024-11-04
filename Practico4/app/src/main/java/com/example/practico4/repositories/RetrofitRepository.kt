package com.example.practico4.repositories

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitRepository {
    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://apicontactos.jmacboy.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}