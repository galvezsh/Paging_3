package com.galvezsh.paging3.presentation.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.galvezsh.paging3.domain.GetAllCharactersFlowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor( getAllCharactersFlowUseCase: GetAllCharactersFlowUseCase ): ViewModel() {

    // The main use for '.cachedIn' is for kept the data loaded even if the internet connection gets lost. Is really usefully when the screen rotates
    var characters = getAllCharactersFlowUseCase().cachedIn( viewModelScope )
}