package com.dasaevcompany.superheroapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Picture: Serializable{
    @SerializedName("path")       var path: String = ""
    @SerializedName("extension")  var extension: String = ""
}