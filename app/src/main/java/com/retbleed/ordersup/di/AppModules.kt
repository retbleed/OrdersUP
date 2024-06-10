package com.retbleed.ordersup.di

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.net.NetworkInterface
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModules {
    @Provides
    @Singleton
    fun provideDeviceMacAddress(@ApplicationContext context: Context): String {
        var macAddress = ""
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
                if (networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true) {
                    val networkInterface = NetworkInterface.getByInetAddress(network.socketFactory.createSocket().inetAddress)
                    macAddress = networkInterface?.hardwareAddress?.joinToString(separator = ":") { byte -> "%02X".format(byte) } ?: ""
                    Log.d("MAC Address", macAddress)
                }
            }
        })
        return macAddress
    }
}