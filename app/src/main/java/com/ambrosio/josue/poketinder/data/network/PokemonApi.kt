package com.ambrosio.josue.poketinder.data.network

import com.ambrosio.josue.poketinder.data.model.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET

interface PokemonApi {
    @GET("/api/v2/pokemon")
    suspend fun getPokemons(): Response<PokemonResponse>
}