package com.example.pokemontcg

import com.example.pokemontcg.PokemonCard

class PokemonRepository(private val cardDao: PokemonCardDao) {

    suspend fun fetchAndSaveCards(page: Int = 1) {
        val response = RetrofitInstance.api.getCards(page = page, pageSize = 20)
        val cards = response.data.map { apiCard ->
            PokemonCard(
                id = apiCard.id,
                name = apiCard.name,
                imageUrl = apiCard.images.large
            )
        }
        cardDao.insertAll(cards)
    }

    fun getAllCards() = cardDao.getAllCards()
}
