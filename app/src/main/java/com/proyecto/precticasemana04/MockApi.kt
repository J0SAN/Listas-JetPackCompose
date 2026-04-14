package com.proyecto.precticasemana04

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class User(
    val id: String,
    val name: String,
    val email: String
)

interface MockApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://69de36eb410caa3d47bacbca.mockapi.io/prueba/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
val apiService: MockApiService = retrofit.create(MockApiService::class.java)