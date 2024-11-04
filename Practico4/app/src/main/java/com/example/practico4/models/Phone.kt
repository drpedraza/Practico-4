package com.example.practico4.models

data class Phone (
    val id: Int,
    val number: String,
    val persona_id: Int,
    val label: String
)
typealias Phones = ArrayList<Phone>