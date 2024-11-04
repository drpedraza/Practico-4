package com.example.practico4.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practico4.models.Email
import com.example.practico4.repositories.EmailRepository

class EmailInsertViewModel : ViewModel() {
    private val _emailInsert = MutableLiveData<Email>()
    val emailInsert: LiveData<Email> = _emailInsert

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    fun insertEmail(email: Email) {
        EmailRepository.insertEmail(email,
            onSuccess = {
                _emailInsert.value = it
            }, onError = {
                _error.value = it
            })
    }
}