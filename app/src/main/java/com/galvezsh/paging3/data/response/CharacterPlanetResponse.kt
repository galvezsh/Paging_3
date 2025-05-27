package com.galvezsh.paging3.data.response

import com.galvezsh.paging3.presentation.model.CharacterPlanetModel
import com.google.gson.annotations.SerializedName

data class CharacterPlanetResponse(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
) {

    fun toMap(): CharacterPlanetModel {
        return CharacterPlanetModel(
            name = name,
            url = url
        )
    }
}
