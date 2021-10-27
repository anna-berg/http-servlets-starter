package com.berg.http.server;

public class HttpServerRunner {
    public static void main(String[] args) {
        var server = new HttpServer(100, 9000);
        server.run();
    }
}
