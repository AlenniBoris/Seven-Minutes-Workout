package com.example.seven_minutes_workout

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.seven_minutes_workout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding!!.root)

        val flStartButton: FrameLayout = binding!!.flStart
        flStartButton.setOnClickListener {
            val intent = Intent(this@MainActivity, ExerciseActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        binding = null
    }

}