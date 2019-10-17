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
import com.google.android.material.snackbar.Snackbar

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
        val addPokemonViewModel = ViewModelProviders.of(this, viewModelFactory).get(AddPokemonViewModel::class.java)

        binding.addPokemonViewModel = addPokemonViewModel
        binding.setLifecycleOwner(this)
//        addPokemonViewModel.navigateToInventory.observe(this, Observer { click ->
//            click?.let {
//                this.findNavController().navigate(
//                    addPokemonViewModel.actionNavHomeToInventoryFragment()
//                )
//                homeViewModel.doneNavigating()
//            }
//        })


        addPokemonViewModel.showSnackBarEvent.observe(this, Observer {
            if (it == true) { // Observed state is true.
                Snackbar.make(
                    activity!!.findViewById(android.R.id.content),
                    getString(R.string.insert_message),
                    Snackbar.LENGTH_SHORT // How long to display the message.
                ).show()
                // Reset state to make sure the toast is only shown once, even if the device
                // has a configuration change.
                addPokemonViewModel.doneShowingSnackbar()
            }
        })
        return binding.root
    }


}
