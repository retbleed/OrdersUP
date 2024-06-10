package com.retbleed.ordersup.ui.components.utils

import com.retbleed.ordersup.R
import com.retbleed.ordersup.commons.constants.ROUTE_HOME
import com.retbleed.ordersup.commons.constants.ROUTE_PRODUCTS
import com.retbleed.ordersup.commons.constants.ROUTE_SETTINGS

open class BottomNavItem(val route: String, val icon: Int, val label: String)

sealed class NavItem {
    object Home :
        BottomNavItem(ROUTE_HOME, R.drawable.home, "Home")

    object Products :
        BottomNavItem(ROUTE_PRODUCTS, R.drawable.pricetags, "Products")

    object Settings :
        BottomNavItem(ROUTE_SETTINGS, R.drawable.settings, "Settings")
}