package com.example.practico4.models

data class Email (
    val id: Int,
    val email: String,
    val persona_id: Int,
    val label: String

)
typealias Emails = ArrayList<Email>