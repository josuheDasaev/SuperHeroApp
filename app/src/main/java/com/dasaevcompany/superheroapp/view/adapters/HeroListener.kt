package com.dasaevcompany.superheroapp.view.adapters

import com.dasaevcompany.superheroapp.model.Hero

interface HeroListener {
    fun clickOnHero(hero : Hero)
}