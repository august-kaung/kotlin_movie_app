package com.example.androidmovieapp.auth

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidmovieapp.R
import com.example.androidmovieapp.databinding.ActivityForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotPassword : AppCompatActivity() {
    lateinit var binding: ActivityForgotPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)

        var forgotPasswordEmailWidget = binding.txtvForgottEmail

        var forgotPasswordButtonWidget = binding.btnForgot


        forgotPasswordButtonWidget.setOnClickListener {
            Log.e("LoginActivity", "Login button clicked")
            if (TextUtils.isEmpty(forgotPasswordEmailWidget.text.toString().trim())) {
                forgotPasswordEmailWidget.error = "Email is required"

//                forgotPasswordEmailWidget.requestFocus()

            } else {
                Toast.makeText(
                    this,
                    "Login Successful",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        forgotPasswordButtonWidget.setOnClickListener {
            resetPassword(forgotPasswordEmailWidget.text.toString().trim())
        }
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Forgot Password"

        setContentView(binding.root)

    }
    fun resetPassword(email: String) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener {
            task ->
            if (task.isSuccessful) {
                finish()
                 startActivity(
                    android.content.Intent(
                        this@ForgotPassword,
                        ForgotPasswordReminder::class.java
                    ))
            } else {
                Toast.makeText(
                    this,
                    "Error: ${task.exception?.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}