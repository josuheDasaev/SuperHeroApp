package com.dasaevcompany.superheroapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Result:Serializable {
    @SerializedName("results")  var results = listOf<Hero>()
}