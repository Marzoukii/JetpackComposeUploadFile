package com.example.jetpackcomposeuploadfile.ui.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeuploadfile.ui.theme.JetpackComposeUploadFileTheme

@Preview()
@Composable
fun GreetingPreview2() {
    JetpackComposeUploadFileTheme {
        LoginScreen()
    }
}

@Composable
fun LoginScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        OutlinedTextField(
            modifier = Modifier.width(250.dp).height(70.dp),
            value = "",
            onValueChange = {},
            label = { Text(text = "Email") },
        )
        Spacer(modifier = Modifier.padding(5.dp))
        OutlinedTextField(
            modifier = Modifier.width(250.dp).height(70.dp),
            value = "",
            onValueChange = {},
            label = { Text(text = "Password") },
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Button(onClick = {}, modifier = Modifier.size(250.dp, 50.dp)) {
            Text(text = "Login")
        }
        }
    }
