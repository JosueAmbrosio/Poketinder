package com.ambrosio.josue.poketinder

import PokemonResponse

data class Pokemon(
    val count: Int,
    val next: String,
    val results: List<PokemonResponse>
)
