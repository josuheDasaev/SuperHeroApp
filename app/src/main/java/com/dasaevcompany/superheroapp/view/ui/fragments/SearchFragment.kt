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
import com.dasaevcompany.superheroapp.databinding.FragmentSearchBinding
import com.dasaevcompany.superheroapp.databinding.ViewInformationBinding
import com.dasaevcompany.superheroapp.model.Hero
import com.dasaevcompany.superheroapp.utilities.ConnectivityUtil
import com.dasaevcompany.superheroapp.utilities.VerifyField
import com.dasaevcompany.superheroapp.utilities.VerifyFieldListener
import com.dasaevcompany.superheroapp.view.adapters.HeroAdapter
import com.dasaevcompany.superheroapp.view.adapters.HeroListener
import com.dasaevcompany.superheroapp.viewmodel.SuperHeroViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(),VerifyFieldListener, HeroListener {

    private lateinit var binding: FragmentSearchBinding
    private val service : SuperHeroViewModel by viewModels()
    private var search = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        observers()
    }

    private fun initData(){
        checkSearch()
    }

    private fun observers(){
        service.superHeroSearch.observe(requireActivity()){
            if (search){
                val adapter = HeroAdapter(it, this)
                binding.recycler.adapter = adapter
                if (it.isEmpty()){
                    binding.loading.visibility = View.VISIBLE
                } else{
                    binding.loading.visibility = View.GONE
                }
                search = false
            }
        }
    }

    private fun checkSearch(){
        /** Check field to verify search**/
        val check = VerifyField(this)
        check.checkField(binding.etSearch)

        binding.ilSearch.isEndIconVisible =
            binding.etSearch.text.toString().isNotEmpty()

        binding.ilSearch.setEndIconOnClickListener {
            binding.etSearch.setText("")
        }

    }

    private fun internet():Boolean{
        return ConnectivityUtil().internet(requireActivity())
    }

    override fun verifyField(verify: Boolean) {
        val searchString = binding.etSearch.text.toString()
        val empty = searchString.isNotEmpty()
        binding.ilSearch.isEndIconVisible = empty
        if (empty){
            search = true
            if (internet()){
                service.searchSuperHero(searchString)
            }
        } else {
            search = false
            val adapter = HeroAdapter(listOf(), this)
            binding.loading.visibility = View.VISIBLE
            binding.recycler.adapter = adapter
        }
    }

    @SuppressLint("InflateParams")
    override fun clickOnHero(hero: Hero) {
        val dialog = BottomSheetDialog(requireContext())
        val view = layoutInflater.inflate(R.layout.view_information, null)
        val bindingInfo = ViewInformationBinding.bind(view)

        printInfoHero(hero,bindingInfo)

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