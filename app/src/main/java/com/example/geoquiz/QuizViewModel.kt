package com.example.geoquiz

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.geoquiz.model.Question

private const val CURRENT_QUESTION_INDEX_KEY = "CURRENT_QUESTION_INDEX_KEY"

class QuizViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val questions = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_midwest, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    private var currentQuestionIndex: Int
        get() = savedStateHandle[CURRENT_QUESTION_INDEX_KEY] ?: 0
        set(value) = savedStateHandle.set(CURRENT_QUESTION_INDEX_KEY, value)

    val currentQuestionAnswer: Boolean
        get() = questions[currentQuestionIndex].answer

    val currentQuestionText: Int
        get() = questions[currentQuestionIndex].resourceId

    fun hasNext(): Boolean = currentQuestionIndex < questions.size - 1

    fun hasPrev(): Boolean = currentQuestionIndex > 0

    fun moveToNext() {
        if (hasNext()) currentQuestionIndex++
    }

    fun moveToPrev() {
        if (hasPrev()) currentQuestionIndex--
    }

}