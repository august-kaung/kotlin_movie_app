package com.example.androidmovieapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidmovieapp.auth.LogIn
import com.example.androidmovieapp.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProfileBinding.inflate(layoutInflater)
        val sharedPrefs = getSharedPreferences(getString(R.string.sp_name), MODE_PRIVATE)

        val username =
            sharedPrefs.getString(getString(R.string.username), "default_user") ?: "default_user"
        val password =
            sharedPrefs.getString(getString(R.string.password), "default_pass") ?: "default_pass"

        binding.txtvUserEmail.setText(username)
        binding.txtvUserPassword.setText(password)

        binding.btnLogout.setOnClickListener {


            val sp = getSharedPreferences(getString(R.string.sp_name), MODE_PRIVATE)
            val editor = sp.edit()
            editor.clear().apply()
            //?Real push replacement
            val intent = Intent(this, LogIn::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        setSupportActionBar(binding.toolbar)
        binding.toolbar.setTitleTextColor(resources.getColor(R.color.primaryColor))
        binding.toolbar.navigationIcon?.setTint(ContextCompat.getColor(this, R.color.primaryColor))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Profile"



        setContentView(binding.root)

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true

        }
        return super.onOptionsItemSelected(item)
    }
}