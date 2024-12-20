package com.volkov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8090)) { // Автоматическое закрытие ServerSocket
            System.out.println("Сервер запущен, ожидаем подключение клиента...");

            try (Socket socket = serverSocket.accept(); // Автоматическое закрытие Socket
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                System.out.println("Подключение от: " + socket.getInetAddress());

                // Чтение сообщения от клиента
                String message = in.readLine();
                System.out.println("Сообщение от клиента: " + message);

            } catch (IOException e) {
                System.out.println("Ошибка при чтении данных: " + e.getMessage());
            }

        } catch (IOException e) {
            System.out.println("Ошибка при запуске сервера: " + e.getMessage());
        }
    }
}
