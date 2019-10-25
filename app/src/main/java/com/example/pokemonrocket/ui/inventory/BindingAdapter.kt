package com.example.pokemonrocket.ui.inventory

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.android.trackmysleepquality.convertNameDurationToFormatted
import com.example.android.trackmysleepquality.convertTypePowerDurationToFormatted
import com.example.pokemonrocket.database.Pokemon

@BindingAdapter("nameDurationFormatted")
fun TextView.setSleepDurationFormatted(item: Pokemon) {
    text = convertNameDurationToFormatted(item.name, context.resources)
}


@BindingAdapter("typePowerString")
fun TextView.setSleepQualityString(item: Pokemon) {
    text = convertTypePowerDurationToFormatted(item.type, item.power, context.resources)
}
