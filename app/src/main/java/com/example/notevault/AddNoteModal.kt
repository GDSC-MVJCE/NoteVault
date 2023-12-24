package com.example.notevault

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.window.Dialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteModal(
    onAddNote: (title: String,content: String) -> Unit,
    onCancel: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var content: String by remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = onCancel // Dismiss the dialog on request
    ) {
        Surface(
            modifier = Modifier
                .padding(2.dp),
            shape = MaterialTheme.shapes.medium,
            shadowElevation = 4.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(18.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    "Add a new post",
                    fontWeight = FontWeight.Bold,
                    fontSize = 5.em // Adjusted font size
                )
                Spacer(modifier = Modifier.height(8.dp).fillMaxHeight()) // Flexible spacing
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") }
                )

                OutlinedTextField(
                    value = content,
                    onValueChange = { content = it },
                    label = { Text("Content") },
                    minLines = 4
                )
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = {
                            if (title.isNotEmpty() && content.isNotEmpty()) {
                                onAddNote(title, content)
                                onCancel()
                            } else {
                                // Handle empty input case
                            }
                        },
                    ) {
                        Text("Add")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = onCancel) {
                        Text("Cancel")
                    }
                }
            }
        }
    }
}