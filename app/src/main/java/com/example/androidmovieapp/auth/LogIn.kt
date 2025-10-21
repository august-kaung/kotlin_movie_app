package com.example.androidmovieapp.auth

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidmovieapp.R
import com.example.androidmovieapp.databinding.ActivityLogInBinding

class LogIn : AppCompatActivity() {
    lateinit var binding: ActivityLogInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLogInBinding.inflate(layoutInflater)
        var loginEmailWidget = binding.txtvEmail
        var loginPassowrdWidget = binding.txtvPassword
        var loginButtonWidget = binding.btnLogin
        var signUpButtonWidget = binding.txtBtnSignIn
        var forgotButtonWidget = binding.txtBtnForgotPassword

        loginButtonWidget.setOnClickListener {
            Log.e("LoginActivity", "Login button clicked")
            if (TextUtils.isEmpty(loginEmailWidget.text.toString().trim())) {
                loginEmailWidget.error = "Email is required"

//                loginEmailWidget.requestFocus()

            } else if (TextUtils.isEmpty(loginPassowrdWidget.text.toString().trim())) {
                loginPassowrdWidget.error = "Password is required"
//                loginPassowrdWidget.requestFocus()

            } else {
                Toast.makeText(
                    this@LogIn,
                    "Login Successful",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        signUpButtonWidget.setOnClickListener {
            startActivity(
                android.content.Intent(
                    this@LogIn,
                    SignUp::class.java
                )
            )
        }
        forgotButtonWidget.setOnClickListener {
            startActivity(
                android.content.Intent(
                    this@LogIn,
                    ForgotPassword::class.java
                )
            )
        }




        setContentView(binding.root)
    }
}