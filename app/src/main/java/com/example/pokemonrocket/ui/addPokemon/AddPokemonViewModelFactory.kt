package com.example.pokemonrocket.ui.addPokemon

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokemonrocket.database.PokemonDatabaseDao

class AddPokemonViewModelFactory(
    private val dataSource: PokemonDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddPokemonViewModel::class.java)) {
            return AddPokemonViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
