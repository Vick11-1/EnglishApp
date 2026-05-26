package com.englishbeginner.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.englishbeginner.App
import com.englishbeginner.R
import com.englishbeginner.data.WordRepository

class LearnFragment : Fragment() {

    private var currentStageId = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_learn, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        val tvStageInfo = view.findViewById<TextView>(R.id.tvStageInfo)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        val btnPrev = view.findViewById<Button>(R.id.btnPrev)
        val btnNext = view.findViewById<Button>(R.id.btnNext)
        val btnStartLearn = view.findViewById<Button>(R.id.btnStartLearn)
        val recycler = view.findViewById<RecyclerView>(R.id.recyclerWords)

        fun refreshStageUI() {
            val stages = WordRepository.getStages()
            val stage = stages.first { it.id == currentStageId }
            val words = WordRepository.getWordsByStage(currentStageId)
            val dbHelper = App.dbHelper
            val learned = words.count { w -> dbHelper.getProgress(w.id) != null }

            tvTitle.text = "${stage.emoji} ${stage.name}"
            tvStageInfo.text = "${stage.description}  ($learned / ${words.size} 词)"
            progressBar.max = words.size
            progressBar.progress = learned

            btnPrev.visibility = if (currentStageId > 0) View.VISIBLE else View.INVISIBLE
            btnNext.visibility = if (currentStageId < stages.size - 1) View.VISIBLE else View.INVISIBLE

            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = WordListAdapter(words)
        }

        btnPrev.setOnClickListener { if (currentStageId > 0) { currentStageId--; refreshStageUI() } }
        btnNext.setOnClickListener { if (currentStageId < 20) { currentStageId++; refreshStageUI() } }

        btnStartLearn.setOnClickListener {
            val intent = Intent(requireContext(), LearnActivity::class.java)
            intent.putExtra("stage_id", currentStageId)
            startActivity(intent)
        }

        refreshStageUI()
    }
}
