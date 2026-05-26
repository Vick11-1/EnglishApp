package com.englishbeginner.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.englishbeginner.App
import com.englishbeginner.R
import com.englishbeginner.data.WordRepository

class MeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_me, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvTotalWords = view.findViewById<TextView>(R.id.tvTotalWords)
        val tvAppVersion = view.findViewById<TextView>(R.id.tvAppVersion)
        val btnReset = view.findViewById<Button>(R.id.btnReset)

        val allWords = WordRepository.getAllWords()
        tvTotalWords.text = "题库共 ${allWords.size} 词"
        tvAppVersion.text = "版本 1.0.0"

        btnReset.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("确认重置")
                .setMessage("将清除所有学习进度，单词数据不变。确定吗？")
                .setPositiveButton("确定") { _, _ ->
                    App.dbHelper.resetAll()
                    btnReset.post {
                        tvTotalWords.text = "题库共 ${allWords.size} 词（进度已重置）"
                    }
                }
                .setNegativeButton("取消", null)
                .show()
        }
    }
}
