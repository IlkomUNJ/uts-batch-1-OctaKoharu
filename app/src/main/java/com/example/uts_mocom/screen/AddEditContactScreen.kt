package com.example.uts_mocom.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api // Import required for TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.uts_mocom.ContactRepository
import com.example.uts_mocom.data.Contact
import com.example.uts_mocom.viewmodel.ContactViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditContactScreen(
    navController: NavController,
    viewModel: ContactViewModel,
    index: Int? = null
) {
    val isEditing = index != null
    val contact = if (isEditing) ContactRepository.Companion.contacts[index!!] else Contact("", "", "", "")

    var name by remember { mutableStateOf(contact.name) }
    var address by remember { mutableStateOf(contact.address) }
    var phone by remember { mutableStateOf(contact.phone) }
    var email by remember { mutableStateOf(contact.email) }

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (isEditing) "Edit Kontak" else "Tambah Kontak", style = MaterialTheme.typography.titleLarge) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Address") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Phone") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (name.isBlank() || address.isBlank() || phone.isBlank() || email.isBlank()) {
                        Toast.makeText(context, "Semua kolom harus diisi", Toast.LENGTH_SHORT).show()
                    } else if (address.trim().split(" ").size < 5) {
                        Toast.makeText(context, "Alamat harus minimal 5 kata", Toast.LENGTH_SHORT).show()
                    } else {
                        val newContact = Contact(name, address, phone, email)
                        if (isEditing) {

                            ContactRepository.Companion.contacts[index!!] = newContact
                        } else {

                            ContactRepository.Companion.contacts.add(newContact)
                        }
                        navController.popBackStack()
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White
                ),
            ) {
                Text(if (isEditing) "Save" else "Add")
            }
        }
    }
}

