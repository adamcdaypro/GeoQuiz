package com.example.geoquiz

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.geoquiz.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: QuizViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(savedInstanceState: Bundle?) called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "Got a QuizViewModel $viewModel")

        updateQuestion()
        updateNextButton()
        updatePrevButton()

        binding.trueButton.setOnClickListener {
            val answer = viewModel.currentQuestionAnswer
            Snackbar.make(it, getAnswerString(answer, true), Snackbar.LENGTH_SHORT).show()
        }
        binding.falseButton.setOnClickListener {
            val answer = viewModel.currentQuestionAnswer
            Snackbar.make(it, getAnswerString(answer, false), Snackbar.LENGTH_SHORT).show()
        }
        binding.nextButton.setOnClickListener {
            viewModel.moveToNext()
            updateQuestion()
            updateNextButton()
            updatePrevButton()
        }
        binding.previousButton.setOnClickListener {
            viewModel.moveToPrev()
            updateQuestion()
            updateNextButton()
            updatePrevButton()
        }
    }

    private fun getAnswerString(correctAnswer: Boolean, selectedAnswer: Boolean): String {
        return if (correctAnswer == selectedAnswer) {
            getString(R.string.correct)
        } else {
            getString(R.string.incorrect)
        }
    }

    private fun updateQuestion() {
        binding.questionTextView.text = getString(viewModel.currentQuestionText)
    }

    private fun updateNextButton() {
        binding.nextButton.isEnabled = viewModel.hasNext()
    }

    private fun updatePrevButton() {
        binding.previousButton.isEnabled = viewModel.hasPrev()
    }

}