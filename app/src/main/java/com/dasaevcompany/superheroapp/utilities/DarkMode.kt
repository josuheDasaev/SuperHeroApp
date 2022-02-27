package com.dasaevcompany.superheroapp.utilities

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.dasaevcompany.superheroapp.R

class DarkMode(context: Context) {

    private val contextApp = context

    fun getThemeCurrent(){
        val theme = getCurrentTheme()
        if(theme == "ON"){
            AppCompatDelegate
                .setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        if (theme == "OFF") {
            AppCompatDelegate
                .setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    fun isDarkMode(): String{
        return getCurrentTheme()
    }

    fun darkMode(themeDark : String){
        if(themeDark == "ON"){
            AppCompatDelegate
                .setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        if (themeDark == "OFF") {
            AppCompatDelegate
                .setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        if (themeDark == "AUTO"){
            AppCompatDelegate
                .setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
        addCurrentTheme(themeDark)
    }

    private fun addCurrentTheme(themeDark : String){
        val sharedPreference =  contextApp.getSharedPreferences(
            contextApp.getString(R.string.Dark_mode_key),
            Context.MODE_PRIVATE)

        with(sharedPreference.edit()){
            putString("DarkMode", themeDark)
            apply()
        }
    }

    private fun getCurrentTheme(): String{

        val sharedPreference =  contextApp.getSharedPreferences(
            contextApp.getString(R.string.Dark_mode_key),
            Context.MODE_PRIVATE)

        return sharedPreference.getString("DarkMode","AUTO").toString()
    }
}