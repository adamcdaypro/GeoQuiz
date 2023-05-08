package com.example.geoquiz

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.geoquiz.databinding.ActivityMainBinding
import com.example.geoquiz.model.Question
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val questions = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_midwest, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(savedInstanceState: Bundle?) called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var question = questions[0]

        binding.questionTextView.text = getString(question.resourceId)

        binding.trueButton.setOnClickListener {
            Snackbar.make(it, getAnswerString(question.answer, true), Snackbar.LENGTH_SHORT).show()
        }
        binding.falseButton.setOnClickListener {
            Snackbar.make(it, getAnswerString(question.answer, false), Snackbar.LENGTH_SHORT).show()
        }
        binding.nextButton.setOnClickListener {
            question = getNextQuestion(question, questions)
            binding.questionTextView.text = getString(question.resourceId)
        }
        binding.previousButton.setOnClickListener {
            question = getPreviousQuestion(question, questions)
            binding.questionTextView.text = getString(question.resourceId)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun getAnswerString(correctAnswer: Boolean, selectedAnswer: Boolean): String {
        return if (correctAnswer == selectedAnswer) {
            getString(R.string.correct)
        } else {
            getString(R.string.incorrect)
        }
    }

    companion object {

        private const val TAG = "MainActivity"

        private fun getNextQuestion(
            currentQuestion: Question,
            questions: List<Question>
        ): Question {
            return try {
                var currentIndex = questions.indexOf(currentQuestion)
                questions[++currentIndex]
            } catch (e: ArrayIndexOutOfBoundsException) {
                questions[0]
            }
        }

        private fun getPreviousQuestion(
            currentQuestion: Question,
            questions: List<Question>
        ): Question {
            return try {
                var currentIndex = questions.indexOf(currentQuestion)
                questions[--currentIndex]
            } catch (e: ArrayIndexOutOfBoundsException) {
                questions[questions.lastIndex]
            }
        }
    }

}