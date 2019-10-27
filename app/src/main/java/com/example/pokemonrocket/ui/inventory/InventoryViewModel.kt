package com.example.pokemonrocket.ui.inventory

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.android.trackmysleepquality.formatPokemon
import com.example.pokemonrocket.database.PokemonDatabaseDao
import kotlinx.coroutines.*

class InventoryViewModel(val dataSource: PokemonDatabaseDao,
                    application: Application
) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigateToInsert = MutableLiveData<Boolean>()
    val navigateToInsert: LiveData<Boolean> get() = _navigateToInsert

    private val _navigateToEdit = MutableLiveData<Long>()
    val navigateToEdit: LiveData<Long> get() = _navigateToEdit

    val database = dataSource

    val pokemons = database.getAllPokemon()

    init {
        Log.i("InventoryViewModel", "InventoryViewModel created!")
        pokemons.value?.forEach {
            Log.i("Fetch Records", "Id: ${it.pokemonId}, Name: ${it.name}, Type: ${it.type}, Power: ${it.power}")
        }
    }

    val pokemonString = Transformations.map(pokemons) { pokemons ->
        formatPokemon(pokemons, application.resources)
    }


    private var _showSnackbarEvent = MutableLiveData<Boolean?>()
    val showSnackBarEvent: LiveData<Boolean?>
        get() = _showSnackbarEvent
    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = null
    }
    val clearButtonVisible = Transformations.map(pokemons){
        it.isNotEmpty()
    }

    fun onClear() {
        uiScope.launch {
            // Clear the database table.
            clear()
            _showSnackbarEvent.value = true
        }
    }
    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }



    fun onClickAdd(){
        uiScope.launch {
            _navigateToInsert.value = true
        }
    }
    fun doneNavigating(){
        _navigateToInsert.value = null;
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("InventoryViewModel", "InventoryViewModel destroyed!")
    }
    fun onPokemonClicked(id: Long) {

        _navigateToEdit.value = id
    }
    fun onEditNavigated() {
        _navigateToEdit.value = null
    }



}