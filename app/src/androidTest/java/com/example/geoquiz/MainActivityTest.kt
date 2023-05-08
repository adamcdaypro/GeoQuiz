package com.example.geoquiz

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        scenario = launch(MainActivity::class.java)
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun on_create_show_first_question() {
        onView(withId(R.id.question_textView)).check(matches(withText(R.string.question_australia)))
        onView(withId(R.id.next_button)).check(matches(isEnabled()))
        onView(withId(R.id.previous_button)).check(matches(isNotEnabled()))
    }

    @Test
    fun first_question_is_true() {
        onView(withId(R.id.true_button)).perform(click())
        onView(withId(com.google.android.material.R.id.snackbar_text)).check(matches(withText(R.string.correct)))
    }

    @Test
    fun first_question_is_false() {
        onView(withId(R.id.false_button)).perform(click())
        onView(withId(com.google.android.material.R.id.snackbar_text)).check(matches(withText(R.string.incorrect)))
    }

    @Test
    fun show_second_question() {
        onView(withId(R.id.next_button)).perform(click())
        onView(withId(R.id.question_textView)).check(matches(withText(R.string.question_oceans)))
        onView(withId(R.id.next_button)).check(matches(isEnabled()))
        onView(withId(R.id.previous_button)).check(matches(isEnabled()))
    }

    @Test
    fun show_third_question() {
        onView(withId(R.id.next_button)).perform(click())
        onView(withId(R.id.next_button)).perform(click())
        onView(withId(R.id.question_textView)).check(matches(withText(R.string.question_midwest)))
        onView(withId(R.id.next_button)).check(matches(isEnabled()))
        onView(withId(R.id.previous_button)).check(matches(isEnabled()))
    }

    @Test
    fun show_fourth_question() {
        onView(withId(R.id.next_button)).perform(click())
        onView(withId(R.id.next_button)).perform(click())
        onView(withId(R.id.next_button)).perform(click())
        onView(withId(R.id.question_textView)).check(matches(withText(R.string.question_africa)))
        onView(withId(R.id.next_button)).check(matches(isEnabled()))
        onView(withId(R.id.previous_button)).check(matches(isEnabled()))
    }

    @Test
    fun show_fifth_question() {
        onView(withId(R.id.next_button)).perform(click())
        onView(withId(R.id.next_button)).perform(click())
        onView(withId(R.id.next_button)).perform(click())
        onView(withId(R.id.next_button)).perform(click())
        onView(withId(R.id.question_textView)).check(matches(withText(R.string.question_americas)))
        onView(withId(R.id.next_button)).check(matches(isEnabled()))
        onView(withId(R.id.previous_button)).check(matches(isEnabled()))
    }

    @Test
    fun show_sixth_question() {
        onView(withId(R.id.next_button)).perform(click())
        onView(withId(R.id.next_button)).perform(click())
        onView(withId(R.id.next_button)).perform(click())
        onView(withId(R.id.next_button)).perform(click())
        onView(withId(R.id.next_button)).perform(click())
        onView(withId(R.id.question_textView)).check(matches(withText(R.string.question_asia)))
        onView(withId(R.id.next_button)).check(matches(isNotEnabled()))
        onView(withId(R.id.previous_button)).check(matches(isEnabled()))

    }

}