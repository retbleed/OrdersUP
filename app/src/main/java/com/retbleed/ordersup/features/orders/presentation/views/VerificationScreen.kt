package com.retbleed.ordersup.features.orders.presentation.views

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.retbleed.ordersup.commons.routing.Route
import com.retbleed.ordersup.ui.components.BottomNavigationBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun VerificationScreen (navController: NavController){
    Scaffold (bottomBar = { BottomNavigationBar(navController = navController) }) {
        Text(text = "Vista de Verificacion.") 
    }
}