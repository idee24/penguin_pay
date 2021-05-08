package com.example.penguinpay.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.penguinpay.MainActivity
import com.example.penguinpay.R
import com.example.penguinpay.adapters.CountrySelectAdapter
import com.example.penguinpay.databinding.CountrySelectSheetBinding
import com.example.penguinpay.networking.CountryModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*

/**
 *@Created by Yerimah on 5/07/2021.
 */
class CountrySelectSheet(private val activity: MainActivity,
                         private val countries: List<CountryModel>,
                         private val onCountrySelected: (String) -> Unit): BottomSheetDialogFragment() {

    private lateinit var binding: CountrySelectSheetBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.country_select_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = CountrySelectSheetBinding.bind(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = RecyclerView.VERTICAL
        binding.countryRecyclerView.layoutManager = layoutManager
        binding.countryRecyclerView.adapter = CountrySelectAdapter(countries, onCountrySelected)
    }
}