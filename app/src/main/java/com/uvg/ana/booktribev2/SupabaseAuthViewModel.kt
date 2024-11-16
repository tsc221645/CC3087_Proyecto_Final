package com.uvg.ana.booktribev2

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uvg.ana.booktribev2.model.UserState
import com.uvg.ana.booktribev2.network.SupabaseClient.client
import com.uvg.ana.booktribev2.utils.SharedPreferenceHelper
import io.github.jan.supabase.gotrue.gotrue
import kotlinx.coroutines.launch
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.ktor.utils.io.concurrent.shared

class SupabaseAuthViewModel : ViewModel() {

    private val _userState = mutableStateOf<UserState>(UserState.Loading)
    val userState: State<UserState> = _userState

    fun signUp(context: Context, userEmail: String, userPassword: String) {
        viewModelScope.launch {
            try {
                println("Signing up with email: $userEmail")
                client.gotrue.signUpWith(Email) {
                    email = userEmail
                    password = userPassword
                }
                saveToken(context)
                println("Signup successful!")
                _userState.value = UserState.Success("Registered user successfully!")
            } catch (e: Exception) {
                println("Signup failed: ${e.message}")
                _userState.value = UserState.Error("Error: ${e.message}")
            }
        }
    }

    private fun saveToken(context: Context) {
        viewModelScope.launch {
            try {
                val accessToken = client.gotrue.currentAccessTokenOrNull() ?: ""
                println("Saving token: $accessToken")
                val sharedPref = SharedPreferenceHelper(context.applicationContext)
                sharedPref.saveStringData("accessToken", accessToken)
            } catch (e: Exception) {
                println("Error saving token: ${e.message}")
            }
        }
    }

    private fun getToken(context: Context): String? {
        return try {
            val sharedPref = SharedPreferenceHelper(context.applicationContext)
            sharedPref.getStringData("accessToken")
        } catch (e: Exception) {
            println("Error retrieving token: ${e.message}")
            null
        }
    }

    fun login(context: Context, userEmail: String, userPassword: String) {
        viewModelScope.launch {
            try {
                println("Attempting login with email: $userEmail")
                client.gotrue.loginWith(Email) {
                    email = userEmail
                    password = userPassword
                }
                println("Login successful!")
                saveToken(context)
                _userState.value = UserState.Success("Logged in successfully!")
            } catch (e: Exception) {
                println("Login failed: ${e.message}")
                _userState.value = UserState.Error("Login failed: ${e.message}")
            }
        }
    }


    fun logout() {
        viewModelScope.launch {
            try {
                client.gotrue.logout()
                println("Logout successful!")
                _userState.value = UserState.Success("Logged out successfully!")
            } catch (e: Exception) {
                println("Logout failed: ${e.message}")
                _userState.value = UserState.Error("Error: ${e.message}")
            }
        }
    }

    fun isUserLoggedIN(context: Context) {
        viewModelScope.launch {
            try {
                val token = getToken(context)
                println("Checking user login status with token: $token")
                if (token.isNullOrEmpty()) {
                    _userState.value = UserState.Error("The user is not logged in!")
                } else {
                    client.gotrue.retrieveUser(token)
                    client.gotrue.refreshCurrentSession()
                    saveToken(context)
                    _userState.value = UserState.Success("User is already logged in!")
                }
            } catch (e: Exception) {
                println("Error checking login status: ${e.message}")
                _userState.value = UserState.Error("Error: ${e.message}")
            }
        }
    }
}
