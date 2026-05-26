package com.englishbeginner.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.englishbeginner.App
import com.englishbeginner.R
import com.englishbeginner.data.WordRepository
import com.englishbeginner.logic.SpacedRepetition

class StatsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvTotalWords = view.findViewById<TextView>(R.id.tvTotalWords)
        val tvMastered = view.findViewById<TextView>(R.id.tvMastered)
        val tvLearning = view.findViewById<TextView>(R.id.tvLearning)
        val tvNewWords = view.findViewById<TextView>(R.id.tvNewWords)
        val tvStreak = view.findViewById<TextView>(R.id.tvStreak)
        val tvTodayLearned = view.findViewById<TextView>(R.id.tvTodayLearned)
        val tvRetention = view.findViewById<TextView>(R.id.tvRetention)
        val stageContainer = view.findViewById<LinearLayout>(R.id.stageContainer)

        val allWords = WordRepository.getAllWords()
        val dbHelper = App.dbHelper

        val mastered = allWords.count { dbHelper.getProgress(it.id)?.isMastered == true }
        val inProgress = allWords.count { p -> dbHelper.getProgress(p.id) != null && dbHelper.getProgress(p.id)!!.isMastered.not() }
        val newWords = allWords.count { dbHelper.getProgress(it.id) == null }

        tvTotalWords.text = allWords.size.toString()
        tvMastered.text = mastered.toString()
        tvLearning.text = inProgress.toString()
        tvNewWords.text = newWords.toString()

        // 连续天数
        val stats = dbHelper.getDailyStats()
        val streak = stats.size
        tvStreak.text = streak.toString()

        // 今日学了多少
        val todayStats = dbHelper.getTodayStats()
        tvTodayLearned.text = todayStats?.wordsLearned?.toString() ?: "0"

        // 正确率
        val totalCorrect = allWords.sumOf { dbHelper.getProgress(it.id)?.correctCount ?: 0 }
        val totalAttempts = allWords.sumOf { p ->
            val prog = dbHelper.getProgress(p.id)
            (prog?.correctCount ?: 0) + (prog?.incorrectCount ?: 0)
        }
        val rate = if (totalAttempts > 0) (totalCorrect * 100 / totalAttempts) else 0
        tvRetention.text = "${rate}%"

        // 各阶段进度
        stageContainer.removeAllViews()
        val stages = WordRepository.getStages()
        for (stage in stages) {
            val words = WordRepository.getWordsByStage(stage.id)
            val done = words.count { dbHelper.getProgress(it.id)?.isMastered == true }
            val pct = if (words.isNotEmpty()) (done * 100 / words.size) else 0

            val row = LayoutInflater.from(requireContext()).inflate(R.layout.item_stage, stageContainer, false)
            row.findViewById<TextView>(R.id.tvEmoji).text = stage.emoji
            row.findViewById<TextView>(R.id.tvName).text = stage.name
            row.findViewById<TextView>(R.id.tvDesc).text = stage.description
            row.findViewById<TextView>(R.id.tvCount).text = "${words.size}词"
            row.findViewById<TextView>(R.id.tvProgress).text = "${pct}%"
            row.findViewById<TextView>(R.id.viewLock).visibility = View.GONE

            stageContainer.addView(row)
        }
    }
}
