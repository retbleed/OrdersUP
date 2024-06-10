package com.retbleed.ordersup

import android.bluetooth.BluetoothDevice
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.retbleed.ordersup.commons.adapters.BluetoothManager
import com.retbleed.ordersup.commons.routing.AppNavGraph
import com.retbleed.ordersup.ui.theme.OrdersUPTheme

private val REQUIRED_PERMISSIONS = arrayOf(
    android.Manifest.permission.BLUETOOTH,
    android.Manifest.permission.BLUETOOTH_ADMIN,
    android.Manifest.permission.ACCESS_FINE_LOCATION
)
private const val REQUEST_CODE_PERMISSIONS = 1

private var bluetoothManager: BluetoothManager? = null;

private var device: BluetoothDevice? = null;

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (allPermissionsGranted()) {
            initializeBluetoothManager()
        } else {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
        }

        bluetoothManager?.discoverDevices();

        enableEdgeToEdge()
        lateinit var navController: NavHostController
        setContent {
            OrdersUPTheme {
                Surface() {
                    navController = rememberNavController()
                    AppNavGraph(navController)
                }
            }
        }


    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
    }

    @Deprecated("Sepa la vrg")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                initializeBluetoothManager()
            } else {
                Toast.makeText(this, "Permissions not granted by the user.", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun initializeBluetoothManager() {
        bluetoothManager = BluetoothManager(this)
        // Para descubrir dispositivos
        bluetoothManager!!.discoverDevices()

        // Para transferir datos a un dispositivo específico (asumiendo que ya tienes el dispositivo)
        val devices = // obtener dispositivo de la lista descubierta
            device?.let { bluetoothManager!!.transferData(it, "Hello, Bluetooth!") }
    }
}