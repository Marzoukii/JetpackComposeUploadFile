package com.example.jetpackcomposeuploadfile.ui.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeuploadfile.R
import com.example.jetpackcomposeuploadfile.domain.model.FileItemModel
import com.example.jetpackcomposeuploadfile.ui.theme.JetpackComposeUploadFileTheme


@Preview(showBackground = true)
@Composable
fun GreetingPreviewFake() {
    JetpackComposeUploadFileTheme {
        ListFilesFake(FakeData.fileItems, onFileClick = {})
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListFilesFake(
    files: List<FileItemModel>,
    onFileClick: (FileItemModel) -> Unit
) {
    var showCreateFolderDialog by remember { mutableStateOf(false) }

    if (showCreateFolderDialog) {
        CreateFolderDialog(
            onDismiss = { showCreateFolderDialog = false },
            onCreate = { /* Logique de création */ showCreateFolderDialog = false }
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("My Files") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Cyan)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showCreateFolderDialog = true },
                containerColor = Color.Cyan
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(files) { file ->
                FileItemRow(file = file, onFileClick = onFileClick)
            }
        }
    }
}

@Composable
fun FileItemRow(file: FileItemModel, onFileClick: (FileItemModel) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onFileClick(file) }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(40.dp),
            painter = painterResource(
                id = if (file.isDirectory == true) R.drawable.ic_folder else R.drawable.ic_file
            ),
            contentDescription = null,
            colorFilter = ColorFilter.tint(if (file.isDirectory == true) Color.Cyan else Color.DarkGray)
        )

        Column(
            modifier = Modifier.padding(start = 12.dp).weight(1f)
        ) {
            Text(text = file.name ?: "", style = MaterialTheme.typography.titleSmall, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(text = file.contentType ?: "Dossier", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        }

        Text(
            text = file.date ?: "",
            modifier = Modifier.width(85.dp),
            color = Color.Gray,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center
        )

        IconButton(onClick = { /* Menu */ }) {
            Icon(Icons.Default.MoreVert, null, tint = Color.Gray)
        }
    }
}

@Composable
fun CreateFolderDialog(onDismiss: () -> Unit, onCreate: (String) -> Unit) {
    var name by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Nouveau dossier") },
        text = { OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Nom") }) },
        confirmButton = { Button(onClick = { onCreate(name) }) { Text("Créer") } },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Annuler") } }
    )
}
