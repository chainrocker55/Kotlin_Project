package com.example.pokemonrocket.ui.editPokemon


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders

import com.example.pokemonrocket.R
import com.example.pokemonrocket.database.PokemonDatabase
import com.example.pokemonrocket.databinding.FragmentEditPokemonBinding

/**
 * A simple [Fragment] subclass.
 */
class EditPokemonFragment : Fragment() {
    private lateinit var binding: FragmentEditPokemonBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentEditPokemonBinding>(inflater,R.layout.fragment_edit_pokemon,container,false)
        val application = requireNotNull(this.activity).application
        val dataSource = PokemonDatabase.getInstance(application).pokemonDatabaseDao
        val viewModelFactory = EditPokemonViewModelFactory(dataSource,application)
        val editPokemonViewModel = ViewModelProviders.of(this, viewModelFactory).get(EditPokemonViewModel::class.java)
        binding.setLifecycleOwner(this)
        binding.editPokemonViewModel = editPokemonViewModel
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
