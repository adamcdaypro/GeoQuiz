package com.example.geoquiz

import androidx.lifecycle.SavedStateHandle
import org.junit.After
import org.junit.Before
import org.junit.Test

internal class QuizViewModelTest {

    lateinit var quizViewModel: QuizViewModel

    @Before
    fun setUp() {
        val savedStateHandle = SavedStateHandle()
        quizViewModel = QuizViewModel(savedStateHandle)
    }

    @After
    fun tearDown() {

    }

    @Suppress("SimplifyBooleanWithConstants")
    @Test
    fun getCurrentQuestionAnswer() {
        assert(true == quizViewModel.currentQuestionAnswer)
        quizViewModel.moveToNext()
        assert(true == quizViewModel.currentQuestionAnswer)
        quizViewModel.moveToNext()
        assert(false == quizViewModel.currentQuestionAnswer)
        quizViewModel.moveToNext()
        assert(false == quizViewModel.currentQuestionAnswer)
        quizViewModel.moveToNext()
        assert(true == quizViewModel.currentQuestionAnswer)
        quizViewModel.moveToNext()
        assert(true == quizViewModel.currentQuestionAnswer)
    }

    @Test
    fun getCurrentQuestionText() {
        assert(R.string.question_australia == quizViewModel.currentQuestionText)
        quizViewModel.moveToNext()
        assert(R.string.question_oceans == quizViewModel.currentQuestionText)
        quizViewModel.moveToNext()
        assert(R.string.question_midwest == quizViewModel.currentQuestionText)
        quizViewModel.moveToNext()
        assert(R.string.question_africa == quizViewModel.currentQuestionText)
        quizViewModel.moveToNext()
        assert(R.string.question_americas == quizViewModel.currentQuestionText)
        quizViewModel.moveToNext()
        assert(R.string.question_asia == quizViewModel.currentQuestionText)
    }

    @Test
    fun hasNext() {
        assert(quizViewModel.hasNext()) { "Text ${quizViewModel.currentQuestionText}" }
        quizViewModel.moveToNext()
        assert(quizViewModel.hasNext()) { "Text ${quizViewModel.currentQuestionText}" }
        quizViewModel.moveToNext()
        assert(quizViewModel.hasNext()) { "Text ${quizViewModel.currentQuestionText}" }
        quizViewModel.moveToNext()
        assert(quizViewModel.hasNext()) { "Text ${quizViewModel.currentQuestionText}" }
        quizViewModel.moveToNext()
        assert(quizViewModel.hasNext()) { "Text ${quizViewModel.currentQuestionText}" }
        quizViewModel.moveToNext()
        assert(!quizViewModel.hasNext()) { "Text ${quizViewModel.currentQuestionText}" }

    }

    @Test
    fun hasPrev() {
        while (quizViewModel.hasNext()) quizViewModel.moveToNext()
        assert(quizViewModel.hasPrev())
        quizViewModel.moveToPrev()
        assert(quizViewModel.hasPrev())
        quizViewModel.moveToPrev()
        assert(quizViewModel.hasPrev())
        quizViewModel.moveToPrev()
        assert(quizViewModel.hasPrev())
        quizViewModel.moveToPrev()
        assert(quizViewModel.hasPrev())
        quizViewModel.moveToPrev()
        assert(!quizViewModel.hasPrev())
    }

    @Test
    fun moveToNext() {

    }

    @Test
    fun moveToPrev() {
    }
}