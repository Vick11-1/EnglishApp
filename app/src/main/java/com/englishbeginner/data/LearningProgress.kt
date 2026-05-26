package com.englishbeginner.data

data class LearningProgress(
    val wordId: Int,
    val stageId: Int,
    val reviewLevel: Int = 0,
    val nextReviewDate: String = "",
    val lastReviewDate: String = "",
    val correctCount: Int = 0,
    val incorrectCount: Int = 0,
    val isMastered: Boolean = false
)
