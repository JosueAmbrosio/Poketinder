package com.ambrosio.josue.poketinder

import PokemonResponse

data class PokemonListResponse(
    val count: Int,
    val next: String,
    val results: List<PokemonResponse>
)
