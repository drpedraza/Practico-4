package com.example.practico4.repositories

import com.example.practico4.api.JSONPlaceHolderService
import com.example.practico4.models.Email
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object EmailRepository {

    fun insertEmail(email: Email, onSuccess: (Email) -> Unit, onError: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService::class.java)
        service.insertEmail(email).enqueue(object : Callback<Email> {
            override fun onResponse(call: Call<Email>, response: Response<Email>) {
                if (response.isSuccessful) {
                    val post = response.body()
                    onSuccess(post!!)
                }
            }

            override fun onFailure(call: Call<Email>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }
}