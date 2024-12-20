package com.volkov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 8090); // Автоматическое закрытие Socket
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // Автоматическая отправка данных
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.print("Введите сообщение для отправки на сервер: ");
            String message = userInput.readLine();

            // Отправка сообщения на сервер
            out.println(message);
            System.out.println("Сообщение отправлено на сервер.");

        } catch (IOException e) {
            System.out.println("Ошибка при подключении к серверу: " + e.getMessage());
        }
    }
}
