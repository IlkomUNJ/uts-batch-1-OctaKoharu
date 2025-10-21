package com.example.uts_mocom.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
// Import combinedClickable and ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.uts_mocom.ContactRepository
import com.example.uts_mocom.viewmodel.ContactViewModel


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class) // Add ExperimentalFoundationApi
@Composable
fun ListContactScreen(navController: NavController, viewModel: ContactViewModel) {
    var selectedContactIndex by remember { mutableStateOf<Int?>(null) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Dashboard", style = MaterialTheme.typography.titleLarge) },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            )) },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("add") },
                containerColor = MaterialTheme.colorScheme.primary) {
                Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White)
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            itemsIndexed(ContactRepository.Companion.contacts) { index, contact ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .combinedClickable(
                            onClick = {},
                            onLongClick = {
                                selectedContactIndex = index
                            }
                        )
                        .padding(16.dp)
                ) {
                    Text(text = contact.name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Text(text = contact.address, fontSize = 16.sp)
                }
            }
        }
        if (selectedContactIndex != null) {
            val contact = ContactRepository.Companion.contacts[selectedContactIndex!!]
            AlertDialog(
                onDismissRequest = { selectedContactIndex = null },
                title = { Text("Detail Kontak") },
                text = {
                    Column {
                        Text("Nama: ${contact.name}")
                        Text("Alamat: ${contact.address}")
                        Text("Telepon: ${contact.phone}")
                        Text("Email: ${contact.email}")
                    }
                },
                confirmButton = {
                    TextButton(onClick = {
                        navController.navigate("edit/$selectedContactIndex")
                        selectedContactIndex = null
                    }) {
                        Text("Edit Kontak")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { selectedContactIndex = null }) {
                        Text("Tutup")
                    }
                }
            )
        }

    }
}
