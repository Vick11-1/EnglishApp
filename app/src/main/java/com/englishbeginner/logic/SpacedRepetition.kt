package com.englishbeginner.logic

import com.englishbeginner.data.LearningProgress
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object SpacedRepetition {

    // 复习间隔（天数）：level 0=当天, 1=1天后, 2=2天后, 3=4天后, 4=7天后, 5=15天后, 6=30天后
    private val intervals = intArrayOf(0, 1, 2, 4, 7, 15, 30)
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    fun calculateNextReview(progress: LearningProgress, correct: Boolean): LearningProgress {
        val today = LocalDate.now()
        val todayStr = today.format(formatter)

        return if (correct) {
            val nextLevel = minOf(progress.reviewLevel + 1, intervals.size - 1)
            val nextDate = today.plusDays(intervals[nextLevel].toLong())
            progress.copy(
                reviewLevel = nextLevel,
                nextReviewDate = nextDate.format(formatter),
                lastReviewDate = todayStr,
                correctCount = progress.correctCount + 1,
                isMastered = nextLevel >= 5
            )
        } else {
            val nextDate = today.plusDays(1)
            progress.copy(
                reviewLevel = 0,
                nextReviewDate = nextDate.format(formatter),
                lastReviewDate = todayStr,
                incorrectCount = progress.incorrectCount + 1,
                isMastered = false
            )
        }
    }

    fun isDueToday(progress: LearningProgress): Boolean {
        val today = LocalDate.now().format(formatter)
        return progress.nextReviewDate <= today && !progress.isMastered
    }

    fun newProgress(wordId: Int, stageId: Int): LearningProgress {
        val today = LocalDate.now().format(formatter)
        return LearningProgress(
            wordId = wordId,
            stageId = stageId,
            reviewLevel = 0,
            nextReviewDate = today,
            lastReviewDate = "",
            correctCount = 0,
            incorrectCount = 0,
            isMastered = false
        )
    }

    /** 被 ReviewFragment / ReviewActivity 调用 */
    fun isDue(progress: LearningProgress): Boolean = isDueToday(progress)

    /** 被 LearnActivity / QuizActivity / ReviewActivity 调用 */
    fun updateAfterReview(progress: LearningProgress, correct: Boolean): LearningProgress =
        calculateNextReview(progress, correct)
}
