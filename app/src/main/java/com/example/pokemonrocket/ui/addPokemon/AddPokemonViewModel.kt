package com.example.pokemonrocket.ui.addPokemon

import android.app.Application
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.pokemonrocket.database.Pokemon
import com.example.pokemonrocket.database.PokemonDatabaseDao
import kotlinx.coroutines.*

class AddPokemonViewModel(
    val dataSource: PokemonDatabaseDao,
    application: Application
) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val database = dataSource

    private val _name = MutableLiveData<String>()
    val name : LiveData<String> get() = _name

    private val _type = MutableLiveData<String>()
    val type : LiveData<String> get() = _type

    private val _power = MutableLiveData<String>()
    val power : LiveData<String> get() = _power



    val nameTextVisible = Transformations.map(name){
        it == null
    }
    val typeTextVisible = Transformations.map(type){
        it == null
    }
    val powerTextVisible = Transformations.map(power){
        it == null
    }


    private var _showSnackbarEvent = MutableLiveData<Boolean?>()
    val showSnackBarEvent: LiveData<Boolean?>
        get() = _showSnackbarEvent
    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = null
    }

    fun onSetValue(nameState:String, typeState:String, powerState:String){
        _name.value = nameState
        _type.value = typeState
        _power.value = powerState
    }
    fun onSave(txtName:String,txtType:String,txtPower:String) {
        uiScope.launch {
            // Create a new night, which captures the current time,
            // and insert it into the database.
            _name.value = txtName
            _type.value = txtType
            _power.value = txtPower

            val newPokemon = Pokemon()
            newPokemon.name = name.value.toString()
            newPokemon.type = type.value.toString()
            newPokemon.power = power.value.toString().toInt()
            Log.i("Add Pokemon",name.value.toString()+" "+type.value.toString()+" "+power.value.toString())

            insert(newPokemon)
            _showSnackbarEvent.value = true
        }
    }
    private suspend fun insert(pokemon: Pokemon) {
        withContext(Dispatchers.IO) {
            database.insert(pokemon)
        }
    }
    override fun onCleared() {
        super.onCleared()
        Log.i("AddPokemonViewModel", "AddPokemonViewModel destroyed!")
    }



}


