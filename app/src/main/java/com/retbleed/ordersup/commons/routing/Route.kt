package com.retbleed.ordersup.commons.routing

sealed class Route(val route: String, val title: String){
    data object RouteLogin: Route("login", "")
    data object RouteSignUp: Route("signup", "")
    data object RouteVerification: Route("verification", "")
    data object RouteHome: Route("home","Home")
    data object RouteProductHome: Route("products","Products")
    data object RouteProductDetails: Route("products","Products")
}