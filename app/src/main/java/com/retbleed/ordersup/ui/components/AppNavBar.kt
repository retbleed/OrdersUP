package com.retbleed.ordersup.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppNavBar (navController: NavController, content: @Composable () -> Unit,) {
    Scaffold(bottomBar = { BottomNavigationBar(navController = navController) }) {
        Column (modifier = Modifier.padding(horizontal = 18.dp, vertical = 30.dp)) {
            content()
        }
    }
}