package com.retbleed.ordersup.features.orders.presentation.views

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.retbleed.ordersup.commons.routing.Route
import com.retbleed.ordersup.ui.components.AppNavBar
import com.retbleed.ordersup.ui.components.ProductCard

@Composable
fun HomeScreen (navController: NavController){
    AppNavBar(navController = navController) {
        Text(text = "Home")
        ProductCard()
    }
}