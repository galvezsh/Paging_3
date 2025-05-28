package com.galvezsh.paging3.presentation.detailScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.galvezsh.paging3.ui.theme.Purple80


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

    Box( modifier = Modifier.fillMaxSize()
        .background(color = Purple80)
        .padding(16.dp)
    ) {

        if ( error == false ) {

            if ( isLoading ) {
                CircularProgressIndicator(
                    modifier = Modifier.align( Alignment.Center ),
                    color = Color.Black
                )

            } else {
                Text(
                    text = character.name,
                    fontSize = 32.sp,
                    lineHeight = 32.sp,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align( Alignment.Center )
                )
            }

        } else {
            Text(
                text = "An error has occurred, please check your internet connection.",
                fontSize = 32.sp,
                lineHeight = 32.sp,
                textAlign = TextAlign.Center,
                color = Color.Red,
                modifier = Modifier.align( Alignment.Center )
            )
        }
    }
}