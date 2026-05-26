package com.englishbeginner.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        const val DB_NAME = "english_beginner.db"
        const val DB_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE progress (
                word_id INTEGER PRIMARY KEY,
                stage_id INTEGER,
                review_level INTEGER DEFAULT 0,
                next_review_date TEXT,
                last_review_date TEXT,
                correct_count INTEGER DEFAULT 0,
                incorrect_count INTEGER DEFAULT 0,
                is_mastered INTEGER DEFAULT 0
            )
        """)
        db.execSQL("""
            CREATE TABLE daily_stats (
                date TEXT PRIMARY KEY,
                words_learned INTEGER DEFAULT 0,
                words_reviewed INTEGER DEFAULT 0,
                study_minutes INTEGER DEFAULT 0,
                correct_rate REAL DEFAULT 0
            )
        """)
        db.execSQL("""
            CREATE TABLE settings (
                `key` TEXT PRIMARY KEY,
                `value` TEXT
            )
        """)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS progress")
        db.execSQL("DROP TABLE IF EXISTS daily_stats")
        db.execSQL("DROP TABLE IF EXISTS settings")
        onCreate(db)
    }

    fun getProgress(wordId: Int): LearningProgress? {
        val db = readableDatabase
        val cursor = db.query("progress", null, "word_id=?", arrayOf(wordId.toString()), null, null, null)
        return cursor.use {
            if (it.moveToFirst()) {
                LearningProgress(
                    wordId = it.getInt(it.getColumnIndexOrThrow("word_id")),
                    stageId = it.getInt(it.getColumnIndexOrThrow("stage_id")),
                    reviewLevel = it.getInt(it.getColumnIndexOrThrow("review_level")),
                    nextReviewDate = it.getString(it.getColumnIndexOrThrow("next_review_date")),
                    lastReviewDate = it.getString(it.getColumnIndexOrThrow("last_review_date")),
                    correctCount = it.getInt(it.getColumnIndexOrThrow("correct_count")),
                    incorrectCount = it.getInt(it.getColumnIndexOrThrow("incorrect_count")),
                    isMastered = it.getInt(it.getColumnIndexOrThrow("is_mastered")) == 1
                )
            } else null
        }
    }

    fun saveProgress(progress: LearningProgress) {
        val db = writableDatabase
        val cv = ContentValues().apply {
            put("word_id", progress.wordId)
            put("stage_id", progress.stageId)
            put("review_level", progress.reviewLevel)
            put("next_review_date", progress.nextReviewDate)
            put("last_review_date", progress.lastReviewDate)
            put("correct_count", progress.correctCount)
            put("incorrect_count", progress.incorrectCount)
            put("is_mastered", if (progress.isMastered) 1 else 0)
        }
        db.insertWithOnConflict("progress", null, cv, SQLiteDatabase.CONFLICT_REPLACE)
    }

    fun getDueWords(): List<LearningProgress> {
        val today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val db = readableDatabase
        val cursor = db.query("progress", null, "next_review_date<=? AND is_mastered=0", arrayOf(today), null, null, "next_review_date ASC")
        return cursor.use {
            val list = mutableListOf<LearningProgress>()
            while (it.moveToNext()) {
                list.add(LearningProgress(
                    wordId = it.getInt(it.getColumnIndexOrThrow("word_id")),
                    stageId = it.getInt(it.getColumnIndexOrThrow("stage_id")),
                    reviewLevel = it.getInt(it.getColumnIndexOrThrow("review_level")),
                    nextReviewDate = it.getString(it.getColumnIndexOrThrow("next_review_date")),
                    lastReviewDate = it.getString(it.getColumnIndexOrThrow("last_review_date")),
                    correctCount = it.getInt(it.getColumnIndexOrThrow("correct_count")),
                    incorrectCount = it.getInt(it.getColumnIndexOrThrow("incorrect_count")),
                    isMastered = it.getInt(it.getColumnIndexOrThrow("is_mastered")) == 1
                ))
            }
            list
        }
    }

    fun getStageProgress(stageId: Int): Pair<Int, Int> {
        val db = readableDatabase
        val total = db.rawQuery("SELECT COUNT(*) FROM progress WHERE stage_id=?", arrayOf(stageId.toString())).use {
            it.moveToFirst(); it.getInt(0)
        }
        val mastered = db.rawQuery("SELECT COUNT(*) FROM progress WHERE stage_id=? AND is_mastered=1", arrayOf(stageId.toString())).use {
            it.moveToFirst(); it.getInt(0)
        }
        return Pair(mastered, total)
    }

    fun getStreakDays(): Int {
        val db = readableDatabase
        val cursor = db.query("daily_stats", arrayOf("date"), null, null, null, null, "date DESC")
        val dates = cursor.use {
            val list = mutableListOf<String>()
            while (it.moveToNext()) { list.add(it.getString(0)) }
            list
        }
        if (dates.isEmpty()) return 0
        var streak = 0
        var checkDate = LocalDate.now()
        for (dateStr in dates) {
            if (dateStr == checkDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))) {
                streak++
                checkDate = checkDate.minusDays(1)
            } else break
        }
        return streak
    }

    fun getTodayStats(): DailyStats {
        val today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val db = readableDatabase
        val cursor = db.query("daily_stats", null, "date=?", arrayOf(today), null, null, null)
        return cursor.use {
            if (it.moveToFirst()) {
                DailyStats(
                    date = it.getString(it.getColumnIndexOrThrow("date")),
                    wordsLearned = it.getInt(it.getColumnIndexOrThrow("words_learned")),
                    wordsReviewed = it.getInt(it.getColumnIndexOrThrow("words_reviewed")),
                    studyMinutes = it.getInt(it.getColumnIndexOrThrow("study_minutes")),
                    correctRate = it.getDouble(it.getColumnIndexOrThrow("correct_rate"))
                )
            } else DailyStats(date = today)
        }
    }

    fun updateTodayStats(addLearned: Int, addReviewed: Int, addMinutes: Int) {
        val today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val existing = getTodayStats()
        val cv = ContentValues().apply {
            put("date", today)
            put("words_learned", existing.wordsLearned + addLearned)
            put("words_reviewed", existing.wordsReviewed + addReviewed)
            put("study_minutes", existing.studyMinutes + addMinutes)
            put("correct_rate", existing.correctRate)
        }
        writableDatabase.insertWithOnConflict("daily_stats", null, cv, SQLiteDatabase.CONFLICT_REPLACE)
    }

    fun getTotalLearnedWords(): Int {
        val db = readableDatabase
        return db.rawQuery("SELECT COUNT(*) FROM progress", null).use { it.moveToFirst(); it.getInt(0) }
    }

    fun getTotalMasteredWords(): Int {
        val db = readableDatabase
        return db.rawQuery("SELECT COUNT(*) FROM progress WHERE is_mastered=1", null).use { it.moveToFirst(); it.getInt(0) }
    }

    fun getDailyStats(): List<DailyStats> {
        val db = readableDatabase
        val cursor = db.query("daily_stats", null, null, null, null, null, "date DESC", "30")
        return cursor.use {
            val list = mutableListOf<DailyStats>()
            while (it.moveToNext()) {
                list.add(DailyStats(
                    date = it.getString(it.getColumnIndexOrThrow("date")),
                    wordsLearned = it.getInt(it.getColumnIndexOrThrow("words_learned")),
                    wordsReviewed = it.getInt(it.getColumnIndexOrThrow("words_reviewed")),
                    studyMinutes = it.getInt(it.getColumnIndexOrThrow("study_minutes")),
                    correctRate = it.getDouble(it.getColumnIndexOrThrow("correct_rate"))
                ))
            }
            list
        }
    }

    fun resetAll() {
        writableDatabase.delete("progress", null, null)
        writableDatabase.delete("daily_stats", null, null)
    }

    fun initStageProgress(words: List<Word>) {
        val db = writableDatabase
        val today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        db.beginTransaction()
        try {
            for (word in words) {
                val cursor = db.query("progress", arrayOf("word_id"), "word_id=?", arrayOf(word.id.toString()), null, null, null)
                val exists = cursor.use { it.moveToFirst() }
                if (!exists) {
                    val cv = ContentValues().apply {
                        put("word_id", word.id)
                        put("stage_id", word.stageId)
                        put("review_level", 0)
                        put("next_review_date", today)
                        put("last_review_date", "")
                        put("correct_count", 0)
                        put("incorrect_count", 0)
                        put("is_mastered", 0)
                    }
                    db.insert("progress", null, cv)
                }
            }
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
    }
}
