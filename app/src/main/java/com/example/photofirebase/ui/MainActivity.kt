package com.example.photofirebase.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.photofirebase.R
import com.example.photofirebase.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonMenu.background = null
        binding.buttonMenu.menu.getItem(2).isEnabled = false
    }
}