package com.dasaevcompany.superheroapp.data.usecase

import com.dasaevcompany.superheroapp.data.network.RetrofitApiClient
import com.dasaevcompany.superheroapp.model.Data
import retrofit2.Callback
import retrofit2.Retrofit
import javax.inject.Inject

class SuperHeroUseCase @Inject constructor(retrofit: Retrofit) {

    private val retrofitService = retrofit.create(RetrofitApiClient::class.java)

    fun getSuperHero(callBack: Callback<Data>){
        retrofitService.getSuperHero().enqueue(callBack)
    }

    fun searchSuperHero(name: String, callBack: Callback<Data>){
        retrofitService.searchSuperHero(name).enqueue(callBack)
    }

}