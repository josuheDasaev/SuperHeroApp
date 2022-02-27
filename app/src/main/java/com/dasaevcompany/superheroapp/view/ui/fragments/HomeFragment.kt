package com.dasaevcompany.superheroapp.view.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.dasaevcompany.superheroapp.R
import com.dasaevcompany.superheroapp.databinding.FragmentHomeBinding
import com.dasaevcompany.superheroapp.databinding.ViewInformationBinding
import com.dasaevcompany.superheroapp.model.Hero
import com.dasaevcompany.superheroapp.view.adapters.HeroAdapter
import com.dasaevcompany.superheroapp.view.adapters.HeroListener
import com.dasaevcompany.superheroapp.viewmodel.SuperHeroViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), HeroListener {

    private val service : SuperHeroViewModel by viewModels()
    private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        observers()
    }

    private fun initData(){
        service.getSuperHero()
        binding.loading.visibility = View.VISIBLE
    }

    private fun observers(){
        service.superHero.observe(requireActivity()) {
            if (it.isNotEmpty()) {
                val adapter = HeroAdapter(it, this)
                binding.recycler.adapter = adapter
            }
        }

        service.isLoading.observe(requireActivity()){
            if (!it){
                binding.loading.visibility = View.GONE
            }
        }
    }

    @SuppressLint("InflateParams")
    override fun clickOnHero(hero: Hero) {
        val dialog = BottomSheetDialog(requireContext())
        val view = layoutInflater.inflate(R.layout.view_information, null)
        val bindingInfo = ViewInformationBinding.bind(view)

        printInfoHero(hero, bindingInfo)

        dialog.setCancelable(true)
        dialog.setContentView(bindingInfo.root)
        dialog.show()
    }

    private fun printInfoHero(hero: Hero, bindingInfo : ViewInformationBinding){
        val photo = hero.picture.path+"."+hero.picture.extension
        val photoNew = photo.replace("http","https")

        Glide.with(requireActivity())
            .load(photoNew)
            .centerCrop()
            .circleCrop()
            .into(bindingInfo.imHero)

        bindingInfo.txNameHero.text = hero.name
        bindingInfo.txUpdateHero.text = String.format(getString(R.string.update),hero.update)
        bindingInfo.txIdHero.text = String.format(getString(R.string.id),hero.id)
        if(hero.description.isNotEmpty()){
            bindingInfo.txDescriptionHero.text = String.format(getString(R.string.descriptionHero),hero.description)
        } else {
            bindingInfo.txDescriptionHero.text = getString(R.string.no_description)
        }
    }

}