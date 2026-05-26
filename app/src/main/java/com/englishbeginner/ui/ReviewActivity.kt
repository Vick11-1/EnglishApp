package com.englishbeginner.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.englishbeginner.App
import com.englishbeginner.R
import com.englishbeginner.data.Word
import com.englishbeginner.data.WordRepository
import com.englishbeginner.logic.SpacedRepetition

class ReviewActivity : AppCompatActivity() {

    private lateinit var dueWords: List<Word>
    private var currentIndex = 0
    private var correctCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        val allWords = WordRepository.getAllWords()
        val dbHelper = App.dbHelper
        dueWords = allWords.filter { word ->
            val prog = dbHelper.getProgress(word.id)
            prog != null && SpacedRepetition.isDue(prog)
        }

        if (dueWords.isEmpty()) {
            AlertDialog.Builder(this)
                .setTitle("复习完毕")
                .setMessage("没有待复习的单词了")
                .setPositiveButton("返回") { _, _ -> finish() }
                .setCancelable(false)
                .show()
            return
        }

        val tvProgress = findViewById<TextView>(R.id.tvProgress)
        val tvEnglish = findViewById<TextView>(R.id.tvEnglish)
        val tvPhonetic = findViewById<TextView>(R.id.tvPhonetic)
        val tvChinese = findViewById<TextView>(R.id.tvChinese)
        val tvMnemonic = findViewById<TextView>(R.id.tvMnemonic)
        val tvComplete = findViewById<TextView>(R.id.tvComplete)
        val answerArea = findViewById<LinearLayout>(R.id.answerArea)
        val btnFlip = findViewById<Button>(R.id.btnFlip)
        val btnAgain = findViewById<Button>(R.id.btnAgain)
        val btnHard = findViewById<Button>(R.id.btnHard)
        val btnGood = findViewById<Button>(R.id.btnGood)
        val btnEasy = findViewById<Button>(R.id.btnEasy)
        val rateButtons = listOf(btnAgain, btnHard, btnGood, btnEasy)

        fun showCard() {
            if (currentIndex >= dueWords.size) {
                val pct = if (dueWords.isNotEmpty()) (correctCount * 100 / dueWords.size) else 0
                AlertDialog.Builder(this)
                    .setTitle("复习完成")
                    .setMessage("正确 $correctCount / ${dueWords.size}，正确率 $pct%")
                    .setPositiveButton("返回") { _, _ -> finish() }
                    .setCancelable(false)
                    .show()
                return
            }
            val word = dueWords[currentIndex]
            tvProgress.text = "复习 ${currentIndex + 1}/${dueWords.size}"
            tvEnglish.text = word.english
            tvPhonetic.text = word.phonetic
            answerArea.visibility = View.GONE
            btnFlip.visibility = View.VISIBLE
            rateButtons.forEach { it.visibility = View.GONE }
        }

        btnFlip.setOnClickListener {
            val word = dueWords[currentIndex]
            tvChinese.text = word.chinese
            tvMnemonic.text = word.mnemonic
            answerArea.visibility = View.VISIBLE
            btnFlip.visibility = View.GONE
            rateButtons.forEach { it.visibility = View.VISIBLE }
        }

        fun handleAnswer(correct: Boolean) {
            val word = dueWords[currentIndex]
            val prog = App.dbHelper.getProgress(word.id)
                ?: SpacedRepetition.newProgress(word.id, word.stageId)
            val updated = SpacedRepetition.updateAfterReview(prog, correct)
            App.dbHelper.saveProgress(updated)
            if (correct) correctCount++
            currentIndex++
            showCard()
        }

        btnAgain.setOnClickListener { handleAnswer(false) }
        btnHard.setOnClickListener { handleAnswer(false) }
        btnGood.setOnClickListener { handleAnswer(true) }
        btnEasy.setOnClickListener { handleAnswer(true) }

        showCard()
    }
}
