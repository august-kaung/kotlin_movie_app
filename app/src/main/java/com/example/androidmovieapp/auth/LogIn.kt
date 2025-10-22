package com.example.androidmovieapp.auth

import android.content.SharedPreferences
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
import com.google.firebase.auth.FirebaseAuth

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
                loginUser(
                    loginEmailWidget.text.toString().trim(),
                    loginPassowrdWidget.text.toString().trim()
                )
            }
        }
        signUpButtonWidget.setOnClickListener {
            startActivity(
                android.content.Intent(
                    this@LogIn,
                    SignUp::class.java
                )
            )
            loginEmailWidget.clearFocus()
            loginPassowrdWidget.clearFocus()
            loginEmailWidget.text.clear()
            loginPassowrdWidget.text.clear()


        }
        forgotButtonWidget.setOnClickListener {

            startActivity(
                android.content.Intent(
                    this@LogIn,
                    ForgotPassword::class.java
                )
            )
            loginEmailWidget.clearFocus()
            loginPassowrdWidget.clearFocus()
            loginEmailWidget.text.clear()
            loginPassowrdWidget.text.clear()
        }




        setContentView(binding.root)
    }

    fun loginUser(email: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    finish()
                    Toast.makeText(
                        this,
                        "Login Successful",
                        Toast.LENGTH_SHORT
                    ).show()
                    var sp = getSharedPreferences(getString(R.string.sp_name), MODE_PRIVATE)
                    var editor = sp.edit()
                    editor.putBoolean(getString(R.string.is_log_in), true)
                    editor.putString(getString(R.string.username), email)
                    editor.putString(getString(R.string.password), password)
                    editor.commit()
                    startActivity(
                        android.content.Intent(
                            this,
                            com.example.androidmovieapp.movie.HomeActivity::class.java
                        )
                    )
                } else {
                    Toast.makeText(
                        this,
                        "Login Failed: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}