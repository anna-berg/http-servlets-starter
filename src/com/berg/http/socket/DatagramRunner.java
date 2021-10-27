package com.berg.http.socket;

import java.io.IOException;
import java.net.*;

public class DatagramRunner {
    public static void main(String[] args) throws IOException {
        var inetAddress = InetAddress.getByName("localhost");
        try (var datagramSocket = new DatagramSocket()) {
            var bytes = "Hello from UDP client!".getBytes();
            DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, inetAddress, 7777);
            datagramSocket.send(datagramPacket);
        }
    }
}
