package com.proyecto.precticasemana04

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInterfaceState: Bundle?){
        super.onCreate(savedInterfaceState)
        setContent {
            MaterialTheme{
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UserListScreen()
                }
            }
        }
    }
}

@Composable
fun UserListScreen(){
    var users by remember { mutableStateOf<List<User>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        try {
            val response = apiService.getUsers()
            users = response
        } catch (e: Exception){
            e.printStackTrace()
        } finally {
            isLoading = false
        }
    }
    if (isLoading){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(users) { user ->
                    UserCard(user = user)
                }
            }
        }
    }

    @Composable
    fun UserCard(user: User){
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = user.name, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = user.email, style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = "ID: ${user.id}", style = MaterialTheme.typography.labelSmall)
            }
        }
    }