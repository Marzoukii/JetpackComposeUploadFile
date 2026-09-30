package com.example.jetpackcomposeuploadfile.ui.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeuploadfile.R
import com.example.jetpackcomposeuploadfile.ui.theme.JetpackComposeUploadFileTheme

@Preview
@Composable
fun GreetingPreview2() {
    JetpackComposeUploadFileTheme {
        LoginScreen(onLoginSuccess = {})
    }
}

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Box {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.loginbkg),
            contentDescription = "Login Background",
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Image(
                modifier = Modifier
                    .width(120.dp)
                    .height(80.dp),
                painter = painterResource(id = R.drawable.kia),
                contentDescription = "Login Background"
            )
            OutlinedTextField(
                modifier = Modifier
                    .width(250.dp)
                    .height(70.dp),
                value = username,
                onValueChange = { username = it },
                label = { Text(text = "Email") },
                )
            Spacer(modifier = Modifier.padding(5.dp))
            OutlinedTextField(
                modifier = Modifier
                    .width(250.dp)
                    .height(70.dp),
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password") },
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Button(
                onClick = {
                    if (username.isNotEmpty() && password.isNotEmpty()) {
                        onLoginSuccess()
                    }
                }, modifier = Modifier.size(250.dp, 50.dp)
            ) {
                Text(text = "Login")
            }
        }
    }
}
