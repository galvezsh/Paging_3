package com.galvezsh.paging3.presentation.mainScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.galvezsh.paging3.R
import com.galvezsh.paging3.presentation.model.CharacterModel

@Composable
fun MainScreen( mainViewModel: MainViewModel = hiltViewModel(), selectedCharacterId: (Int) -> Unit ) {

    // Is important to use 'collectAsLazyPagingItems' because is gonna load always new characters when we scroll down in the screen
    val characters = mainViewModel.characters.collectAsLazyPagingItems()

    Box( modifier = Modifier.fillMaxSize() ) {
        Image(
            painter = painterResource( R.drawable.portal ),
            contentDescription = "Background portal",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        when {
            // First call
            characters.loadState.refresh is LoadState.Loading && characters.itemCount == 0 -> {
                ShowCircularProgressBar()
            }

            // No data
            characters.loadState.refresh is LoadState.NotLoading && characters.itemCount == 0 -> {
                ShowTextWithButton(
                    text = "No data fetched from the server",
                    textButton = "Retry",
                    onPressedButton = { characters.retry() }
                )
            }

            // Error
            characters.loadState.hasError -> {
                ShowTextWithButton(
                    text = "Unexpected error, check internet connection",
                    textButton = "Retry",
                    onPressedButton = { characters.retry() }
                )
            }

            else -> {
                LazyColumn( modifier = Modifier.padding( horizontal = 16.dp ).padding( top = 16.dp ) ) {
                    items( characters.itemCount ) {

                        // Important to check that if there is no character in the 'it' position, simply do nothing
                        characters[ it ]?.let { character ->
                            ShowItemList( character = character, sendId = { characterId -> selectedCharacterId( characterId ) } )
                        }
                    }
                }

                // We reach the end of the paging, and more data is coming
                if ( characters.loadState.append is LoadState.Loading ) {
                    ShowCircularProgressBar()
                }
            }
        }
    }
}

@Composable
fun ShowItemList( character: CharacterModel, sendId: (Int) -> Unit ) {
    Box( modifier = Modifier
            .padding( 16.dp )
            .clip( RoundedCornerShape( 8 ) )
            .border( 2.dp, Color.Black, RoundedCornerShape( 16.dp ) )
            .height( 200.dp )
            .clickable { sendId( character.id ) },
        contentAlignment = Alignment.BottomCenter
    ) {
        AsyncImage(
            model = character.image,
            contentDescription = "Character image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background( Brush.verticalGradient( listOf(
                    Color.Black.copy( alpha = 0.01f ),
                    Color.Black.copy( alpha = 0.02f ),
                    Color.Black.copy( alpha = 0.04f ),
                    Color.Black.copy( alpha = 0.08f ),
                    Color.Black.copy( alpha = 0.16f ),
                    Color.Black.copy( alpha = 0.32f ),
                    Color.Black.copy( alpha = 0.64f ),
                ) ) )
        )

        Text(
            text = character.name,
            color = Color.White,
            modifier = Modifier.padding( bottom = 8.dp )
        )
    }
}

@Composable
fun ShowCircularProgressBar() {
    Box( modifier = Modifier.fillMaxSize() ) {
        CircularProgressIndicator( modifier = Modifier.align( Alignment.Center ), color = Color.Black )
    }
}

@Composable
fun ShowTextWithButton( text: String, textButton: String, onPressedButton: () -> Unit ) {
    Box( modifier = Modifier.fillMaxSize() ) {
        Column(
            modifier = Modifier
                .align( Alignment.Center )
                .clip( RoundedCornerShape( 16.dp ) )
                .border( 2.dp, Color.Black, RoundedCornerShape( 16.dp ) )
                .background( Color( 0xFFDEDDDD ) )
                .padding( 16.dp )
                .wrapContentWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = text,
                modifier = Modifier.padding( bottom = 16.dp ),
                color = Color.Red,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

            Button(
                onClick = { onPressedButton() }
            ) { Text( text = textButton ) }
        }
    }
}