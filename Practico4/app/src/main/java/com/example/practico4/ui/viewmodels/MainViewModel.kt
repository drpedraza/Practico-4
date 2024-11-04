package com.example.practico4.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practico4.repositories.PersonaRepository

class MainViewModel : ViewModel() {
    private val _profilePicture = MutableLiveData<String>()
    val profilePicture: LiveData<String> = _profilePicture


    private val _name = MutableLiveData<String>().apply {
        value = ""
    }
    val name: LiveData<String> = _name

    private val _lastName = MutableLiveData<String>().apply {
        value = ""
    }
    val lastName: LiveData<String> = _lastName


    private val _company = MutableLiveData<String>().apply {
        value = ""
    }
    val company: LiveData<String> = _company

    private val _address = MutableLiveData<String>().apply {
        value = ""
    }
    val address: LiveData<String> = _address

    private val _city = MutableLiveData<String>().apply {
        value = ""
    }
    val city: LiveData<String> = _city

    private val _state = MutableLiveData<String>().apply {
        value = ""
    }
    val state: LiveData<String> = _state

    private val _personaDeleted = MutableLiveData<Boolean>()
    val personaDeleted: LiveData<Boolean> = _personaDeleted

    fun getPostById(id: Int) {
        PersonaRepository.getPersonaById(id,
            onSuccess = {
                _name.value = it.name
                _lastName.value = it.last_name
                _company.value = it.company
                _address.value = it.address
                _city.value = it.city
                _state.value = it.state
                _profilePicture.value = it.profile_picture
            }, onError = {
                println("Error: $it")
            })
    }

    fun deletePersona(id: Int) {
        PersonaRepository.deletePersona(id,
            onSuccess = {
                _personaDeleted.value = true
                println("Deleted")
            }, onError = {
                _personaDeleted.value = false
                println("Error: $it")
            })
    }


}