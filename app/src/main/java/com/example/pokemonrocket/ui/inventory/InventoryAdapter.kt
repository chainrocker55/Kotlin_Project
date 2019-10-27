package com.example.pokemonrocket.ui.inventory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonrocket.database.Pokemon
import com.example.pokemonrocket.databinding.ListItemPokemonBinding

class PokemonDiffCallback : DiffUtil.ItemCallback<Pokemon>() {
    override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem.pokemonId == newItem.pokemonId
    }

    override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem == newItem
    }
}
class InventoryAdapter(val clickListener: InventoryListener) :  ListAdapter<Pokemon, InventoryAdapter.ViewHolder>(PokemonDiffCallback()) {

    class ViewHolder(val binding: ListItemPokemonBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(
            item: Pokemon,
            clickListener: InventoryListener
        ) {
            binding.clickListener = clickListener
            binding.pokemon = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemPokemonBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
//        holder.textView.text = item.name.toString()
        holder.bind(item!!, clickListener)

    }

//    fun ViewHolder.bind(
//        item: Pokemon,
//        clickListener: InventoryListener
//    ) {
//
////        val res = itemView.context.resources
////        binding.name.text = convertNameDurationToFormatted(item.name, res)
////        binding.typePower.text = convertTypePowerDurationToFormatted(item.type, item.power, res)
//        binding.clickListener = clickListener
//        binding.pokemon = item
//        binding.executePendingBindings()
//    }
//
}
class InventoryListener(val clickListener: (pokemonId: Long) -> Unit) {
    fun onClick(pokemon: Pokemon) = clickListener(pokemon.pokemonId)
}