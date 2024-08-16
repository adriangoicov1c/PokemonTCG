package com.example.pokemontcg

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokemontcg.PokemonCard

@Database(entities = [PokemonCard::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonCardDao(): PokemonCardDao
}