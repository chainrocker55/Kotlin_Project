package com.example.pokemonrocket.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface PokemonDatabaseDao{
    @Insert
    fun insert(pokemon: Pokemon)

    @Update
    fun update(pokemon: Pokemon)

    @Query("SELECT * FROM PokemonRocketTable WHERE pokemonId = :key")
    fun get(key: Long) : Pokemon

    @Query("DELETE FROM PokemonRocketTable")
    fun clear()


    @Query("SELECT * FROM PokemonRocketTable ORDER BY pokemonId DESC")
    fun  getAllPokemon(): LiveData<List<Pokemon>>

}