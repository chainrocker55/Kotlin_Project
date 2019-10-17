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
import com.google.android.material.snackbar.Snackbar

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

        binding.inventortViewModel = inventortViewModel
        binding.setLifecycleOwner(this)
        inventortViewModel.navigateToInsert.observe(this, Observer { click ->
            click?.let {
                this.findNavController().navigate(
                    InventoryFragmentDirections.actionInventoryFragmentToAddPokemonFragment()
                )
                inventortViewModel.doneNavigating()
            }
        })
        inventortViewModel.showSnackBarEvent.observe(this, Observer {
            if (it == true) { // Observed state is true.
                Snackbar.make(
                    activity!!.findViewById(android.R.id.content),
                    getString(R.string.cleared_message),
                    Snackbar.LENGTH_SHORT // How long to display the message.
                ).show()
                // Reset state to make sure the toast is only shown once, even if the device
                // has a configuration change.
                inventortViewModel.doneShowingSnackbar()
            }
        })
        return binding.root
    }


}
