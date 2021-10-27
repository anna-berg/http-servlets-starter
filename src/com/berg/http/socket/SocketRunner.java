package com.berg.http.socket;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketRunner {
    public static void main(String[] args) throws IOException {
        var inetAddress = Inet4Address.getByName("localhost");
        try (var socket = new Socket(inetAddress, 7777);
             var inputStream = new DataInputStream(socket.getInputStream());
             var outputStream = new DataOutputStream(socket.getOutputStream());
             var scanner = new Scanner(System.in)) {
            while (scanner.hasNext()){
                var request = scanner.nextLine();
                outputStream.writeUTF(request);
                System.out.println("Responce from server: " + inputStream.readUTF());
            }
//            var responce = inputStream.readAllBytes();
        }
    }
}
