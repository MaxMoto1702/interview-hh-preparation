package com.rstyle.maxmoto1702.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by m on 15.03.2015.
 */
public class HttpClient {
    private static Logger LOG = LoggerFactory.getLogger(HttpClient.class);

    public static void main(String... args) {
        try {
            byte buffer[] = new byte[1024 * 1024];
            FileInputStream fileInputStream = new FileInputStream(HttpClient.class.getClassLoader().getResource(args[0]).getFile());
            int readBytes = fileInputStream.read(buffer);
            String header = new String(buffer, 0, readBytes);
            LOG.info("Header:  " + header);
            fileInputStream.close();

            String host = extract(header, "Host:", "\n");
            if (host == null) {
                throw new Exception("Invalid header");
            }
            int port = 80;
            int separateIndex = host.indexOf(":");
            if (!(separateIndex < 0)) {
                port = Integer.parseInt(host.substring(separateIndex + 1));
                host = host.substring(0, separateIndex);
            }
            LOG.info("Host:    " + host);
            LOG.info("Port:    " + port);

            Socket clientSocket = new Socket(host, port);

            OutputStream outputStream = clientSocket.getOutputStream();
            outputStream.write(header.getBytes());

            InputStream inputStream = clientSocket.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(args[1]);

            String response = "";
            readBytes = 1;
            while(readBytes > 0)
            {
                readBytes = inputStream.read(buffer);
                if(readBytes > 0)
//                    response += new String(buffer, 0, readBytes);
                    fileOutputStream.write(buffer, 0, readBytes);
            }
            LOG.info("Response: " + response);
            fileOutputStream.close();
            clientSocket.close();

        } catch (UnknownHostException e) {
            LOG.error("Unknown host", e);
        } catch (SocketException e) {
            LOG.error("Timeout error", e);
        } catch (IOException e) {
            LOG.error("IO error", e);
        } catch (ArrayIndexOutOfBoundsException e) {
            LOG.error("Index error", e);
        } catch (NumberFormatException e) {
            LOG.error("Number parse error", e);
        } catch (Exception e) {
            LOG.error("Other Error", e);
        }
    }

    private static String extract(String string, String start, String end) {
        int s = string.indexOf("\n\n", 0), e;
        if(s < 0) s = string.indexOf("\r\n\r\n", 0);
        if(s > 0) string = string.substring(0, s);
        s = string.indexOf(start, 0)+start.length();
        if(s < start.length()) return null;
        e = string.indexOf(end, s);
        if(e < 0) e = string.length();
        return (string.substring(s, e)).trim();
    }
}
