package com.retbleed.ordersup.features.orders.presentation.views

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.retbleed.ordersup.commons.routing.Route
import com.retbleed.ordersup.ui.components.AppNavBar

@Composable
fun ProductsHomeScreen (navController: NavController) {
    AppNavBar(navController = navController) {
        Text(text = "Products")
    }
}