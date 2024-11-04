package com.example.practico4.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practico4.models.Phone
import com.example.practico4.models.Phones
import com.example.practico4.repositories.PhoneRepository

class PhoneInsertViewModel : ViewModel() {
    private val _phoneInsert = MutableLiveData<Phone>()
    val phoneInsert: LiveData<Phone> = _phoneInsert

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    fun insertPhone(phone: Phone) {
        PhoneRepository.insertPhone(phone,
            onSuccess = {
                _phoneInsert.value = it
            }, onError = {
                _error.value = it
            })
    }
    private val _phoneList = MutableLiveData<Phones>().apply {
        value = arrayListOf()
    }
    val phoneList: LiveData<Phones> = _phoneList

    fun getPhoneList() {
        PhoneRepository.getPhoneList(
            onSuccess = {
                _phoneList.value = it

            }, onError = {
                _error.value = it
            }
        )
    }
}