package com.dasaevcompany.superheroapp

import android.app.Application
import com.dasaevcompany.superheroapp.utilities.DarkMode
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SuperHeroApp: Application(){
    override fun onCreate() {
        super.onCreate()
        DarkMode(this).getThemeCurrent()
    }
}