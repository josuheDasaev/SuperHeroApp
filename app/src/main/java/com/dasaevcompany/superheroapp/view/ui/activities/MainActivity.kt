package com.dasaevcompany.superheroapp.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
import com.dasaevcompany.superheroapp.R
import com.dasaevcompany.superheroapp.databinding.ActivityMainBinding
import com.dasaevcompany.superheroapp.utilities.DarkMode
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var darkMode: DarkMode

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        darkMode = DarkMode(this)
        getCurrentTheme()

        /** Create function of navigation **/
        val navHostFragment = supportFragmentManager.findFragmentById(binding.fragmentContainer.id) as NavHostFragment
        NavigationUI.setupWithNavController(binding.menuNavigation, navHostFragment.navController)

        binding.mode.setOnClickListener {
            changeTheme()
        }

    }

    private fun getCurrentTheme() {
        when (darkMode.isDarkMode()) {
            "ON" -> {
                icon(R.drawable.ic_dark_mode)
            }
            "OFF" -> {
                icon(R.drawable.ic_day_mode)
            }
            "AUTO" -> {
                icon(R.drawable.ic_auto)
            }
        }
    }

    private fun changeTheme() {
        when (darkMode.isDarkMode()) {
            "ON" -> {
                icon(R.drawable.ic_day_mode)
                darkMode.darkMode("OFF")
            }
            "OFF" -> {
                icon(R.drawable.ic_auto)
                darkMode.darkMode("AUTO")
            }
            "AUTO" -> {
                icon(R.drawable.ic_dark_mode)
                darkMode.darkMode("ON")
            }
        }
    }

    private fun icon(icon: Int) {
        Glide.with(this)
            .load(icon)
            .into(binding.mode)
    }
}