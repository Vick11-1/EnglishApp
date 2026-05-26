package com.englishbeginner.ui

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.englishbeginner.App
import com.englishbeginner.R
import com.englishbeginner.data.Stage
import com.englishbeginner.data.WordRepository

class StageAdapter(
    private val stages: List<Stage>,
    private val onClick: (Stage) -> Unit
) : RecyclerView.Adapter<StageAdapter.VH>() {

    class VH(view: View) : RecyclerView.ViewHolder(view) {
        val tvEmoji: TextView = view.findViewById(R.id.tvEmoji)
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvDesc: TextView = view.findViewById(R.id.tvDesc)
        val tvCount: TextView = view.findViewById(R.id.tvCount)
        val tvProgress: TextView = view.findViewById(R.id.tvProgress)
        val tvLock: TextView = view.findViewById(R.id.viewLock)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_stage, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val stage = stages[position]
        val context = holder.itemView.context
        val dbHelper = App.dbHelper
        val words = WordRepository.getWordsByStage(stage.id)
        val mastered = words.count { dbHelper.getProgress(it.id)?.isMastered == true }

        holder.tvEmoji.text = stage.emoji
        holder.tvName.text = stage.name
        holder.tvDesc.text = stage.description
        holder.tvCount.text = "${words.size}词"
        holder.tvProgress.text = "${mastered}/${words.size}"

        val unlocked = WordRepository.isStageUnlocked(stage.id)
        if (unlocked) {
            holder.itemView.alpha = 1.0f
            holder.tvLock.visibility = View.GONE
            holder.itemView.setOnClickListener { onClick(stage) }
        } else {
            holder.itemView.alpha = 0.5f
            holder.tvLock.visibility = View.VISIBLE
            holder.tvLock.text = "🔒"
            holder.tvProgress.text = "未解锁"
            holder.itemView.setOnClickListener(null)
        }
    }

    override fun getItemCount() = stages.size
}
