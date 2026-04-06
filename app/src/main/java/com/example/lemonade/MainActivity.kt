package com.example.lemonade

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lemonade.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private enum class LemonadeState {
        SELECT, SQUEEZE, DRINK, RESTART
    }

    private lateinit var binding: ActivityMainBinding
    private var currentState = LemonadeState.SELECT
    private var squeezeTarget = 0
    private var squeezeCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateUI(LemonadeState.SELECT)

        binding.imageView.setOnClickListener {
            handleTap()
        }
    }

    private fun handleTap() {
        when (currentState) {
            LemonadeState.SELECT -> {
                squeezeTarget = Random.nextInt(2, 5)
                squeezeCount = 0
                currentState = LemonadeState.SQUEEZE
                updateUI(currentState)
            }
            LemonadeState.SQUEEZE -> {
                squeezeCount++
                if (squeezeCount >= squeezeTarget) {
                    currentState = LemonadeState.DRINK
                    updateUI(currentState)
                }
            }
            LemonadeState.DRINK -> {
                currentState = LemonadeState.RESTART
                updateUI(currentState)
            }
            LemonadeState.RESTART -> {
                currentState = LemonadeState.SELECT
                updateUI(currentState)
            }
        }
    }

    private fun updateUI(state: LemonadeState) {
        when (state) {
            LemonadeState.SELECT -> {
                binding.imageView.setImageResource(R.drawable.lemon_tree)
                binding.imageView.contentDescription = getString(R.string.lemon_tree_desc)
                binding.textView.text = getString(R.string.tap_tree)
            }
            LemonadeState.SQUEEZE -> {
                binding.imageView.setImageResource(R.drawable.lemon_squeeze)
                binding.imageView.contentDescription = getString(R.string.lemon_desc)
                binding.textView.text = getString(R.string.tap_lemon)
            }
            LemonadeState.DRINK -> {
                binding.imageView.setImageResource(R.drawable.lemon_drink)
                binding.imageView.contentDescription = getString(R.string.lemonade_desc)
                binding.textView.text = getString(R.string.tap_drink)
            }
            LemonadeState.RESTART -> {
                binding.imageView.setImageResource(R.drawable.lemon_restart)
                binding.imageView.contentDescription = getString(R.string.empty_glass_desc)
                binding.textView.text = getString(R.string.tap_restart)
            }
        }
    }
}