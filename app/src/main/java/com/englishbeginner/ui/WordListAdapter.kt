package com.englishbeginner.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.englishbeginner.App
import com.englishbeginner.R
import com.englishbeginner.data.Word

class WordListAdapter(private val words: List<Word>) : RecyclerView.Adapter<WordListAdapter.VH>() {

    class VH(view: View) : RecyclerView.ViewHolder(view) {
        val tvEnglish: TextView = view.findViewById(R.id.tvEnglish)
        val tvPhonetic: TextView = view.findViewById(R.id.tvPhonetic)
        val tvChinese: TextView = view.findViewById(R.id.tvChinese)
        val tvStatus: TextView = view.findViewById(R.id.tvStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_word_list, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val word = words[position]
        holder.tvEnglish.text = word.english
        holder.tvPhonetic.text = word.phonetic
        holder.tvChinese.text = word.chinese

        val progress = App.dbHelper.getProgress(word.id)
        holder.tvStatus.visibility = View.VISIBLE
        when {
            progress?.isMastered == true -> {
                holder.tvStatus.text = "✅"
            }
            progress != null -> {
                holder.tvStatus.text = "📖"
            }
            else -> {
                holder.tvStatus.text = "🆕"
            }
        }
    }

    override fun getItemCount() = words.size
}
