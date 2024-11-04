package com.example.practico4.repositories

import com.example.practico4.api.JSONPlaceHolderService
import com.example.practico4.models.Persona
import com.example.practico4.models.Personas
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

object PersonaRepository {
    fun getPersonaList(
        onSuccess: (Personas) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService::class.java)
        service.getPersonaList().enqueue(object : Callback<Personas> {
            override fun onResponse(call: Call<Personas>, response: Response<Personas>) {
                if (response.isSuccessful) {
                    val persona = response.body()
                    onSuccess(persona!!)
                }
            }

            override fun onFailure(call: Call<Personas>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }


    fun getPersonaById(id: Int, onSuccess: (Persona) -> Unit, onError: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService::class.java)
        service.getPersonaById(id).enqueue(object : Callback<Persona> {
            override fun onResponse(call: Call<Persona>, response: Response<Persona>) {
                if (response.isSuccessful) {
                    val post = response.body()
                    onSuccess(post!!)
                }
            }

            override fun onFailure(call: Call<Persona>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }

    fun insertPersona(persona: Persona, onSuccess: (Persona) -> Unit, onError: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService::class.java)
        service.insertPersona(persona).enqueue(object : Callback<Persona> {
            override fun onResponse(call: Call<Persona>, response: Response<Persona>) {
                if (response.isSuccessful) {
                    val post = response.body()
                    onSuccess(post!!)
                }
            }

            override fun onFailure(call: Call<Persona>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }

    fun deletePersona(id: Int, onSuccess: (Persona) -> Unit, onError: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService::class.java)
        service.deletePersona(id).enqueue(object : Callback<Persona> {
            override fun onResponse(call: Call<Persona>, response: Response<Persona>) {
                if (response.isSuccessful) {
                    val post = response.body()
                    onSuccess(post!!)
                }
            }

            override fun onFailure(call: Call<Persona>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }

    fun updatePersona(id: Int, persona: Persona, onSuccess: (Persona) -> Unit, onError: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService::class.java)
        service.updatePersona(id, persona).enqueue(object : Callback<Persona> {
            override fun onResponse(call: Call<Persona>, response: Response<Persona>) {
                if (response.isSuccessful) {
                    val post = response.body()
                    onSuccess(post!!)
                }
            }

            override fun onFailure(call: Call<Persona>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }

}