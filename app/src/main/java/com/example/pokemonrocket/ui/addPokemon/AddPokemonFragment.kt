package com.example.pokemonrocket.ui.addPokemon


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

import com.example.pokemonrocket.R
import com.example.pokemonrocket.database.PokemonDatabase
import com.example.pokemonrocket.databinding.FragmentAddPokemonBinding

/**
 * A simple [Fragment] subclass.
 */
class AddPokemonFragment : Fragment() {
    private lateinit var binding: FragmentAddPokemonBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentAddPokemonBinding>(inflater,R.layout.fragment_add_pokemon,container,false)
        val application = requireNotNull(this.activity).application
        val dataSource = PokemonDatabase.getInstance(application).pokemonDatabaseDao
        val viewModelFactory = AddPokemonViewModelFactory(dataSource,application)
        val homeViewModel = ViewModelProviders.of(this, viewModelFactory).get(AddPokemonViewModel::class.java)
        binding.setLifecycleOwner(this)
        binding.addPokemonViewModel = homeViewModel
//        addPokemonViewModel.navigateToInventory.observe(this, Observer { click ->
//            click?.let {
//                this.findNavController().navigate(
//                    addPokemonViewModel.actionNavHomeToInventoryFragment()
//                )
//                homeViewModel.doneNavigating()
//            }
//        })
        return binding.root
    }


}
