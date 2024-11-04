package com.example.practico4.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practico4.models.Persona
import com.example.practico4.repositories.PersonaRepository

class PersonaInsertViewModel : ViewModel() {
    private val _personaInsert = MutableLiveData<Persona>()
    val personaInsert: LiveData<Persona> = _personaInsert

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    fun insertPersona(persona: Persona) {
        PersonaRepository.insertPersona(persona,
            onSuccess = {
                _personaInsert.value = it
            }, onError = {
                _error.value = it
            })
    }

    val persona = MutableLiveData<Persona>()

    fun loadPersona(id: Int) {
        PersonaRepository.getPersonaById(id,
            onSuccess = { persona.value = it },
            onError = { _error.value = it }
        )
    }

    fun updatePersona(id: Int, persona: Persona) {
        PersonaRepository.updatePersona(id, persona,
            onSuccess = {
                _personaInsert.value = it
            }, onError = {
                _error.value = it
            })
    }


}