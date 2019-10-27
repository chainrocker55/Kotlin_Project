package com.example.pokemonrocket.ui.editPokemon

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.pokemonrocket.database.Pokemon
import com.example.pokemonrocket.database.PokemonDatabaseDao
import kotlinx.coroutines.*

class EditPokemonViewModel(val dataSource: PokemonDatabaseDao,
                           val pokemonId: Long,
                           application: Application
) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    val database = dataSource

    private val pokemon: LiveData<Pokemon>
    fun getPokemon() = pokemon
    private val _name = MutableLiveData<String>()
    val name : LiveData<String> get() = _name

    private val _type = MutableLiveData<String>()
    val type : LiveData<String> get() = _type

    private val _power = MutableLiveData<String>()
    val power : LiveData<String> get() = _power

    private var _showSnackbarEvent = MutableLiveData<Boolean?>()
    val showSnackBarEvent: LiveData<Boolean?>
        get() = _showSnackbarEvent
    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = null
    }

    //val pokemonString:

    init {
        Log.i("Edit Pokemon",pokemonId.toString())
        pokemon = database.getPokemonWithId(pokemonId)
        val pokemonString = Transformations.map(pokemon){
            Log.i("Edit Pokemon",it.name+" "+it.type+" "+it.power)
        }

//        val pokemonString = Transformations.map(pokemon){
//            _name.value = it.name
//            _type.value = it.type
//            _power.value = it.power.toString()
//            Log.i("Edit Pokemon",it.name+" "+it.type+" "+it.power)
//        }

    }
    fun onSetValue(nameState:String, typeState:String, powerState:String){
        _name.value = nameState
        _type.value = typeState
        _power.value = powerState
    }
    fun onEdit(txtName:String,txtType:String,txtPower:String) {
        uiScope.launch {
            // Create a new night, which captures the current time,
            // and insert it into the database.
            _name.value = txtName
            _type.value = txtType
            _power.value = txtPower

            Log.i("Edit Pokemon",name.value.toString()+" "+type.value.toString()+" "+power.value.toString())

            var oldPokemon : Pokemon = getPokemon(pokemonId)
            oldPokemon.name = name.value.toString()
            oldPokemon.type = type.value.toString()
            oldPokemon.power = power.value.toString().toInt()

            edit(oldPokemon)
            _showSnackbarEvent.value = true
        }
    }
    private suspend fun getPokemon(pokemonId:Long):Pokemon {
        return withContext(Dispatchers.IO) {
            database.get(pokemonId)
        }
    }
    private suspend fun edit(pokemon: Pokemon) {
        withContext(Dispatchers.IO) {
            database.update(pokemon)
        }
    }
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
    private val _navigateToInventory = MutableLiveData<Boolean?>()

    val navigateToInventory: LiveData<Boolean?>
        get() = _navigateToInventory

    fun doneNavigating() {
        _navigateToInventory.value = null
    }

    fun onClose() {
        _navigateToInventory.value = true
    }
}