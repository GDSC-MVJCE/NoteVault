package com.example.notevault

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.notevault.data.NoteModel
import com.example.notevault.ui.theme.CommunityHubAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CommunityHubAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val notes = remember {
        mutableStateListOf<NoteModel>(
            NoteModel("Hello","This is a note")
        )
    }


    var isAddNoteModalVisible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                title = {
                    Text("NoteVault")
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    isAddNoteModalVisible = true
                },
            ) {
                Icon(Icons.Filled.Add, "Floating action button.")
            }
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            items(notes.toList()) { it ->
                Card(
                    modifier = Modifier
                        .padding(all = 16.dp)
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp),
                        Arrangement.SpaceBetween
                    ) {
                        Text(text = it.title, fontSize = 5.em, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = it.content)
                    }
                }
            }
        }

        // Display the AddPostForm when the state variable is true
        if (isAddNoteModalVisible) {
            AddNoteModal(
                onAddNote = { title,content ->
                    // Handle post creation
                            notes.add(
                                NoteModel(title, content)
                            )
                    // You may want to update your ViewModel or perform other actions
                    // based on the data received from the form
                },
                onCancel = {
                    // Handle form cancellation
                    isAddNoteModalVisible = false
                }
            )
        }
    }
}


