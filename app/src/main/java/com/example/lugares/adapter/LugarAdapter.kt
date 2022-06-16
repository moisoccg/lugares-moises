package com.example.lugares.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.lugares.databinding.LugarFilaBinding
import com.example.lugares.model.Lugar
import com.example.lugares.ui.lugar.LugarFragmentDirections

class LugarAdapter : RecyclerView.Adapter<LugarAdapter.LugarViewHolder>() {

    //Lista para almacenar informacio
    private var listaLugares = emptyList<Lugar>()

    inner class LugarViewHolder(private val itemBinding: LugarFilaBinding) :
            RecyclerView.ViewHolder(itemBinding.root){
                fun bind(lugar: Lugar){
                    itemBinding.lugarName.text = lugar.nombre
                    //Para navegar al update
                    itemBinding.vistaFila.setOnClickListener{
                        val action = LugarFragmentDirections.actionNavLugarToUpdateLugarFragment3(lugar)
                        itemView.findNavController().navigate(action)
                    }
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LugarAdapter.LugarViewHolder {
       val itemBinding = LugarFilaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
       return LugarViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: LugarViewHolder, position: Int) {
        val lugarActual = listaLugares[position]
        holder.bind(lugarActual)
    }

    override fun getItemCount(): Int {
        return listaLugares.size
    }

    fun setData(lugares: List<Lugar>){
        this.listaLugares = lugares
        notifyDataSetChanged()
    }
}