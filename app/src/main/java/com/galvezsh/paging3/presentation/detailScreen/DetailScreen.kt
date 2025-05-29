package com.galvezsh.paging3.presentation.detailScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.galvezsh.paging3.R
import com.galvezsh.paging3.presentation.mainScreen.ShowCircularProgressBar
import com.galvezsh.paging3.presentation.mainScreen.ShowTextWithButton
import com.galvezsh.paging3.presentation.model.CharacterModel


//@Preview( showBackground = true, showSystemUi = true )
@Composable
fun DetailScreen( detailViewModel: DetailViewModel = hiltViewModel(), characterId: Int ) {

    val character by detailViewModel.character.collectAsState()
    val isLoading by detailViewModel.isLoading.collectAsState()
    val error by detailViewModel.error.collectAsState()

    // It will be executed only one time thanks to the lifecycle of the ViewModel
    if (detailViewModel.getOneTimeExecution() == false ) {
        detailViewModel.loadId( characterId )
        detailViewModel.setOneTimeExecution()
    }

    Box( modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center ) {
        Image(
            painter = painterResource( R.drawable.portal ),
            contentDescription = "Background portal",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        if ( error == false ) {

            if ( isLoading )
                ShowCircularProgressBar()
            else
                ShowCharacterCard( character )

        } else {
            ShowTextWithButton(
                text = "Unexpected error, check internet connection",
                textButton = "Retry",
                onPressedButton = { detailViewModel.loadId( characterId ) }
            )
        }
    }
}

@Composable
fun ShowCharacterCard( character: CharacterModel ) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding( horizontal =  32.dp )
            .wrapContentHeight()
            .clip( RoundedCornerShape( 16.dp ) )
            .border( 2.dp, Color.Black, RoundedCornerShape( 16.dp ) )
            .background( Color( 0xFFDEDDDD ) ),
        ) {
        AsyncImage(
            model = character.image,
            contentDescription = "Character image",
            modifier = Modifier.fillMaxWidth()
        )

        Divider()
        ShowText( label = "Name: ", text = character.name )
        Divider()
        ShowAliveText( isAlive = character.isAlive )
        Divider()
        ShowText( label = "Type: ", text = character.type )
        Divider()
        ShowText( label = "Gender: ", text = character.gender )
        Divider()
        ShowText( label = "Origin planet: ", text = character.origin.name )
        Divider()
        ShowText( label = "Actual location: ", text = character.location.name )
    }
}

@Composable
fun ShowText( label: String, text: String ) {
    Row {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding( 8.dp )
        )
        Text(
            text = text,
            modifier = Modifier.padding( 8.dp )
        )
    }
}

@Composable
fun ShowAliveText( isAlive: Boolean ) {
    Row {
        Text(
            text = "Is alive?:",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding( 8.dp )
        )
        Text(
            text = if ( isAlive ) "Yes" else "No",
            modifier = Modifier.padding( 8.dp )
        )
    }
}

@Composable
fun Divider() {
    HorizontalDivider(
        color = Color.Black,
        thickness = 2.dp,
    )
}