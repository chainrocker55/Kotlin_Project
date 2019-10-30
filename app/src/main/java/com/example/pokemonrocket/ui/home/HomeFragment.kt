package com.example.pokemonrocket.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.pokemonrocket.R
import com.example.pokemonrocket.database.PokemonDatabase
import com.example.pokemonrocket.databinding.FragmentHomeBinding
import com.gigamole.navigationtabstrip.NavigationTabStrip

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater,R.layout.fragment_home,container,false)
        val application = requireNotNull(this.activity).application
        val dataSource = PokemonDatabase.getInstance(application).pokemonDatabaseDao
        val viewModelFactory = HomeViewModelFactory(dataSource,application)
        val homeViewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
        binding.setLifecycleOwner(this)
        binding.homeViewModel = homeViewModel
        homeViewModel.navigateToInventory.observe(this, Observer { click ->
            click?.let {
                this.findNavController().navigate(
                    HomeFragmentDirections.actionNavHomeToInventoryFragment()
                )
                homeViewModel.doneNavigating()
            }
        })
        return binding.root
    }
}