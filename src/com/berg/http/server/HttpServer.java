package com.berg.http.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    private final ExecutorService pool;
    private final int port;
    private boolean stopped;

    public HttpServer(int poolSize, int port) {
        this.pool = Executors.newFixedThreadPool(poolSize);
        this.port = port;
    }

    public void run() {
        try {
            var server = new ServerSocket(port);        // создаем сервер, бронируя порт
            while (!stopped){
                var socket = server.accept();    // .accept принимает соденение от клиента и представлен в виде класса Socket
                System.out.println("Socket accepted");
                pool.submit(()->processSocet(socket));  // обрабатываем соеденение
            }

//            processSocet(socket);                  // обрабатываем соеденение
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void processSocet(Socket socket) {
        try (socket;                                                         // сокет надо закрыть после использования, по-этому помещаем в try
            var inputStream = new DataInputStream(socket.getInputStream());  //считываем информацию из реквеста
            var outputStream = new DataOutputStream(socket.getOutputStream())//что бы вернуть респонс
        ) {
//          Step 1: Handle Request
            System.out.println("Request: " + new String(inputStream.readNBytes(400)));
            Thread.sleep(10000);
//          Step 2: Handle Responce
//          для примера создадитм респонс в виде хтмл странички
            var body = Files.readAllBytes(Path.of("resources", "example.html"));
//          перечисляем все заголовки в строке
            var headers = """
                    HTTP/1.1 200 OK
                    content-type: text/html
                    content-length: %s
                    """.formatted(body.length).getBytes();
            outputStream.write(headers);
            outputStream.write(System.lineSeparator().getBytes());
            outputStream.write(body);
        } catch (IOException | InterruptedException e) {
            // TODO: 19.10.2021 log error message
            e.printStackTrace();
        }
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }
}
