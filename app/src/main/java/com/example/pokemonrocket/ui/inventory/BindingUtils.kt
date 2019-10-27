package com.example.pokemonrocket.ui.inventory

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.android.trackmysleepquality.convertNameDurationToFormatted
import com.example.android.trackmysleepquality.convertTypePowerDurationToFormatted
import com.example.pokemonrocket.database.Pokemon

@BindingAdapter("pokemonNameString")
fun TextView.setPokemonDurationFormatted(item: Pokemon?) {
    item?.let {
        text = convertNameDurationToFormatted(item.name, context.resources)
    }

}

@BindingAdapter("pokemonTypePowerString")
fun TextView.setSleepQualityString(item: Pokemon?) {
    item?.let {
        text = convertTypePowerDurationToFormatted(item.type, item.power, context.resources)
    }

}