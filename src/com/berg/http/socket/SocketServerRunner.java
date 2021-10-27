package com.berg.http.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketServerRunner {
    public static void main(String[] args) throws IOException {
        try (var serverSocket = new ServerSocket(7777);
             var socket = serverSocket.accept();         // возвр. socet (тот клиент кот. подключился к нашему серверу) -> ожидает пока клиент подключится к серверу
             var outputStream = new DataOutputStream(socket.getOutputStream());
             var inputStream = new DataInputStream(socket.getInputStream());
             var scanner = new Scanner(System.in)) {
            var request = inputStream.readUTF();
            while (!"stop".equals(request)){
                System.out.println("Client request: " + request);
                var responce = scanner.nextLine();
                outputStream.writeUTF(responce);
                request = inputStream.readUTF();
            }

        }
    }
}
