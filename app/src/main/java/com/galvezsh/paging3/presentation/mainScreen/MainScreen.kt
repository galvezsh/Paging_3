package com.galvezsh.paging3.presentation.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.galvezsh.paging3.presentation.model.CharacterModel

@Composable
fun MainScreen( mainViewModel: MainViewModel = hiltViewModel() ) {

    // Is important to use 'collectAsLazyPagingItems' because is gonna load always new characters when we scroll down in the screen
    val characters = mainViewModel.characters.collectAsLazyPagingItems()

    LazyColumn {
        items( characters.itemCount ) {

            // Important to check that if there is no character in the 'it' position, simply do nothing
            characters[ it ]?.let { character ->
                ItemList( character )
            }
        }
    }
}

@Composable
fun ItemList( character: CharacterModel ) {
    Box( modifier = Modifier
        .fillMaxSize()
        .padding( 48.dp )
        .height( 200.dp )
        .background( Color.Black )
    ) {
        Text( text = character.name, color = Color.White )
    }
}