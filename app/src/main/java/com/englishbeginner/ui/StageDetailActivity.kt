package com.englishbeginner.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.englishbeginner.App
import com.englishbeginner.R
import com.englishbeginner.data.WordRepository
import com.englishbeginner.logic.SpacedRepetition

class StageDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stage_detail)

        val stageId = intent.getIntExtra("stage_id", 0)
        val stage = WordRepository.getStages().firstOrNull { it.id == stageId } ?: run { finish(); return }
        val words = WordRepository.getWordsByStage(stageId)

        val tvStageName = findViewById<TextView>(R.id.tvStageName)
        val tvStageDesc = findViewById<TextView>(R.id.tvStageDesc)
        val tvWordCount = findViewById<TextView>(R.id.tvWordCount)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val btnLearn = findViewById<Button>(R.id.btnLearn)
        val btnQuiz = findViewById<Button>(R.id.btnQuiz)

        tvStageName.text = "${stage.emoji} ${stage.name}"
        tvStageDesc.text = stage.description

        val dbHelper = App.dbHelper
        val mastered = words.count { dbHelper.getProgress(it.id)?.isMastered == true }

        tvWordCount.text = "共 ${words.size} 词，已掌握 $mastered"
        progressBar.max = words.size
        progressBar.progress = mastered

        btnLearn.setOnClickListener {
            startActivity(Intent(this, LearnActivity::class.java).putExtra("stage_id", stageId))
        }
        btnQuiz.setOnClickListener {
            startActivity(Intent(this, QuizActivity::class.java).putExtra("stage_id", stageId))
        }
    }
}
