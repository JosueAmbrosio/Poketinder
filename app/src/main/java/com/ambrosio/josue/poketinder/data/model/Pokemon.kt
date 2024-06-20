package com.ambrosio.josue.poketinder.data.model

import com.ambrosio.josue.poketinder.data.model.PokemonResponse

data class Pokemon(
    val count: Int,
    val next: String,
    val results: List<PokemonResponse>
)
