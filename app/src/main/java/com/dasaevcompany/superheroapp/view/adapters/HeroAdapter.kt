package com.dasaevcompany.superheroapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dasaevcompany.superheroapp.R
import com.dasaevcompany.superheroapp.databinding.ViewSuperHeroBinding
import com.dasaevcompany.superheroapp.model.Hero

class HeroAdapter (
    private val list: List<Hero>,
    private val listener: HeroListener
) :
    RecyclerView.Adapter<HeroAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.view_super_hero,parent,false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.createView(item)
        holder.click(item,listener)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val binding = ViewSuperHeroBinding.bind(view)

        fun createView(hero : Hero) {
            val photo = hero.picture.path+"."+hero.picture.extension
            val photoNew = photo.replace("http","https")
            Glide.with(itemView.context)
                .load(photoNew)
                .centerCrop()
                .into(binding.imHero)
            binding.txNameHero.text = hero.name
        }

        fun click(hero: Hero,listener: HeroListener){
            binding.item.setOnClickListener {
                listener.clickOnHero(hero)
            }
        }

    }
}