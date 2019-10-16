package com.example.pokemonrocket.ui.inventory


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
import com.example.pokemonrocket.databinding.FragmentInventoryBinding

/**
 * A simple [Fragment] subclass.
 */
class InventoryFragment : Fragment() {
    private lateinit var binding: FragmentInventoryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentInventoryBinding>(inflater,R.layout.fragment_inventory,container,false)
        val application = requireNotNull(this.activity).application
        val dataSource = PokemonDatabase.getInstance(application).pokemonDatabaseDao
        val viewModelFactory = InventoryViewmodelFactory(dataSource,application)
        val inventortViewModel = ViewModelProviders.of(this, viewModelFactory).get(InventoryViewModel::class.java)
        binding.setLifecycleOwner(this)
        binding.inventortViewModel = inventortViewModel
        inventortViewModel.navigateToInsert.observe(this, Observer { click ->
            click?.let {
                this.findNavController().navigate(
                    InventoryFragmentDirections.actionInventoryFragmentToAddPokemonFragment()
                )
                inventortViewModel.doneNavigating()
            }
        })
        return binding.root
    }


}