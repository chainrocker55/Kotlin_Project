package com.example.pokemonrocket.API

import retrofit2.Call
import retrofit2.http.GET
import com.example.pokemonrocket.model.DataModel
import com.google.gson.JsonObject


interface ApiInterface {

    @GET("pokemon")
    fun getPokemon(): Call<JsonObject>

}