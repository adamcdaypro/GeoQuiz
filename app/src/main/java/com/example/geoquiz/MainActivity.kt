package com.example.geoquiz

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.geoquiz.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: QuizViewModel by viewModels()

    private val cheatLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (RESULT_OK == it.resultCode) {
                val isCheater =
                    it.data?.getBooleanExtra(CheatActivity.RESULT_EXTRA_ANSWER_SHOWN, false)
                        ?: false
                viewModel.isCheater = isCheater
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(savedInstanceState: Bundle?) called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "Got a QuizViewModel $viewModel")

        updateQuestion()
        updateNextButton()
        updatePrevButton()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            blurCheatButton()
        }

        binding.trueButton.setOnClickListener {
            val answer = getAnswerString(viewModel.currentQuestionAnswer, true)
            Snackbar.make(it, answer, Snackbar.LENGTH_SHORT).show()
        }
        binding.falseButton.setOnClickListener {
            val answer = getAnswerString(viewModel.currentQuestionAnswer, false)
            Snackbar.make(it, answer, Snackbar.LENGTH_SHORT).show()
        }
        binding.cheatButton.setOnClickListener {
            val intent = CheatActivity.newIntent(this, viewModel.currentQuestionAnswer)
            cheatLauncher.launch(intent)
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
        return when {
            viewModel.isCheater -> getString(R.string.judgment)
            (correctAnswer == selectedAnswer) -> getString(R.string.correct)
            else -> getString(R.string.incorrect)
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

    @RequiresApi(Build.VERSION_CODES.S)
    private fun blurCheatButton() {
        val effect = RenderEffect.createBlurEffect(
            10.0f,
            10.0f,
            Shader.TileMode.CLAMP
        )
        binding.cheatButton.setRenderEffect(effect)
    }
}