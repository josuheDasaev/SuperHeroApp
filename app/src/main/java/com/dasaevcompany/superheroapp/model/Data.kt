package com.dasaevcompany.superheroapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Data:Serializable {
    @SerializedName("data")  var data = Result()
    @SerializedName("code")  var code = 0
    @SerializedName("copyright")  var copy = ""
}