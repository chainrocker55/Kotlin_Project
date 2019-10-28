package com.example.pokemonrocket.ui.addPokemon


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.transition.FragmentTransitionSupport

import com.example.pokemonrocket.R
import com.example.pokemonrocket.database.PokemonDatabase
import com.example.pokemonrocket.databinding.FragmentAddPokemonBinding
import com.example.pokemonrocket.databinding.FragmentInventoryBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_add_pokemon.*
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
const val KEY_NAME = "name_key"
const val KEY_TYPE = "type_key"
const val KEY_POWER = "power_key"
class AddPokemonFragment : Fragment() {
    private lateinit var binding: FragmentAddPokemonBinding
    private var name : String = ""
    private  var type : String  = ""
    private  var power : String  = ""
    @SuppressLint("BinaryOperationInTimber")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Log.i("AddPokemonFragment", "onCreateView called")
        // Inflate the layout for this fragment


        binding = DataBindingUtil.inflate<FragmentAddPokemonBinding>(inflater,R.layout.fragment_add_pokemon,container,false)
        val application = requireNotNull(this.activity).application
        val dataSource = PokemonDatabase.getInstance(application).pokemonDatabaseDao
        val viewModelFactory = AddPokemonViewModelFactory(dataSource,application)
        val addPokemonViewModel = ViewModelProviders.of(this, viewModelFactory).get(AddPokemonViewModel::class.java)


        binding.addPokemonViewModel = addPokemonViewModel
        binding.setLifecycleOwner(this)

        if (savedInstanceState != null) {
            name = savedInstanceState.getString(KEY_NAME, "")
            type = savedInstanceState.getString(KEY_TYPE, "")
            power = savedInstanceState.getString(KEY_POWER, "")
            //showCurrentState()
            addPokemonViewModel.onSetValue(name,type,power)
            Timber.i("Name = ${name} Type =  $type} Power =  ${power}")
        }

        binding.apply {
//            txtName.setText(name)
//            txtType.setText(type)
//            txtPower.setText(power)
//
//            invalidateAll()
            btnSave.setOnClickListener {
                if(txtName.text.toString().length > 0 && txtType.text.toString().length > 0 && txtPower.text.toString().length > 0){
                    addPokemonViewModel.onSave(txtName.text.toString(),txtType.text.toString(),txtPower.text.toString())
                }else{
                    Toast.makeText(context, "Please to check input values not null!!", Toast.LENGTH_SHORT).show()
                }

            }
        }
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
                hideKeyboard()
                this.findNavController().navigate(
                    AddPokemonFragmentDirections.actionAddPokemonFragmentToInventoryFragment()
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
//    private fun showCurrentState(){
//        binding.apply {
//            txtName.setText(name)
//            txtType.setText(type)
//            txtPower.setText(power)
//        }
//    }



//    override fun onAttach(context: Context?) {
//        super.onAttach(context)
//        Log.i("AddPokemonFragment", "onAttach called")
//    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        Log.i("AddPokemonFragment", "onCreate called")
//    }
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        Log.i("AddPokemonFragment", "onActivityCreated called")
//    }
//    override fun onStart() {
//        super.onStart()
//        Log.i("AddPokemonFragment", "onStart called")
//    }
//    override fun onResume() {
//        super.onResume()
//        Log.i("AddPokemonFragment", "onResume called")
//    }
//    override fun onPause() {
//        super.onPause()
//        Log.i("AddPokemonFragment", "onPause called")
//    }
//    override fun onStop() {
//        super.onStop()
//        Log.i("AddPokemonFragment", "onStop called")
//    }
//    override fun onDestroyView() {
//        super.onDestroyView()
//        Log.i("AddPokemonFragment", "onDestroyView called")
//    }
//    override fun onDetach() {
//        super.onDetach()
//        Log.i("AddPokemonFragment", "onDetach called")
//    }



}
