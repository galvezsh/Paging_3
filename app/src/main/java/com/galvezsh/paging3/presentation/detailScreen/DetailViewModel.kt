package com.galvezsh.paging3.presentation.detailScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.galvezsh.paging3.domain.GetCharacterByIdUseCase
import com.galvezsh.paging3.presentation.model.CharacterModel
import com.galvezsh.paging3.presentation.model.CharacterPlanetModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor( private val getCharacterByIdUseCase: GetCharacterByIdUseCase ): ViewModel() {

    private var _character = MutableStateFlow( CharacterModel( 1, "", false, "", "", "", CharacterPlanetModel("", ""), CharacterPlanetModel("", "") ) )
    var character: StateFlow< CharacterModel > = _character

    private val _isLoading = MutableStateFlow( false )
    val isLoading: StateFlow< Boolean > = _isLoading

    private val _error = MutableStateFlow( false )
    val error: StateFlow< Boolean > = _error

    private var oneTimeExecution = false

    fun loadId( id: Int ) {
        viewModelScope.launch {
            _isLoading.value = true
            val character = getCharacterByIdUseCase( id )

            if (character != null) {
                _character.value = character.toMap()
                _error.value = false

            } else {
                _error.value = true
            }
            _isLoading.value = false
        }
    }

    fun getOneTimeExecution(): Boolean {
        return oneTimeExecution
    }

    fun setOneTimeExecution() {
        oneTimeExecution = true
    }
}