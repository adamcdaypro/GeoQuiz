package com.example.geoquiz

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.geoquiz.model.Question

private const val KEY_CURRENT_QUESTION_INDEX = "KEY_CURRENT_QUESTION_INDEX"
private const val KEY_IS_CHEATER = "KEY_IS_CHEATER"

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
        get() = savedStateHandle[KEY_CURRENT_QUESTION_INDEX] ?: 0
        set(value) = savedStateHandle.set(KEY_CURRENT_QUESTION_INDEX, value)

    val currentQuestionAnswer: Boolean
        get() = questions[currentQuestionIndex].answer

    val currentQuestionText: Int
        get() = questions[currentQuestionIndex].resourceId

    var isCheater: Boolean
        get() = savedStateHandle[KEY_IS_CHEATER] ?: false
        set(value) = savedStateHandle.set(KEY_IS_CHEATER, value)

    fun hasNext(): Boolean = currentQuestionIndex < questions.size - 1

    fun hasPrev(): Boolean = currentQuestionIndex > 0

    fun moveToNext() {
        if (hasNext()) currentQuestionIndex++
    }

    fun moveToPrev() {
        if (hasPrev()) currentQuestionIndex--
    }

}