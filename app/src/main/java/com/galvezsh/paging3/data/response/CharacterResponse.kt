package com.galvezsh.paging3.data.response

import com.galvezsh.paging3.presentation.model.CharacterModel
import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("id") val id: Short,
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("type") val type: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("image") val image: String,
    @SerializedName("origin") val origin: CharacterPlanetResponse,
    @SerializedName("location") val location: CharacterPlanetResponse,
) {
    fun toMap(): CharacterModel {
        return CharacterModel(
            id = id,
            name = name,
            isAlive = status == "Alive",
            type = type,
            gender = gender,
            image = image,
            origin = origin.toMap(),
            location = location.toMap(),
        )
    }
}
