/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonrocket.R
import com.example.pokemonrocket.database.Pokemon
import java.text.SimpleDateFormat
import java.util.*


/**
 * Takes a list of SleepNights and converts and formats it into one string for display.
 *
 * For display in a TextView, we have to supply one string, and styles are per TextView, not
 * applicable per word. So, we build a formatted string using HTML. This is handy, but we will
 * learn a better way of displaying this data in a future lesson.
 *
 * @param   nights - List of all SleepNights in the database.
 * @param   resources - Resources object for all the resources defined for our app.
 *
 * @return  Spanned - An interface for text that has formatting attached to it.
 *           See: https://developer.android.com/reference/android/text/Spanned
 */
fun convertNameDurationToFormatted(name:String, res:Resources):String{
    return res.getString(R.string.name_pokemon,name)
}
fun convertTypePowerDurationToFormatted(type:String, power:Int, res:Resources):String{
    return res.getString(R.string.type_power, type, power)
}
fun formatPokemon(pokemons: List<Pokemon>, resources: Resources): Spanned {
    val sb = StringBuilder()
    sb.apply {
       // append(resources.getString(R.string.title))
        Log.i("Util","Rendor Pokemon to Inventory")
        pokemons.forEach {
            Log.i("Fetch Records", "Id: ${it.pokemonId}, Name: ${it.name}, Type: ${it.type}, Power: ${it.power}")
            append("<br>")
            append(resources.getString(R.string.name_lebel))
            append("\t${it.name}<br>")
            append(resources.getString(R.string.type_lebel))
            append("\t${it.type}   ")
            append(resources.getString(R.string.power_lebel))
            append("\t${it.power}<br>")
        }
    }
    // fromHtml is deprecated for target API without a flag, but since our minSDK is 19, we
    // can't use the newer version, which requires minSDK of 24
    //https://developer.android.com/reference/android/text/Html#fromHtml(java.lang.String,%20int)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        @Suppress("DEPRECATION")
        return Html.fromHtml(sb.toString())
    }
}

class TextItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)