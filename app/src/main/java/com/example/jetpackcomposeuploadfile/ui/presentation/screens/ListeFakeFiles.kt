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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeuploadfile.R
import com.example.jetpackcomposeuploadfile.ui.theme.JetpackComposeUploadFileTheme
import com.example.myapp.domain.model.FileItem

@Preview(showBackground = true)
@Composable
fun GreetingPreviewFake() {
    JetpackComposeUploadFileTheme {
        ListFilesFake(FakeData.fileItems)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListFilesFake(files: List<FileItem>) {
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar={
            TopAppBar(title = {Text("My Files")},
                colors = TopAppBarDefaults.
                topAppBarColors(Color.Cyan))
        },

    content = {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 150.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(files) { file ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
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
                            IconButton(onClick = {}) {
                                Icon(
                                    contentDescription = "Options",
                                    imageVector = Icons.Default.MoreVert,
                                    tint = Color.Gray
                                )
                            }
                            IconButton(onClick = {}) {
                                Icon(
                                    contentDescription = "ouvrir",
                                    painter = painterResource(id = R.drawable.ic_next),
                                    tint = Color.Gray
                                )
                            }
                        } else{
                            IconButton(onClick = {}) {
                                Icon(
                                    contentDescription = "Options",
                                    imageVector = Icons.Default.MoreVert,
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
            FloatingActionButton(onClick = {}) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
        floatingActionButtonPosition = FabPosition.End
    )




    }


