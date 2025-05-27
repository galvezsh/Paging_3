package com.galvezsh.paging3.presentation.model

data class CharacterModel(
    val id: Int,
    val name: String,
    val isAlive: Boolean,
    val type: String,
    val gender: String,
    val image: String,
    val origin: CharacterPlanetModel,
    val location: CharacterPlanetModel,
)