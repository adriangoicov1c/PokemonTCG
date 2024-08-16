package com.example.pokemontcg


class FakeDao : PokemonCardDao {
    override fun getAllCards(): List<PokemonCard> {
        return listOf(
            PokemonCard(
                id = "1",
                name = "Pikachu",
                imageUrl = "https://assets.pokemon.com/assets/cms2/img/cards/web/SWSH1/SWSH1_EN_28.png"
            ),
            PokemonCard(
                id = "2",
                name = "Charizard",
                imageUrl = "https://assets.pokemon.com/assets/cms2/img/cards/web/SWSH1/SWSH1_EN_4.png"
            )
        )
    }

    override fun getOwnedCards(): List<PokemonCard> {
        TODO("Not yet implemented")
    }

    override fun insertAll(cards: List<PokemonCard>) {
        TODO("Not yet implemented")
    }

    override fun updateCard(card: com.example.pokemontcg.PokemonCard) {
        TODO("Not yet implemented")
    }

}
