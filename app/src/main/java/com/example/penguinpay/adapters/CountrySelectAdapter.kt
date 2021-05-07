package com.example.penguinpay.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.penguinpay.R
import com.example.penguinpay.networking.CountryModel
import de.hdodenhof.circleimageview.CircleImageView

/**
 *Created by Yerimah on 5/7/2021.
 */class CountrySelectAdapter (private val countries: List<CountryModel>,
                               private val onCountrySelected: (String) -> Unit):
    RecyclerView.Adapter<CountrySelectAdapter.CountrySelectViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountrySelectViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_select_item, parent, false)
        return CountrySelectViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountrySelectViewHolder, position: Int) {
        countries[position].let { country ->
            holder.nameTextView.text = country.name
            holder.flagImageView.setImageResource(country.image)
            holder.itemView.setOnClickListener { onCountrySelected(country.countryCode) }
        }
    }

    override fun getItemCount(): Int = countries.size

    inner class CountrySelectViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val flagImageView: CircleImageView = itemView.findViewById(R.id.flagImageView)
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
    }
}