package com.example.pokemonrocket.ui.editPokemon

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokemonrocket.database.PokemonDatabaseDao

class EditPokemonViewModelFactory(
    private val dataSource: PokemonDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditPokemonViewModel::class.java)) {
            return EditPokemonViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
