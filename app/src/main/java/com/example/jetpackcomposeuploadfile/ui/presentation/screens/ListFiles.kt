package com.example.jetpackcomposeuploadfile.ui.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetpackcomposeuploadfile.R
import com.example.jetpackcomposeuploadfile.data.response.FileUiState
import com.example.jetpackcomposeuploadfile.ui.presentation.viewmodels.FileViewModel
import com.example.jetpackcomposeuploadfile.domain.model.FileItemModel

@Composable
fun ListFiles(
    viewModel: FileViewModel = hiltViewModel(),
    onFileClick: (FileItemModel) -> Unit
) {
    val uiState by viewModel.uiStateFile.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (val state = uiState) {
            is FileUiState.Loading -> {
                CircularProgressIndicator(color = Color.Black)
            }

            is FileUiState.Error -> {
                Text(
                    text = state.message,
                    color = Color.Red,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )
            }

            is FileUiState.Success -> {
                if (state.files.isEmpty()) {
                    Text(
                        text = "Aucun fichier trouvé",
                        color = Color.Gray,
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    ListFilesContent(state.files, viewModel, onFileClick)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListFilesContent(
    files: List<FileItemModel>,
    viewModel: FileViewModel,
    onFileClick: (FileItemModel) -> Unit
) {
    var showCreateFolderDialog by remember { mutableStateOf(false) }
    var folderName by remember { mutableStateOf("") }

    if (showCreateFolderDialog) {
        AlertDialog(
            onDismissRequest = { showCreateFolderDialog = false },
            title = { Text("Nouveau dossier") },
            text = {
                OutlinedTextField(
                    value = folderName,
                    onValueChange = { folderName = it },
                    label = { Text("Nom du dossier") },
                    singleLine = true
                )
            },
            confirmButton = {
                Button(onClick = {
                    if (folderName.isNotBlank()) {
                        viewModel.createFolder(folderName)
                        folderName = ""
                        showCreateFolderDialog = false
                    }
                }) {
                    Text("Créer")
                }
            },
            dismissButton = {
                TextButton(onClick = { showCreateFolderDialog = false }) {
                    Text("Annuler")
                }
            })
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(
            title = { Text("My Files") },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Cyan)
        )
    }, content = { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(files) { file ->
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onFileClick(file) }
                    .padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        modifier = Modifier.size(40.dp),
                        painter = if (file.isDirectory == true) painterResource(id = R.drawable.ic_folder)
                        else painterResource(id = R.drawable.ic_file),
                        contentDescription = null,
                        colorFilter = if (file.isDirectory == true) ColorFilter.tint(Color.Cyan) else ColorFilter.tint(
                            Color.DarkGray
                        )
                    )
                    Column(
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .weight(1f)
                    ) {
                        Text(
                            file.name ?: "Folder",
                            style = MaterialTheme.typography.titleSmall,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                        Text(
                            file.contentType ?: "Unknown",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray
                        )
                    }

                    Text(
                        file.date ?: "",
                        modifier = Modifier.padding(horizontal = 8.dp),
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodySmall,
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { viewModel.deleteItem(file.id ?: "") }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_delete_24),
                                contentDescription = "supprimer",
                                tint = Color.Gray
                            )
                        }
                        if (file.isDirectory == true) {
                            IconButton(onClick = { viewModel.loadFiles(file.id ?: "") }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_next),
                                    contentDescription = "ouvrir",
                                    tint = Color.Gray
                                )
                            }
                        }
                    }
                }
            }
        }
    }, floatingActionButton = {
        FloatingActionButton(
            onClick = { showCreateFolderDialog = true },
            containerColor = Color.Cyan
        ) {
            Icon(Icons.Default.Add,
                contentDescription = "Add")
        }
    }, floatingActionButtonPosition = FabPosition.End
    )
}
