package com.englishbeginner.data

/**
 * 紧凑格式: stageId|english|chinese|phonetic|mnemonic
 * 多个阶段数据拼接后统一解析
 */
object WordDataRaw {
    private var idCounter = 0

    fun parseAll(): List<Word> {
        val all = mutableListOf<Word>()
        val chunks = listOf(
            DataPart0.raw, DataPart1.raw, DataPart2.raw,
            DataPart3.raw, DataPart4.raw, DataPart5.raw,
            DataPart6.raw, DataPart7.raw
        )
        for (raw in chunks) {
            all.addAll(parse(raw))
        }
        return all
    }

    private fun parse(raw: String): List<Word> {
        return raw.trim().lines()
            .filter { it.isNotBlank() }
            .map { line ->
                val p = line.split("|")
                Word(
                    id = ++idCounter,
                    stageId = p[0].toInt(),
                    english = p[1],
                    chinese = p[2],
                    phonetic = p.getOrNull(3) ?: "",
                    mnemonic = p.getOrNull(4) ?: ""
                )
            }
    }
}
