package com.example.pokemonrocket.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "PokemonRocketTable")
data class Pokemon(
    @PrimaryKey(autoGenerate = true)
    var pokemonId: Long = 0L,

    @ColumnInfo(name = "name")
    val name: String = "",

    @ColumnInfo(name = "type")
    var type: String = "",


    @ColumnInfo(name = "power")
    var power: Int = 0
)