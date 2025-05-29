package com.galvezsh.paging3.presentation.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.galvezsh.paging3.domain.GetAllCharactersFlowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModel @Inject constructor(
    getAllCharactersFlowUseCase: GetAllCharactersFlowUseCase,
): ViewModel() {

    private val _search = MutableStateFlow( "" )
    val search: StateFlow<String> = _search

    // The main use for '.cachedIn' is for kept the data loaded even if the internet connection gets lost. Is really usefully when the screen rotates
    var characters = _search.flatMapLatest { getAllCharactersFlowUseCase( _search.value ).cachedIn( viewModelScope ) }

    fun onChangedSearch( newSearch: String ) {
        _search.value = newSearch
    }
}