package com.example.pokemonrocket.ui.search


import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.pokemonrocket.API.ApiClient

import com.example.pokemonrocket.R
import com.github.kittinunf.fuel.Fuel
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import java.net.HttpURLConnection
import java.net.URL
import com.google.gson.Gson
import org.json.JSONObject


/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        val url = URL("https://pokeapi.co/api/v2/pokemon/ditto")
//        with(url.openConnection() as HttpURLConnection) {
//            requestMethod = "GET"  // optional default is GET
//
//            println("\nSent 'GET' request to URL : $url; Response Code : $responseCode")
//
//            inputStream.bufferedReader().use {
//                Log.i("API", it.readText())
//            }
//        }
        getData()



        return inflater.inflate(R.layout.fragment_search, container, false)
    }
    private fun getData() {
        val call: Call<JsonObject> = ApiClient.getClient.getPokemon()
        val jsonObject = JSONObject(Gson().toJson(call))
        Log.i("Reponse",jsonObject.toString())

    }

//    private fun sampleKo() {
//        val url:URL = URL("https://pokeapi.co/api/v2/pokemon/ditto")
//        //https://github.com/androidmads/KotlinFuelHttpSample/blob/master/app/src/main/java/com/androidmads/kotlinfuelhttpsample/MainActivity.kt
//        //https://github.com/kittinunf/Fuel/blob/1.16.0/README.md
//        try {
//            Fuel.get("https://pokeapi.co/api/v2/pokemon/ditto").responseString { request, response, result ->
//                //do something with response
//                result.fold({ d ->
//                    //do something with data
//                    Log.i("Data",d)
//                }, { err ->
//                    Log.i("Error","Error")
//                })
//            }
////            Fuel.post(url, listOf()).responseJson { request, response, result ->
////                Log.d("plzzzzz", result.get().content)
////                //onTaskCompleted(result.get().content)
////            }
//        } catch (e: Exception) {
//
//        } finally {
//
//        }
//    }
//    fun onTaskCompleted(response: String) {
//        Log.d("responsejson", response)
//
//        playersModelArrayList = getInfo(response)
//        customeAdapter = CustomeAdapter(this, playersModelArrayList!!)
//        listView!!.adapter = customeAdapter
//    }



}


