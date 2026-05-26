package com.englishbeginner.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.englishbeginner.App
import com.englishbeginner.R
import com.englishbeginner.data.WordRepository

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvGreeting = view.findViewById<TextView>(R.id.tvGreeting)
        val tvStats = view.findViewById<TextView>(R.id.tvStats)
        val recycler = view.findViewById<RecyclerView>(R.id.recyclerStages)

        val stages = WordRepository.getStages()
        val dbHelper = App.dbHelper

        // 统计
        val totalWords = stages.sumOf { it.wordCount }
        var mastered = 0
        stages.forEach { stage ->
            val words = WordRepository.getWordsByStage(stage.id)
            mastered += words.count { w -> dbHelper.getProgress(w.id)?.isMastered == true }
        }
        tvStats.text = "已掌握 $mastered / $totalWords 词"

        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = StageAdapter(stages) { stage ->
            val intent = Intent(requireContext(), StageDetailActivity::class.java)
            intent.putExtra("stage_id", stage.id)
            startActivity(intent)
        }
    }
}
