package com.galvezsh.paging3.presentation.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.galvezsh.paging3.data.RickAndMortyRepository
import com.galvezsh.paging3.domain.GetAllCharactersFlowUseCase
import com.galvezsh.paging3.presentation.model.CharacterModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor( getAllCharactersFlowUseCase: GetAllCharactersFlowUseCase ): ViewModel() {

    // The main use for '.cachedIn' is for kept the data loaded even if the internet connection gets lost. Is really usefully when the screen rotates
    val characters = getAllCharactersFlowUseCase().cachedIn( viewModelScope )
}