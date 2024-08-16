package com.example.pokemontcg

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_cards")
data class PokemonCard(
    @PrimaryKey val id: String,
    val name: String,
    val imageUrl: String?,
    val owned: Boolean = false
)