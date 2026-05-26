package com.englishbeginner.data

import com.englishbeginner.logic.SpacedRepetition

object WordRepository {
    private var allWords: List<Word> = emptyList()

    fun getAllWords(): List<Word> {
        if (allWords.isEmpty()) {
            allWords = WordDataRaw.parseAll()
        }
        return allWords
    }

    fun getWordsByStage(stageId: Int): List<Word> {
        return getAllWords().filter { it.stageId == stageId }
    }

    fun getStages(): List<Stage> {
        return listOf(
            Stage(0, "字母入门", "26个英文字母及基础发音", "🔤", 26),
            Stage(1, "数字", "基数词、序数词、量词", "🔢", 136),
            Stage(2, "时间与日期", "星期、钟点、日常时间", "📅", 45),
            Stage(3, "月份与季节", "12个月份、四季、节日", "🌤", 34),
            Stage(4, "天气与自然", "天气现象、自然环境", "🌈", 60),
            Stage(5, "水果食物饮料", "常见饮食词汇", "🍎", 80),
            Stage(6, "动物世界", "各类常见动物", "🐾", 90),
            Stage(7, "家庭与称谓", "家庭成员及关系", "👨‍👩‍👧", 35),
            Stage(8, "身体部位", "人体各部位名称", "🦴", 50),
            Stage(9, "颜色与形状", "颜色、形状词汇", "🎨", 35),
            Stage(10, "日常用品与衣物", "生活物品和服装", "👕", 80),
            Stage(11, "基础动词", "日常动作词汇", "🏃", 90),
            Stage(12, "基础形容词", "常用描述词", "📝", 70),
            Stage(13, "基础短句", "日常常用句型", "💬", 60),
            Stage(14, "方位与位置", "方向、位置表达", "🧭", 25),
            Stage(15, "交通工具", "各类出行方式", "🚗", 25),
            Stage(16, "职业", "常见职业名称", "👷", 18),
            Stage(17, "学校与学习", "校园场景词汇", "🏫", 30),
            Stage(18, "情绪与感受", "情感表达词汇", "😊", 18),
            Stage(19, "购物与金钱", "消费场景词汇", "🛒", 25),
            Stage(20, "餐厅点餐", "餐饮场景词汇", "🍽", 25)
        )
    }

    fun isStageUnlocked(stageId: Int): Boolean {
        if (stageId == 0) return true
        val prevWords = getWordsByStage(stageId - 1)
        if (prevWords.isEmpty()) return true
        val prevMastered = prevWords.count { word ->
            val dbHelper = com.englishbeginner.App.dbHelper
            dbHelper.getProgress(word.id)?.isMastered == true
        }
        return prevMastered >= prevWords.size * 0.8
    }

    fun initStageIfNeeded(stageId: Int) {
        val words = getWordsByStage(stageId)
        com.englishbeginner.App.dbHelper.initStageProgress(words)
    }
}
