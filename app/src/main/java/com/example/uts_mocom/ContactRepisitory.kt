package com.example.uts_mocom

import com.example.uts_mocom.data.Contact

class ContactRepository {
    companion object {
        val contacts = mutableListOf<Contact>()
    }
}
