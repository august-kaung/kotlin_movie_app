package com.example.androidmovieapp.splash

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidmovieapp.R

class SplashScreen : AppCompatActivity() {
    lateinit var splashImg: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)
        splashImg = findViewById(R.id.imgSplash)
        splashImg.alpha = 0f
        splashImg.animate().setDuration(2000).alpha(1f).withEndAction {
            overridePendingTransition(
                android.R.anim.fade_in, android.R.anim.fade_out
            )
            finish()
            startActivity(
                android.content.Intent(
                    this@SplashScreen,
                    com.example.androidmovieapp.auth.LogIn::class.java
                )
            )
        }


    }
}