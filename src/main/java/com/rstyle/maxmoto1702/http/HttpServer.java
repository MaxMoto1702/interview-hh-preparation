package com.rstyle.maxmoto1702.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by m on 15.03.2015.
 */
public class HttpServer extends Thread {
    private static Logger LOG = LoggerFactory.getLogger(HttpServer.class);

    private Socket serverSocket;
    private int number;

    public static void main(String... args) {
        try {
            int i = 0;
            ServerSocket serverSocket = new ServerSocket(17289, 0, InetAddress.getByName("localhost"));
            LOG.info("Server is started");
            while(true)
            {
                new HttpServer(i, serverSocket.accept());
                i++;
            }
        } catch (IOException e) {
            LOG.error("IO error", e);
        } catch (Exception e) {
            LOG.error("Other Error", e);
        }
    }

    public HttpServer(int number, Socket serverSocket)
    {
        this.number = number;
        this.serverSocket = serverSocket;

        setDaemon(true);
        setPriority(NORM_PRIORITY);
        start();
    }

    public void run()
    {
        try
        {
            InputStream inputStream = serverSocket.getInputStream();
            OutputStream outputStream = serverSocket.getOutputStream();

            byte buffer[] = new byte[64 * 1024];
            int readBytes = inputStream.read(buffer);
            String request = new String(buffer, 0, readBytes);
            LOG.info("Request:  " + request);
            String response = "Request number " + number + ": " + request;
            LOG.info("Response: " + response);
            outputStream.write(response.getBytes());

            serverSocket.close();
        } catch(Exception e) {
            LOG.error("Running error: ", e);
        }
    }
}
