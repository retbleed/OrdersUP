package com.retbleed.ordersup.commons.adapters

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import java.io.IOException
import java.io.OutputStream
import java.util.*

class BluetoothManager(private val context: Context) {

    private val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
    private val discoveredDevices = mutableListOf<BluetoothDevice>()
    private var discoveryReceiver: BroadcastReceiver? = null
    private var socket: BluetoothSocket? = null

    init {
        if (bluetoothAdapter == null) {
            throw IllegalStateException("Device doesn't support Bluetooth")
        }
    }

    fun discoverDevices() {
        print("Discovering devices")
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(context, android.Manifest.permission.BLUETOOTH_ADMIN) != PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(context, "Bluetooth permissions are not granted", Toast.LENGTH_SHORT).show()
            return
        }

        if (bluetoothAdapter?.isDiscovering == true) {
            print("Discovering ?")
            bluetoothAdapter.cancelDiscovery()
        }

        discoveryReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                when (intent?.action) {
                    BluetoothDevice.ACTION_FOUND -> {
                        val device: BluetoothDevice? = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                        device?.let { discoveredDevices.add(it) }
                    }
                    BluetoothAdapter.ACTION_DISCOVERY_FINISHED -> {
                        context?.unregisterReceiver(this)
                        discoveryReceiver = null
                    }
                }
            }
        }

        print(discoveredDevices)

        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)
        context.registerReceiver(discoveryReceiver, filter)
        bluetoothAdapter?.startDiscovery()
    }

    fun transferData(device: BluetoothDevice, message: String) {
        if (socket == null || !socket!!.isConnected) {
            try {
                socket = device.createRfcommSocketToServiceRecord(MY_UUID)
                bluetoothAdapter?.cancelDiscovery()
                socket?.connect()
            } catch (e: IOException) {
                e.printStackTrace()
                try {
                    socket?.close()
                } catch (closeException: IOException) {
                    closeException.printStackTrace()
                }
                return
            }
        }

        try {
            val outputStream: OutputStream = socket!!.outputStream
            outputStream.write(message.toByteArray())
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    companion object {
        // Replace with your app's UUID
        private val MY_UUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
    }
}
