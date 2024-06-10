package com.retbleed.ordersup.commons.routing

import com.retbleed.ordersup.commons.constants.*

sealed class Route(val route: String, val title: String) {
    data object RouteLogin : Route(ROUTE_LOGIN, "")
    data object RouteSignUp : Route(ROUTE_SIGNUP, "")
    data object RouteVerification : Route(ROUTE_VERIFICATION, "")
    data object RouteHome : Route(ROUTE_HOME, "Home")
    data object RouteProductHome : Route(ROUTE_PRODUCTS, "Products")
    data object RouteProductDetails : Route(ROUTE_PRODUCT_DETAILS, "Products")
}