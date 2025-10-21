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
import com.example.androidmovieapp.databinding.ActivitySignUpBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        binding = ActivitySignUpBinding.inflate(layoutInflater)

        var signUpEmailWidget = binding.txtvSignUptEmail
        var signUpPassowrdWidget = binding.txtvSignUpPassword
        var signUpButtonWidget = binding.btnSignUp


        signUpButtonWidget.setOnClickListener {
            Log.e("LoginActivity", "Login button clicked")
            if (TextUtils.isEmpty(signUpEmailWidget.text.toString().trim())) {
                signUpEmailWidget.error = "Email is required"

//                signUpEmailWidget.requestFocus()

            } else if (TextUtils.isEmpty(signUpPassowrdWidget.text.toString().trim())) {
                signUpPassowrdWidget.error = "Password is required"
//                signUpPassowrdWidget.requestFocus()

            } else {
                registerUser(
                    signUpEmailWidget.text.toString().trim(),
                    signUpPassowrdWidget.text.toString().trim()
                )
            }
        }

        setSupportActionBar(binding.toolbar) // use view binding if available
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Sign Up"


        setContentView(binding.root)

    }

    fun registerUser(email: String, password: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    finish()
                    Toast.makeText(
                        this,
                        "Registration Successful",
                        Toast.LENGTH_SHORT
                    ).show()
                    onBackPressed()
                } else {
                    Toast.makeText(
                        this,
                        "Registration Failed: ${task.exception?.message}",
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