package com.englishbeginner.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.englishbeginner.App
import com.englishbeginner.R
import com.englishbeginner.data.Word
import com.englishbeginner.data.WordRepository
import com.englishbeginner.logic.SpacedRepetition

class LearnActivity : AppCompatActivity() {

    private lateinit var words: List<Word>
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn)

        val stageId = intent.getIntExtra("stage_id", 0)
        words = WordRepository.getWordsByStage(stageId)
        if (words.isEmpty()) { finish(); return }

        WordRepository.initStageIfNeeded(stageId)

        val tvStageName = findViewById<TextView>(R.id.tvStageName)
        val tvProgress = findViewById<TextView>(R.id.tvProgress)
        val tvEnglish = findViewById<TextView>(R.id.tvEnglish)
        val tvPhonetic = findViewById<TextView>(R.id.tvPhonetic)
        val tvChinese = findViewById<TextView>(R.id.tvChinese)
        val tvMnemonic = findViewById<TextView>(R.id.tvMnemonic)
        val tvComplete = findViewById<TextView>(R.id.tvComplete)
        val answerArea = findViewById<LinearLayout>(R.id.answerArea)
        val btnFlip = findViewById<Button>(R.id.btnFlip)
        val btnKnow = findViewById<Button>(R.id.btnKnow)
        val btnDontKnow = findViewById<Button>(R.id.btnDontKnow)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        val stage = WordRepository.getStages().first { it.id == stageId }
        tvStageName.text = stage.emoji + " " + stage.name
        progressBar.max = words.size

        fun showWord() {
            if (currentIndex >= words.size) {
                tvEnglish.text = "🎉"
                tvPhonetic.text = ""
                tvComplete.text = "全部完成！"
                tvComplete.visibility = View.VISIBLE
                answerArea.visibility = View.GONE
                btnFlip.visibility = View.GONE
                btnKnow.visibility = View.GONE
                btnDontKnow.visibility = View.GONE
                return
            }
            val word = words[currentIndex]
            tvProgress.text = (currentIndex + 1).toString() + " / " + words.size
            progressBar.progress = currentIndex
            tvEnglish.text = word.english
            tvPhonetic.text = word.phonetic
            answerArea.visibility = View.GONE
            btnKnow.visibility = View.GONE
            btnDontKnow.visibility = View.GONE
            btnFlip.visibility = View.VISIBLE
            btnFlip.text = "查看答案"
        }

        btnFlip.setOnClickListener {
            val word = words[currentIndex]
            tvChinese.text = word.chinese
            tvMnemonic.text = word.mnemonic
            answerArea.visibility = View.VISIBLE
            btnFlip.visibility = View.GONE
            btnKnow.visibility = View.VISIBLE
            btnDontKnow.visibility = View.VISIBLE
            val existing = App.dbHelper.getProgress(word.id)
            if (existing == null) {
                App.dbHelper.saveProgress(SpacedRepetition.newProgress(word.id, stageId))
            }
        }

        btnKnow.setOnClickListener {
            val word = words[currentIndex]
            val prog = App.dbHelper.getProgress(word.id) ?: SpacedRepetition.newProgress(word.id, stageId)
            App.dbHelper.saveProgress(SpacedRepetition.updateAfterReview(prog, true))
            currentIndex++
            showWord()
        }

        btnDontKnow.setOnClickListener {
            val word = words[currentIndex]
            val prog = App.dbHelper.getProgress(word.id) ?: SpacedRepetition.newProgress(word.id, stageId)
            App.dbHelper.saveProgress(SpacedRepetition.updateAfterReview(prog, false))
            currentIndex++
            showWord()
        }

        showWord()
    }
}
