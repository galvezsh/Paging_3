package com.galvezsh.paging3.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object MainScreen

@Serializable
data class DetailScreen( val characterId: Int )