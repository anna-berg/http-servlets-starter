package com.berg.http.client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UrlExample {
    public static void main(String[] args) throws IOException {
        var url = new URL("file:/home/anuta/JavaProjects/http-servlets-starter/src/com/berg/http/socket/DatagramRunner.java");
        var urlConnection = url.openConnection();
        System.out.println(new String(urlConnection.getInputStream().readAllBytes()));
    }

    private static void checkGoogle() throws IOException {
        var url = new URL("https://google.com");
        var urlConnection = url.openConnection();
        System.out.println(urlConnection);
        urlConnection.setDoOutput(true);
        try (var outputStream = urlConnection.getOutputStream()) {
        }
    }
}
