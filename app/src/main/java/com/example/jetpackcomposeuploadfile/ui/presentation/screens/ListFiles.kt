package com.example.jetpackcomposeuploadfile.ui.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetpackcomposeuploadfile.R
import com.example.jetpackcomposeuploadfile.data.response.FileUiState
import com.example.jetpackcomposeuploadfile.ui.presentation.viewmodels.FileViewModel
import com.example.jetpackcomposeuploadfile.ui.theme.JetpackComposeUploadFileTheme
import com.example.myapp.domain.model.FileItem



//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    JetpackComposeUploadFileTheme {
////        ListFiles(
////            files = FakeData.fileItems
////        )
//    }
//}
@Composable
fun ListFiles(
    viewModel: FileViewModel = hiltViewModel()
) {
    // 1. On observe l'état du ViewModel
    val uiState by viewModel.uiStateFile.collectAsState()


        // 2. On gère l'affichage selon l'état
        when (val state = uiState) {
            is FileUiState.Loading -> {
//                CircularProgressIndicator(
//                    modifier = Modifier.align(Alignment.Center),
//                    color = Color.White
//                )
            }

            is FileUiState.Error -> {
//                Text(
//                    text = state.message,
//                    color = Color.Red,
//                    modifier = Modifier.align(Alignment.Center).padding(16.dp),
//                    textAlign = TextAlign.Center
//                )
            }

            is FileUiState.Success -> {
                if (state.files.isEmpty()) {
//                    Text(
//                        text = "Aucun fichier trouvé",
//                        color = Color.Gray,
//                        modifier = Modifier.align(Alignment.Center)
//                    )
                } else {
                    ListFilesContent(state.files, viewModel)
                }
            }
        }
    }


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListFilesContent(files: List<FileItem>, viewModel: FileViewModel) {

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
            }
        )
    }

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar={
            TopAppBar(title = {Text("My Files")},
                colors = TopAppBarDefaults.
                topAppBarColors(Color.Cyan))
        },


        content = {innerPadding->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                contentPadding = PaddingValues(top = 80.dp, start = 16.dp, end = 16.dp)
            ) {
                items(files) { file ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Image(
                            modifier = Modifier.size(40.dp).align(Alignment.CenterVertically),
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
                                file.name.toString(),
                                style = MaterialTheme.typography.titleSmall,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                file.contentType.toString(),
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Gray
                            )
                        }

                        Text(
                            "11/09/2026",
                            modifier = Modifier.padding(12.dp),
                            color = Color.Gray,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                        Row(modifier = Modifier
                            .padding(start = 4.dp)
                            .weight(1f),
                            horizontalArrangement= Arrangement.End) {
                            if (file.isDirectory == true) {
                                IconButton(onClick = {
                                    file.id?.let { viewModel.deleteItem(it) }
                                }) {
                                    Icon(
                                        contentDescription = "supprimer",
                                        painter = painterResource(id = R.drawable.ic_delete_24),
                                        tint = Color.Gray
                                    )
                                }
                                IconButton(onClick = {

                                }) {
                                    Icon(
                                        contentDescription = "ouvrir",
                                        painter = painterResource(id = R.drawable.ic_next),
                                        tint = Color.Gray
                                    )
                                }
                            } else{
                                IconButton(onClick = {
                                    file.id?.let { viewModel.deleteItem(it) }
                                }) {
                                    Icon(
                                        contentDescription = "supprimer",
                                        painter = painterResource(id = R.drawable.ic_delete_24),
                                        tint = Color.Gray
                                    )
                                }
                            }
                        }

                    }


                }

            }

        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                showCreateFolderDialog = true
            },containerColor= Color.Cyan) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
        floatingActionButtonPosition = FabPosition.End
    )




}




