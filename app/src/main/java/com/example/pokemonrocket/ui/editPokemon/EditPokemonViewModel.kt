package com.example.pokemonrocket.ui.editPokemon

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.pokemonrocket.database.PokemonDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class EditPokemonViewModel(val dataSource: PokemonDatabaseDao,
                           application: Application
) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

//    private val _insert = MutableLiveData<Boolean>()
//    val navigateToInventory: LiveData<Boolean> get() = _navigateToInventory
//
//    fun onClickAdd(){
//        uiScope.launch {
//            _navigateToInventory.value = true
//        }
//    }
//    fun doneNavigating(){
//        _navigateToInventory.value = null;
//    }
}