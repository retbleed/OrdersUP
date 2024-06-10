package com.retbleed.ordersup.commons.routing

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.retbleed.ordersup.commons.constants.*
import com.retbleed.ordersup.features.orders.presentation.views.*

@Composable
fun AppNavGraph(navHostController: NavHostController){
    NavHost(navController = navHostController, startDestination = ROUTE_VERIFICATION) {

        // Verification
        composable(ROUTE_VERIFICATION){VerificationScreen(navController = navHostController)}

        // Login
        composable(ROUTE_LOGIN){ LoginScreen(navController = navHostController, route = Route.RouteLogin) }

        // Sign Up
        composable(ROUTE_SIGNUP){SignupScreen(navController = navHostController, route = Route.RouteSignUp)}

        // Home
        composable(ROUTE_HOME){ HomeScreen(navController = navHostController, route = Route.RouteHome) }

        // Products
        composable(ROUTE_PRODUCTS){ProductsHomeScreen(navController = navHostController, route = Route.RouteHome)}
        composable(Route.RouteProductDetails.route + "/{id}", arguments = listOf(navArgument("id") { type = NavType.StringType })) { backStackEntry ->
            val orderId = backStackEntry.arguments?.getString("id")
            Route.RouteProductDetails.route.let {
                ProductsDetailsScreen(navHostController, Route.RouteProductDetails ,id = orderId ?: "")
            }
        }
    }
}