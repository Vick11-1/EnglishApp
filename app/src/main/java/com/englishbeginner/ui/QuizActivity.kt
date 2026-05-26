package com.englishbeginner.ui

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.englishbeginner.App
import com.englishbeginner.R
import com.englishbeginner.data.Word
import com.englishbeginner.data.WordRepository
import com.englishbeginner.logic.SpacedRepetition

class QuizActivity : AppCompatActivity() {

    private lateinit var allWords: List<Word>
    private lateinit var quizWords: List<Word>
    private var currentIndex = 0
    private var correctCount = 0
    private var stageId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        stageId = intent.getIntExtra("stage_id", 0)
        allWords = WordRepository.getAllWords()
        quizWords = WordRepository.getWordsByStage(stageId).shuffled().take(10)
        if (quizWords.isEmpty()) { finish(); return }

        val tvScore = findViewById<TextView>(R.id.tvScore)
        val tvQuestion = findViewById<TextView>(R.id.tvQuestion)
        val btnOpt1 = findViewById<Button>(R.id.btnOpt1)
        val btnOpt2 = findViewById<Button>(R.id.btnOpt2)
        val btnOpt3 = findViewById<Button>(R.id.btnOpt3)
        val btnOpt4 = findViewById<Button>(R.id.btnOpt4)
        val buttons = listOf(btnOpt1, btnOpt2, btnOpt3, btnOpt4)

        fun showQuestion() {
            if (currentIndex >= quizWords.size) {
                val pct = (correctCount * 100) / quizWords.size
                AlertDialog.Builder(this)
                    .setTitle("测验完成")
                    .setMessage("正确 $correctCount / ${quizWords.size}，正确率 $pct%")
                    .setPositiveButton("返回") { _, _ -> finish() }
                    .setCancelable(false)
                    .show()
                return
            }

            val word = quizWords[currentIndex]
            tvScore.text = "第 ${currentIndex + 1}/${quizWords.size} 题  正确 $correctCount"
            tvQuestion.text = "${word.english}\n\n${word.phonetic}"

            // 生成4个选项（1个正确 + 3个干扰）
            val correctIndex = (0..3).random()
            val distractors = allWords.filter { it.id != word.id }.shuffled().take(3)
            val options = mutableListOf<String>()
            var dIdx = 0
            for (i in 0..3) {
                if (i == correctIndex) {
                    options.add(word.chinese)
                } else {
                    options.add(distractors[dIdx].chinese)
                    dIdx++
                }
            }

            buttons.forEachIndexed { idx, btn ->
                btn.text = options[idx]
                btn.isEnabled = true
                btn.setBackgroundColor(getColor(R.color.white))
                btn.setOnClickListener {
                    // 禁用所有按钮防止重复点击
                    buttons.forEach { it.isEnabled = false }

                    val prog = App.dbHelper.getProgress(word.id)
                        ?: SpacedRepetition.newProgress(word.id, stageId)
                    if (idx == correctIndex) {
                        correctCount++
                        btn.setBackgroundColor(getColor(R.color.correct_green))
                        val updated = SpacedRepetition.updateAfterReview(prog, true)
                        App.dbHelper.saveProgress(updated)
                    } else {
                        btn.setBackgroundColor(getColor(R.color.wrong_red))
                        buttons[correctIndex].setBackgroundColor(getColor(R.color.correct_green))
                        val updated = SpacedRepetition.updateAfterReview(prog, false)
                        App.dbHelper.saveProgress(updated)
                    }
                    tvScore.postDelayed({
                        currentIndex++
                        showQuestion()
                    }, 800)
                }
            }
        }

        showQuestion()
    }
}
