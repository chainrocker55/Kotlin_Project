package com.example.pokemonrocket.ui.search
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.net.HttpURLConnection
import java.net.URL

class WebAPI (val name:String){

    fun sendGet() {
        val url = URL("https://pokeapi.co/api/v2/pokemon/ditto").readText()
        Log.i("API", url)


//        with(url.openConnection() as HttpURLConnection) {
//            requestMethod = "GET"  // optional default is GET
//
//            println("\nSent 'GET' request to URL : $url; Response Code : $responseCode")
//
//            inputStream.bufferedReader().use {
//                it.lines().forEach { line ->
//                    println(line)
//                    Log.i("Search", line.toString())
//                }
//            }
//        }
    }
}