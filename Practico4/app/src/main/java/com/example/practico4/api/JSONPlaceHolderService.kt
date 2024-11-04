package com.example.practico4.api

import com.example.practico4.models.Email
import com.example.practico4.models.Persona
import com.example.practico4.models.Personas
import com.example.practico4.models.Phone
import com.example.practico4.models.Phones
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface JSONPlaceHolderService {
    @GET("api/personas/{id}")
    fun getPersonaById(@Path("id") id: Int): Call<Persona>

    @GET("api/personas")
    fun getPersonaList(): Call<Personas>

    @POST("api/personas")
    fun insertPersona(@Body persona: Persona): Call<Persona>

    @DELETE("api/personas/{id}")
    fun deletePersona(@Path("id") id: Int): Call<Persona>

    @PUT("api/personas/{id}")
    fun updatePersona(@Path("id") id: Int, @Body persona: Persona): Call<Persona>

    @GET("api/phones")
    fun getPhoneList(): Call<Phones>

    @POST("api/phones")
    fun insertPhone(@Body phone: Phone): Call<Phone>

    @POST("api/emails")
    fun insertEmail(@Body email: Email): Call<Email>

}