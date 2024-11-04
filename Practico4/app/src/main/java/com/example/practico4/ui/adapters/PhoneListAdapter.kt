package com.example.practico4.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practico4.R
import com.example.practico4.databinding.PhoneItemLayoutBinding
import com.example.practico4.models.Phone
import com.example.practico4.models.Phones

class PhoneListAdapter()
    : RecyclerView.Adapter<PhoneListAdapter.PhoneItemViewHolder>() {
    private var phoneList: Phones = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneItemViewHolder {
        val binding =
            PhoneItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhoneItemViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return phoneList.size
    }

    override fun onBindViewHolder(holder: PhoneItemViewHolder, position: Int) {
        holder.bind(phoneList[position])
    }

    fun updateData(it: Phones) {
        phoneList = it
        notifyDataSetChanged()

    }

    class PhoneItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val lblPhoneItemNumber: TextView = itemView.findViewById(R.id.lblPhoneItemNumber)
        private val lblPhoneItemLabel: TextView = itemView.findViewById(R.id.lblPhoneItemLabel)
        fun bind(phone: Phone) {
            lblPhoneItemNumber.text = phone.number
            lblPhoneItemLabel.text = phone.label
            }
        }
    }