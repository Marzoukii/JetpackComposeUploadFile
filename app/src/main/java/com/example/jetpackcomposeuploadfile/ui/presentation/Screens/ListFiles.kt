package com.example.jetpackcomposeuploadfile.ui.presentation.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetpackcomposeuploadfile.R
import com.example.jetpackcomposeuploadfile.domain.enums.FileUiState
import com.example.jetpackcomposeuploadfile.ui.presentation.viewmodels.FileViewModel
import com.example.myapp.domain.model.FileItem

@Composable
fun ListFiles(
    viewModel: FileViewModel = hiltViewModel()
) {
    // 1. On observe l'état du ViewModel
    val uiState by viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        // 2. On gère l'affichage selon l'état
        when (val state = uiState) {
            is FileUiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White
                )
            }

            is FileUiState.Error -> {
                Text(
                    text = state.message,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center).padding(16.dp),
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
                    FileContentList(state.files)
                }
            }
        }
    }
}

@Composable
fun FileContentList(files: List<FileItem>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // 3. On utilise items(files) pour boucler sur la vraie liste
        items(files) { file ->
            FileRow(file)
        }
    }
}

@Composable
fun FileRow(file: FileItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icône dynamique selon si c'est un dossier ou un fichier
        Image(
            modifier = Modifier.height(40.dp),
            painter = painterResource(
                id = if (file.isDirectory) R.drawable.ic_folder else R.drawable.ic_folder // Changez ic_folder par ic_file si vous en avez un
            ),
            contentDescription = null,
            colorFilter = ColorFilter.tint(if (file.isDirectory) Color(0xFFFFC107) else Color.White)
        )

        Column(
            modifier = Modifier.padding(start = 12.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = file.name, // Nom réel du fichier
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )
            if (file.isDirectory) {
                Text(
                    text = "Dossier",
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodySmall
                )
            } else {
                Text(
                    text = "${file.size ?: 0} octets", // Taille réelle
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        Text(
            modifier = Modifier
                .padding(start = 12.dp)
                .weight(1f),
            textAlign = TextAlign.Center,
            text = file.date, // Date réelle
            color = Color.Gray,
            style = MaterialTheme.typography.bodySmall
        )

        IconButton(onClick = { /* Actions : Renommer, Supprimer... */ }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "Options",
                tint = Color.White
            )
        }

        IconButton(onClick = { /* Action : Ouvrir */ }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_next),
                contentDescription = "Ouvrir",
                tint = Color.White
            )
        }
    }
}
