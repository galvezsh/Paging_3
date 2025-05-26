package com.galvezsh.paging3.data.response

import com.google.gson.annotations.SerializedName

data class InfoResponse(
    @SerializedName("count") val count: Short,
    @SerializedName("pages") val pages: Short,
    @SerializedName("next") val next: String?,
    @SerializedName("prev") val prev: String?
)
