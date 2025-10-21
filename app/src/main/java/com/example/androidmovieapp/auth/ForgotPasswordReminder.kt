package com.example.androidmovieapp.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidmovieapp.R
import com.example.androidmovieapp.databinding.ActivityForgotPasswordReminderBinding

class ForgotPasswordReminder : AppCompatActivity() {
    lateinit var binding: ActivityForgotPasswordReminderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityForgotPasswordReminderBinding.inflate(layoutInflater)

        //? Push replacement to Login Activity
        binding.btnReLogin.setOnClickListener {
            val intent = Intent(this, LogIn::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)

        }
        setContentView(binding.root)

    }
}