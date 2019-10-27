package com.example.pokemonrocket.ui.inventory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.convertNameDurationToFormatted
import com.example.android.trackmysleepquality.convertTypePowerDurationToFormatted
import com.example.pokemonrocket.R
import com.example.pokemonrocket.database.Pokemon
class PokemonDiffCallback : DiffUtil.ItemCallback<Pokemon>() {
    override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem.pokemonId == newItem.pokemonId
    }

    override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem == newItem
    }
}
class InventoryAdapter : RecyclerView.Adapter<InventoryAdapter.ViewHolder>(){
    var data = listOf<Pokemon>()
        set(value){
            field = value
            notifyDataSetChanged()
        }
    class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.name)
        val type_power: TextView = itemView.findViewById(R.id.type_power)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_pokemon, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
//        holder.textView.text = item.name.toString()
        holder.bind(item)

    }

    fun ViewHolder.bind(item: Pokemon) {
        val res = itemView.context.resources
        name.text = convertNameDurationToFormatted(item.name, res)
        type_power.text = convertTypePowerDurationToFormatted(item.type, item.power, res)
    }

}
class InventoryListener(val clickListener: (pokemonId: Long) -> Unit) {
    fun onClick(pokemon: Pokemon) = clickListener(pokemon.pokemonId)
}