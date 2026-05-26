package com.englishbeginner.data

data class Word(
    val id: Int,
    val stageId: Int,
    val english: String,
    val chinese: String,
    val phonetic: String = "",
    val mnemonic: String = "",
    val example: String = ""
)
