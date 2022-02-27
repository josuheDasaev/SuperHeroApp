package com.dasaevcompany.superheroapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dasaevcompany.superheroapp.data.usecase.SuperHeroUseCase
import com.dasaevcompany.superheroapp.model.Data
import com.dasaevcompany.superheroapp.model.Hero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SuperHeroViewModel @Inject constructor(
    private val service: SuperHeroUseCase
): ViewModel() {

    var superHero =  MutableLiveData<List<Hero>>()
    var superHeroSearch =  MutableLiveData<List<Hero>>()


    var isLoading = MutableLiveData<Boolean>()
    var isFailed = MutableLiveData<Boolean>()


    fun getSuperHero(){
        viewModelScope.launch {
            service.getSuperHero(object : Callback<Data> {
                override fun onResponse(call: Call<Data>, response: Response<Data>) {
                    val data = response.body()
                    val list = data?.data?.results
                    superHero.postValue(list!!)
                    processFinished(false)
                }
                override fun onFailure(call: Call<Data>, t: Throwable) {
                    processFinished(true)
                }
            })
        }
    }

    fun searchSuperHero(name : String){
        viewModelScope.launch {
            service.searchSuperHero(name,object : Callback<Data>{
                override fun onResponse(call: Call<Data>, response: Response<Data>) {
                    val data = response.body()
                    val list = data?.data?.results
                    superHeroSearch.postValue(list!!)
                    processFinished(false)
                }
                override fun onFailure(call: Call<Data>, t: Throwable) {
                    processFinished(true)
                }
            })
        }
    }

    fun processFinished(failed : Boolean){
        isLoading.value = false
        isFailed.value = failed
    }
}