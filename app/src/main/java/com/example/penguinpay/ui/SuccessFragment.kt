package com.example.penguinpay.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.penguinpay.Constants
import com.example.penguinpay.MainActivity
import com.example.penguinpay.R


class SuccessFragment : Fragment(R.layout.fragment_success) {

    private lateinit var mainActivity: MainActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val builder = NotificationCompat.Builder(mainActivity, Constants.CHANNEL_ID)
                .setSmallIcon(R.drawable.notify_icon)
                .setContentTitle("Penguin Pay transfer alert")
                .setContentText("Your transaction is being sent.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
        with(NotificationManagerCompat.from(mainActivity)) {
            notify(Constants.NOTIFICATION_ID, builder.build())
        }

    }
}