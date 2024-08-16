package com.example.pokemontcg

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApiService {
    @GET("cards")
    suspend fun getCards(@Query("page") page: Int, @Query("pageSize") pageSize: Int): PokemonCardResponse
}

object RetrofitInstance {
    val api: PokemonApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.pokemontcg.io/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonApiService::class.java)
    }
}
