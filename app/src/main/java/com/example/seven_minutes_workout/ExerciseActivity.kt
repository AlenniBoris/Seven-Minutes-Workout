package com.example.seven_minutes_workout

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.seven_minutes_workout.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {

    private var binding: ActivityExerciseBinding? = null

    private var restTimer: CountDownTimer? = null
    private var restProgress = 0

    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 0


    private var exerciseList: ArrayList<ExerciseModel>? = null
    private var currentExercisePosition = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityExerciseBinding.inflate(layoutInflater)

        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarExercise)

        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        exerciseList = Constants.defaultExerciseList()

        binding?.toolbarExercise?.setNavigationOnClickListener {
            onBackPressed()
        }

        setUpRestView()
    }

    override fun onDestroy() {
        super.onDestroy()

        if(restTimer != null){
            restTimer?.cancel()
            restProgress = 0
        }

        if(exerciseTimer != null){
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }

        binding = null
    }

    private fun setUpRestView(){
        if(restTimer != null){
            restTimer?.cancel()
            restProgress = 0
        }
        setRestProgressBar()
    }

    private fun setUpExerciseView(){

        binding?.flProgressBar?.visibility = View.GONE
        binding?.tvTitle?.visibility = View.GONE

        binding?.flExerciseView?.visibility = View.VISIBLE
        binding?.tvTitleExercise?.visibility = View.VISIBLE

        if(exerciseTimer != null){
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }

        setExerciseProgressBar()
    }

    private fun setRestProgressBar(){
        binding?.progressBar?.progress = restProgress

        restTimer = object: CountDownTimer(10000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                binding?.progressBar?.progress = 10 - restProgress
                binding?.tvTimer?.text = (10-restProgress).toString()
            }

            override fun onFinish() {
                setUpExerciseView()
            }
        }.start()
    }

    private fun setExerciseProgressBar(){
        binding?.progressBarExercise?.progress = exerciseProgress

        exerciseTimer = object: CountDownTimer(30000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                binding?.progressBarExercise?.progress = 30 - exerciseProgress
                binding?.tvTimerExercise?.text = (30-exerciseProgress).toString()
            }

            override fun onFinish() {
                Toast.makeText(
                    this@ExerciseActivity,
                    "Here we will start the next exercise",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }.start()
    }
}