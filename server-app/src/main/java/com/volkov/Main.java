package com.volkov;

import org.bouncycastle.util.io.pem.PemWriter;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.security.cert.Certificate;

public class Main {

    private static final String[] protocols = new String[]{"TLSv1.2"};
    private static final String[] cipher_suites = new String[]{"TLS_RSA_WITH_AES_128_GCM_SHA256"};

    public static void main(String[] args) throws IOException {

        SSLSocket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            SSLSocketFactory factory =
                    (SSLSocketFactory) SSLSocketFactory.getDefault();
            socket =
                    (SSLSocket) factory.createSocket("yandex.by", 443);

            socket.setEnabledProtocols(protocols);
            socket.setEnabledCipherSuites(cipher_suites);

            socket.startHandshake();

            out = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    socket.getOutputStream())));

            out.println("GET / HTTP/1.0");
            out.println();
            out.flush();

            if (out.checkError())
                System.out.println("SSLSocketClient:  java.io.PrintWriter error");
            Certificate[] certificates = socket.getSession().getPeerCertificates();
            PemWriter pw = new PemWriter(out);

            for (Certificate crt : certificates) {
                System.out.println( crt.toString());
            }
            /* read response */
            in = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null)
                socket.close();
            if (out != null)
                out.close();
            if (in != null)
                in.close();
        }
    }
}