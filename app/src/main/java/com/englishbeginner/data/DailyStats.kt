package com.englishbeginner.data

data class DailyStats(
    val date: String,
    val wordsLearned: Int = 0,
    val wordsReviewed: Int = 0,
    val studyMinutes: Int = 0,
    val correctRate: Float = 0f
)
