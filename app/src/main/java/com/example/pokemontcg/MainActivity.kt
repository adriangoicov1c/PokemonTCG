package com.example.pokemontcg


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch

import androidx.compose.ui.draw.clip // Esta es la importación necesaria para clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.pokemontcg.PokemonCard

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar Room y el Repositorio
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "pokemon-db"
        )
            .allowMainThreadQueries()
            .build()
        val repository = PokemonRepository(db.pokemonCardDao())

        // Descarga y almacenamiento de cartas
        lifecycleScope.launch {
            repository.fetchAndSaveCards()
        }

        setContent {
            PokemonCardBook(repository)
        }
    }
}



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PokemonCardBook(repository: PokemonRepository) {
    // Simulación de obtener cartas del repositorio (se debe usar un flujo real de Room)
    val allCards = remember { repository.getAllCards() }

    val pagerState = rememberPagerState{ 1000 }

    //val state = rememberPagerState { 10 }

    HorizontalPager(

        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        val start = page * 4
        val end = minOf(start + 4, allCards.size)
        val cardsOnPage = allCards.subList(start, end)


        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(cardsOnPage) { card ->
                PokemonCardItem(card)
            }
        }
    }
}

@Composable
fun PokemonCardItem(card: PokemonCard) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .clip(RoundedCornerShape(CornerSize(8.dp))),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = card.imageUrl),
            contentDescription = card.name,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.height(350.dp).fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    // Preview sin datos reales
    PokemonCardBook(PokemonRepository(
        FakeDao()))
}
