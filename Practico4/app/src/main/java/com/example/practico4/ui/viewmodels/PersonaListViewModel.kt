package com.example.practico4.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practico4.models.Personas
import com.example.practico4.repositories.PersonaRepository


class PersonaListViewModel : ViewModel() {
    private val _personaList = MutableLiveData<Personas>().apply {
        value = arrayListOf()
    }
    val personaList: LiveData<Personas> = _personaList

    fun getPersonaList() {
        PersonaRepository.getPersonaList(
            onSuccess = {
                _personaList.value = it

            }, onError = {
                it.printStackTrace()
            }
        )
    }

}