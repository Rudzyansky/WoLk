package ru.ft.wol.ui.listscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.ft.wol.domain.entity.Client
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

class WakeUpViewModel : ViewModel() {

    fun wakeUp(client: Client) {
        viewModelScope.launch(Dispatchers.IO) {
            val ipAndPort = client.address.split(":")
            val ipAddress = InetAddress.getByName(ipAndPort[0])
            val port = ipAndPort[1].toInt()
            val macBytes = client.macAddress.split(":").map { it.toInt(16).toByte() }.toByteArray()
            val datagram = ByteArray(102)

            // Add 6 bytes of 0xFF to the beginning of the datagram
            for (i in 0 until 6) {
                datagram[i] = 0xFF.toByte()
            }

            // Repeat the MAC address 16 times
            for (i in 1..16) {
                System.arraycopy(macBytes, 0, datagram, i * 6, 6)
            }

            // Send the datagram to the specified IP address and port number
            val socket = DatagramSocket()
            val packet = DatagramPacket(datagram, datagram.size, ipAddress, port)
            socket.send(packet)
            socket.close()
        }
    }
}