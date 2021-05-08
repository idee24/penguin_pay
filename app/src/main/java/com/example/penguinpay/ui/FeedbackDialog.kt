package com.example.penguinpay.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import com.example.penguinpay.MainActivity
import com.example.penguinpay.databinding.FeedbackDialogBinding

/**
 *@Created by Yerimah on 5/07/2021.
 */

class  FeedbackDialog(activity: MainActivity,
                      private val message: String,
                      private val actionText: String,
                      private val callBack: () -> Unit): Dialog(activity) {

    private lateinit var binding: FeedbackDialogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = FeedbackDialogBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)

        binding.messageTextView.text = message
        binding.actionTextView.text = actionText
        binding.actionTextView.setOnClickListener {
            callBack()
            dismiss()
        }
    }
}