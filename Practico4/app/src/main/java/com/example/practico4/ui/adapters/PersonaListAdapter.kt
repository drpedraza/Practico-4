package com.example.practico4.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practico4.R
import com.example.practico4.databinding.PersonaItemLayoutBinding
import com.example.practico4.models.Persona
import com.example.practico4.models.Personas


class PersonaListAdapter(
    private val listener: PersonaItemListener
) : RecyclerView.Adapter<PersonaListAdapter.PersonaItemViewHolder>() {
    private var personaList: Personas = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaItemViewHolder {
        val binding =
            PersonaItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonaItemViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return personaList.size
    }

    override fun onBindViewHolder(holder: PersonaItemViewHolder, position: Int) {
        holder.bind(personaList[position], listener)
    }

    fun updateData(it: Personas) {
        personaList = it
        notifyDataSetChanged()

    }

    class PersonaItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val lblPersonaItemNombre: TextView = itemView.findViewById(R.id.lblPersonaItemNombre)
        fun bind(persona: Persona, listener: PersonaItemListener) {
            lblPersonaItemNombre.text = persona.name
            itemView.setOnClickListener {
                listener.onPersonaItemClick(persona)
            }
        }
    }

    interface PersonaItemListener {
        fun onPersonaItemClick(persona: Persona)
    }
}
