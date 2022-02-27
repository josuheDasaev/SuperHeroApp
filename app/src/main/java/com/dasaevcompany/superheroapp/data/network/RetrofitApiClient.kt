package com.dasaevcompany.superheroapp.data.network

import com.dasaevcompany.superheroapp.model.Data
import retrofit2.Call
import retrofit2.http.*

interface RetrofitApiClient {

    @GET("characters?limit=100&ts=1&apikey=90d3b5a9be0b68f28667293cc85958e8&hash=34837cc426cb2f80d763a6ae588daf7e")
    fun getSuperHero(): Call<Data>

    @GET("characters?&ts=1&apikey=90d3b5a9be0b68f28667293cc85958e8&hash=34837cc426cb2f80d763a6ae588daf7e")
    fun searchSuperHero(@Query("nameStartsWith") name : String): Call<Data>

}

/*
@GET("josuhea7x@hotmail-com/{id}/.json")
fun getItem(@Path("id") id : String): Call<Hero>

@PUT("josuhea7x@hotmail-com/{id}/.json")
fun addItem(@Path("id") id : String, @Body item: Hero): Call<Hero>

@POST("josuhea7x@hotmail-com/.json")
//fun addItemPost(@Body item: Hero): Call<Hero>*/
