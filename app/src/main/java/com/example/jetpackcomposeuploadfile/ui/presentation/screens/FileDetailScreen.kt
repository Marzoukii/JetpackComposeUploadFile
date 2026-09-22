package com.example.jetpackcomposeuploadfile.ui.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeuploadfile.domain.model.FileItemModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FileDetailScreen(
    file: FileItemModel?,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Détails du fichier") }, navigationIcon = {
                IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Retour") } },
                colors = TopAppBarDefaults.
                topAppBarColors(containerColor = Color.Cyan)) })
    { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            DetailRow(label = "Nom", value = file?.name ?: "Inconnu")
            DetailRow(label = "Type", value = file?.contentType ?: (if (file?.isDirectory == true) "Dossier" else "Fichier"))
            DetailRow(label = "Date", value = file?.date ?: "")
            DetailRow(label = "Taille", value = if (file?.isDirectory == true) "N/A" else "${file?.size ?: 0} ")
            DetailRow(label = "ID", value = file?.id ?: "")
        }
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "$label : ",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.width(100.dp))
        Text(text = value,
            style = MaterialTheme.typography.bodyLarge)
    }
}
