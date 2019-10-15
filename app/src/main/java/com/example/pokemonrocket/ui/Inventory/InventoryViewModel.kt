package com.example.pokemonrocket.ui.Inventory

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pokemonrocket.database.PokemonDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class InventoryViewModel(val database: PokemonDatabaseDao,
                    application: Application
) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigateToInventory = MutableLiveData<Boolean>()
    val navigateToInventory: LiveData<Boolean> get() = _navigateToInventory
    fun onClickInventory(){
        uiScope.launch {
            _navigateToInventory.value = true
        }
    }
    fun doneNavigating(){
        _navigateToInventory.value = null;
    }
}