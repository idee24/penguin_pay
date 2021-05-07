package com.example.penguinpay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.penguinpay.utils.setCountries

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setCountries()
    }
}