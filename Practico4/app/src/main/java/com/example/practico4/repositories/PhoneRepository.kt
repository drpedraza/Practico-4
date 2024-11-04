package com.example.practico4.repositories

import com.example.practico4.api.JSONPlaceHolderService
import com.example.practico4.models.Phone
import com.example.practico4.models.Phones
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object PhoneRepository {

    fun getPhoneList(
        onSuccess: (Phones) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService::class.java)
        service.getPhoneList().enqueue(object : Callback<Phones> {
            override fun onResponse(call: Call<Phones>, response: Response<Phones>) {
                if (response.isSuccessful) {
                    val persona = response.body()
                    onSuccess(persona!!)
                }
            }

            override fun onFailure(call: Call<Phones>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }

    fun insertPhone(phone: Phone, onSuccess: (Phone) -> Unit, onError: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService::class.java)
        service.insertPhone(phone).enqueue(object : Callback<Phone> {
            override fun onResponse(call: Call<Phone>, response: Response<Phone>) {
                if (response.isSuccessful) {
                    val post = response.body()
                    onSuccess(post!!)
                }
            }

            override fun onFailure(call: Call<Phone>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }
}