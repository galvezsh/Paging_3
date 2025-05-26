package com.galvezsh.paging3.presentation.model

data class CharacterModel(
    val id: Short,
    val name: String,
    val isAlive: Boolean,
    val type: String,
    val gender: String,
    val image: String,
    val origin: String,
    val location: String,
)