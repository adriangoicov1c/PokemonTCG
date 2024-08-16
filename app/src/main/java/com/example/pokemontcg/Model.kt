package com.example.pokemontcg

data class PokemonCardResponse(
    val data: List<PokemonCard2>
)

data class PokemonCard2(
    val id: String,
    val name: String,
    val images: CardImages
)

data class CardImages(
    val small: String,
    val large: String
)
