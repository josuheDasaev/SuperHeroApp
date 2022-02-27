package com.dasaevcompany.superheroapp.model

import com.google.gson.annotations.SerializedName

class Hero {
    @SerializedName("id")    var id: Int = 0
    @SerializedName("name")  var name: String = ""
    @SerializedName("thumbnail")  var picture = Picture()
    @SerializedName("description")  var description: String = ""
    @SerializedName("modified")  var update: String = ""

}