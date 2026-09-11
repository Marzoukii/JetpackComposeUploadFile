package com.example.jetpackcomposeuploadfile.ui.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.jetpackcomposeuploadfile.ui.theme.JetpackComposeUploadFileTheme
import com.example.myapp.domain.model.FileItem


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackComposeUploadFileTheme {
        FileContentList(
            files = FakeData.fileItems
        )
    }
}
//@Composable
//fun ListFiles(
//    viewModel: FileViewModel = hiltViewModel()
//) {
//    // 1. On observe l'état du ViewModel
//    val uiState by viewModel.uiStateFile.collectAsState()
//
//    Box(modifier = Modifier.fillMaxSize()) {
//        // 2. On gère l'affichage selon l'état
//        when (val state = uiState) {
//            is FileUiState.Loading -> {
//                CircularProgressIndicator(
//                    modifier = Modifier.align(Alignment.Center),
//                    color = Color.White
//                )
//            }
//
//            is FileUiState.Error -> {
//                Text(
//                    text = state.message,
//                    color = Color.Red,
//                    modifier = Modifier.align(Alignment.Center).padding(16.dp),
//                    textAlign = TextAlign.Center
//                )
//            }
//
//            is FileUiState.Success -> {
//                if (state.files.isEmpty()) {
//                    Text(
//                        text = "Aucun fichier trouvé",
//                        color = Color.Gray,
//                        modifier = Modifier.align(Alignment.Center)
//                    )
//                } else {
//                    FileContentList(FakeData.fileItems)
//                }
//            }
//        }
//    }
//}

@Composable
fun FileContentList(files: List<FileItem>) {
//    Box(
//        modifier = Modifier.fillMaxSize()
//    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(files) { file ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
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
                        modifier = Modifier
                            .padding(start = 25.dp)
                            .weight(1f),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = file.name ?: "",
                            color = Color.Black,
                            style = MaterialTheme.typography.titleMedium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = if (file.isDirectory == true) "Dossier" else file.size.toString(),
                            color = Color.Gray,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Text(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        text = file.date ?: "",
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.End
                    )

                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Options",
                            tint = Color.Gray
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_next),
                            contentDescription = "Ouvrir",
                            tint = Color.Gray
                        )
                    }

                }
            }
        }

//        FloatingActionButton(
//            onClick = { },
//            modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp)
//        ) {
//            Icon(
//                imageVector = Icons.Default.Add,
//                contentDescription = "add folder"
//            )
//        }

}


