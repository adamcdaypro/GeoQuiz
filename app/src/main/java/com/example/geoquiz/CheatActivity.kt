package com.example.geoquiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.geoquiz.databinding.ActivityCheatBinding

class CheatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val answer = intent.getBooleanExtra(EXTRA_ANSWER, true)

        binding.showAnswerButton.setOnClickListener {
            binding.answerTextView.text = when {
                answer -> getString(R.string.true_text)
                else -> getString(R.string.false_text)
            }
            setActivityResult()
        }
    }

    private fun setActivityResult() {
        val intent = Intent().putExtra(RESULT_EXTRA_ANSWER_SHOWN, true)
        setResult(RESULT_OK, intent)
    }

    companion object {

        private const val EXTRA_ANSWER = "com.example.geoquiz.answer"
        const val RESULT_EXTRA_ANSWER_SHOWN = "com.example.geoquiz.answer_shown"

        fun newIntent(context: Context, answer: Boolean): Intent {
            return Intent(context, CheatActivity::class.java)
                .putExtra(EXTRA_ANSWER, answer)
        }
    }
}