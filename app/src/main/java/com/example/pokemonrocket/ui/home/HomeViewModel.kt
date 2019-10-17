package com.example.pokemonrocket.ui.home

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemonrocket.database.PokemonDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel( val dataSource: PokemonDatabaseDao,
                     application: Application
) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigateToInventory = MutableLiveData<Boolean>()
    val navigateToInventory: LiveData<Boolean> get() = _navigateToInventory

    init {
        Log.i("HomeViewModel", "HomeViewModel created!")
    }
    fun onClickInventory(){
        uiScope.launch {
            _navigateToInventory.value = true
        }
    }
    fun doneNavigating(){
        _navigateToInventory.value = null;
    }
    override fun onCleared() {
        super.onCleared()
        Log.i("HomeViewModel", "HomeViewModel destroyed!")
    }
}