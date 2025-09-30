package com.example.cube

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cube.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, DiceFragment())
            .commit()

        binding.btnDiceMenu.setOnClickListener { supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, DiceFragment())
            .commit() }

        binding.btnHistoryDice.setOnClickListener { supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, HistoryFragment())
            .commit() }
    }

}