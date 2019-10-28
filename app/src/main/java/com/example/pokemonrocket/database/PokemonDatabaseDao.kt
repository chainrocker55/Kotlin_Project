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

    @Query("SELECT * from PokemonRocketTable WHERE pokemonId = :key")
    fun getPokemonWithId(key: Long): LiveData<Pokemon>

    @Query("DELETE FROM PokemonRocketTable")
    fun clear()

    @Query("DELETE FROM pokemonrockettable WHERE pokemonId = :key")
    fun deleteById(key: Long)

    @Query("SELECT * FROM PokemonRocketTable ORDER BY pokemonId DESC")
    fun  getAllPokemon(): LiveData<List<Pokemon>>

}