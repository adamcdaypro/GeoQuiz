package com.example.geoquiz.model

import androidx.annotation.StringRes

data class Question(@StringRes val resourceId: Int, val answer: Boolean)