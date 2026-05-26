package com.englishbeginner.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.englishbeginner.App
import com.englishbeginner.R
import com.englishbeginner.data.WordRepository
import com.englishbeginner.logic.SpacedRepetition

class ReviewFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvDueCount = view.findViewById<TextView>(R.id.tvDueCount)
        val tvSummary = view.findViewById<TextView>(R.id.tvSummary)
        val btnStartReview = view.findViewById<Button>(R.id.btnStartReview)

        val allWords = WordRepository.getAllWords()
        val dbHelper = App.dbHelper
        val dueWords = allWords.filter { word ->
            val prog = dbHelper.getProgress(word.id)
            prog != null && SpacedRepetition.isDue(prog)
        }

        val mastered = allWords.count { w -> dbHelper.getProgress(w.id)?.isMastered == true }

        tvDueCount.text = dueWords.size.toString()
        tvSummary.text = "已掌握 $mastered / ${allWords.size} 词"

        btnStartReview.isEnabled = dueWords.isNotEmpty()
        if (dueWords.isEmpty()) {
            btnStartReview.text = "✅ 今日复习已完成"
            btnStartReview.alpha = 0.5f
        }

        btnStartReview.setOnClickListener {
            val intent = Intent(requireContext(), ReviewActivity::class.java)
            startActivity(intent)
        }
    }
}
