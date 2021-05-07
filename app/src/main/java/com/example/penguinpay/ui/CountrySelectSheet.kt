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
 *Created by Yerimah on 5/7/2021.
 */
class CountrySelectSheet(private val activity: MainActivity,
                         private val onCountrySelected: (Int) -> Unit): BottomSheetDialogFragment() {

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

        val countries = LinkedList<CountryModel>()
        countries.add(CountryModel("NIGERIA", "NGN", R.drawable.flag_nigeria, 1))
        countries.add(CountryModel("GHANA", "GHN", R.drawable.flag_ghana, 69))
        countries.add(CountryModel("UNITED STATES", "USD", R.drawable.flag_united_states_of_america, 207))
        countries.add(CountryModel("UNITED KINGDOM", "GP", R.drawable.flag_united_kingdom, 62))


        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = RecyclerView.VERTICAL
        binding.countryRecyclerView.layoutManager = layoutManager
        binding.countryRecyclerView.adapter = CountrySelectAdapter(countries, onCountrySelected)
    }
}