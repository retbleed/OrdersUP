package com.retbleed.ordersup.features.orders.presentation.views

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.NavController
import com.retbleed.ordersup.ui.components.AppNavBar

@Composable
fun SettingsScreen (navController: NavController) {

    val bluetoothManager: BluetoothManager? = getSystemService(LocalContext.current, BluetoothManager::class.java)
    val bluetoothAdapter: BluetoothAdapter? = bluetoothManager?.adapter
    if (bluetoothAdapter == null) {
        Log.d("ORDER UP", "NO ESTA JALANDO EL BLUTU")
    }

    val pairedDevices: Set<BluetoothDevice>? = bluetoothAdapter?.bondedDevices
    pairedDevices?.forEach { device ->
        val deviceName = if (ActivityCompat.checkSelfPermission(
                LocalContext.current,
                Manifest.permission.BLUETOOTH_CONNECT
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        } else {

        }
        device.name
        val deviceHardwareAddress = device.address
        Log.d("ORDERS UP", "$deviceName + $deviceHardwareAddress")
    }

    AppNavBar(navController = navController) {
        val requestPermissionLauncher =
            rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    Log.d("ORDER UP", "Permisos concedidos")
                } else {
                    Log.d("ORDER UP", "Permisos no concedidos")
                }
            }
        LaunchedEffect(true) {
            requestPermissionLauncher.launch(Manifest.permission.BLUETOOTH)
        }

        val requestDiscoverableLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                Log.d("Bluetooth", "Device is now discoverable")
            } else {
                Log.d("Bluetooth", "Device is not discoverable")
            }
        }

        Button(onClick = {
            val discoverableIntent: Intent = Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE).apply {
                putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300)
            }
            requestDiscoverableLauncher.launch(discoverableIntent)
        }) {
            Text("Make Discoverable")
        }
    }
}