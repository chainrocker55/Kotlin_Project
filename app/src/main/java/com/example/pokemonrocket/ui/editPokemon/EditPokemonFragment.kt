package com.example.pokemonrocket.ui.editPokemon


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

import com.example.pokemonrocket.R
import com.example.pokemonrocket.database.PokemonDatabase
import com.example.pokemonrocket.databinding.FragmentEditPokemonBinding
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
const val KEY_NAME = "name_key"
const val KEY_TYPE = "type_key"
const val KEY_POWER = "power_key"
private var name : String = ""
private  var type : String  = ""
private  var power : String  = ""
class EditPokemonFragment : Fragment() {
    private lateinit var binding: FragmentEditPokemonBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_edit_pokemon,container,false)
        val application = requireNotNull(this.activity).application
        val arguments = EditPokemonFragmentArgs.fromBundle(arguments!!)
        val dataSource = PokemonDatabase.getInstance(application).pokemonDatabaseDao
        val viewModelFactory = EditPokemonViewModelFactory(dataSource, arguments.pokemonId ,application)
        val editPokemonViewModel = ViewModelProviders.of(this, viewModelFactory).get(EditPokemonViewModel::class.java)

        binding.editPokemonViewModel = editPokemonViewModel
        binding.setLifecycleOwner(this)
        if (savedInstanceState != null) {
            name = savedInstanceState.getString(KEY_NAME, "")
            type = savedInstanceState.getString(KEY_TYPE, "")
            power = savedInstanceState.getString(KEY_POWER, "")
            //showCurrentState()
            editPokemonViewModel.onSetValue(name,type,power)
            Timber.i("Name = ${name} Type =  $type} Power =  ${power}")
        }
        binding.apply {
            btnEdit.setOnClickListener {
                editPokemonViewModel.onEdit(txtName.text.toString(),txtType.text.toString(),txtPower.text.toString())
            }
        }
        editPokemonViewModel.navigateToInventory.observe(this, Observer {
            if (it==true){
                this.findNavController().navigate(
                    EditPokemonFragmentDirections.actionEditPokemonFragmentToInventoryFragment()
                )
                editPokemonViewModel.doneNavigating()
            }
        })
        editPokemonViewModel.showSnackBarEvent.observe(this, Observer {
            if (it == true) { // Observed state is true.
                Snackbar.make(
                    activity!!.findViewById(android.R.id.content),
                    getString(R.string.update_message),
                    Snackbar.LENGTH_SHORT // How long to display the message.
                ).show()
                // Reset state to make sure the toast is only shown once, even if the device
                // has a configuration change.
                editPokemonViewModel.doneShowingSnackbar()
                hideKeyboard()
                this.findNavController().navigate(
                   EditPokemonFragmentDirections.actionEditPokemonFragmentToInventoryFragment()
                )

            }
        })

        return binding.root
    }
    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Activity.hideKeyboard() {
        if (currentFocus == null) View(this) else currentFocus?.let { hideKeyboard(it) }
    }
    @SuppressLint("ServiceCast")
    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        name = binding.txtName.text.toString()
        type = binding.txtType.text.toString()
        power = binding.txtPower.text.toString()
        outState.putString(KEY_NAME, name)
        outState.putString(KEY_TYPE, type)
        outState.putString(KEY_POWER, power)

        Timber.i("onSaveInstanceState Called")
    }
}
