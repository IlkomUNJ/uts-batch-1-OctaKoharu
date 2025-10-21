package com.example.uts_mocom.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.uts_mocom.data.Contact

class ContactViewModel : ViewModel() {

    private val _contacts = mutableStateListOf<Contact>()
    val contacts: List<Contact> get() = _contacts

    fun addContact(contact: Contact) {
        _contacts.add(contact)
    }

    fun updateContact(index: Int, updated: Contact) {
        if (index in _contacts.indices) {
            _contacts[index] = updated
        }
    }
}