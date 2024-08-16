package com.example.pokemontcg

import androidx.room.*
import com.example.pokemontcg.PokemonCard

@Dao
interface PokemonCardDao {
    @Query("SELECT * FROM pokemon_cards")
    fun getAllCards(): List<PokemonCard>

    @Query("SELECT * FROM pokemon_cards WHERE owned = 1")
    fun getOwnedCards(): List<PokemonCard>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(cards: List<PokemonCard>)

    @Update
    fun updateCard(card: PokemonCard)
}