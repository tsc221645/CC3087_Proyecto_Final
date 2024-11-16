package com.uvg.ana.booktribev2.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uvg.ana.booktribev2.SupabaseAuthViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.text.style.TextAlign
import com.uvg.ana.booktribev2.model.UserState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit, // Callback for successful registration
    onLoginClick: () -> Unit, // Callback to navigate to the login screen
    viewModel: SupabaseAuthViewModel = viewModel() // ViewModel for Supabase authentication
) {
    val context = LocalContext.current
    var userEmail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val userState by viewModel.userState

    // Regular expression to validate email format
    val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")

    // Password validation
    fun isPasswordValid(password: String): Boolean {
        return password.length >= 6 // Replace with your backend's password rules if stricter
    }

    MaterialTheme(
        colorScheme = darkColorScheme()
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Title
                Text(
                    text = "Register",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 32.dp)
                )

                // Email TextField
                OutlinedTextField(
                    value = userEmail,
                    onValueChange = { userEmail = it },
                    label = { Text("Email") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.Gray,
                        cursorColor = Color.White,
                        // textColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )

                // Password TextField
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.Gray,
                        cursorColor = Color.White,
                        //textColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )



                // Display Error Messages
                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }

                // Register Button
                Button(
                    onClick = {
                        when {
                            !emailRegex.matches(userEmail) -> {
                                errorMessage = "Invalid email format"
                            }
                            !isPasswordValid(password) -> {
                                errorMessage = "Password must be at least 6 characters"
                            }

                            else -> {
                                errorMessage = ""
                                viewModel.signUp(
                                    context = context,
                                    userEmail = userEmail,
                                    userPassword = password
                                )
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text("Register")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Navigate to Login Screen
                Text(
                    text = "Already have an account? Login",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { onLoginClick() }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Handle Success and Error States
                when (userState) {
                    is UserState.Success -> {
                        onRegisterSuccess()
                    }
                    is UserState.Error -> {
                        errorMessage = (userState as UserState.Error).message
                    }
                    else -> {} // Do nothing for other states
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewRegisterScreen() {
    RegisterScreen(
        onRegisterSuccess = {},
        onLoginClick = {}
    )
}
