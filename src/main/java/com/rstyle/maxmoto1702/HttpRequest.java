package com.rstyle.maxmoto1702;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

/**
 * Created by m on 22.03.2015.
 */
public class HttpRequest {
    public static void main(String... args) throws IOException {
//        GET /me HTTP/1.1
//        Host: api.hh.ru
//        Accept: */*
//        User-Agent: Test/1.0 (test@test.ru)
//        Authorization: Bearer LES9KE96GA7AE4RAE96CC2NC1PR7K6HG93835HKQQIPO6417MVCA65NM6UG9KMQ0

        HttpURLConnection connection = (HttpURLConnection) new URL("https://api.hh.ru/me").openConnection();
        connection.setRequestProperty("Host", "api.hh.ru");
        connection.setRequestProperty("Accept", "*/*");
        connection.setRequestProperty("User-Agent", "Test/1.0 (test@test.ru)");
        connection.setRequestProperty("Authorization", "Bearer N5KGR3F2LK6IG7FJAF256DTFERJRVVADBADK2BSH98TKPDJ3NQG46Q19E0GHDUVE");

        InputStream is = connection.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        String line;
        StringBuffer response = new StringBuffer();
        while ((line = rd.readLine()) != null) {
            response.append(line);
            response.append('\n');
        }
        rd.close();

        System.out.println("r1 " + response);

//        Host: api.hh.ru
//        Accept: */*
//        User-Agent: Test/1.0 (test@test.ru)
//        Authorization: Bearer N5KGR3F2LK6IG7FJAF256DTFERJRVVADBADK2BSH98TKPDJ3NQG46Q19E0GHDUVE
//        Content-Type: application/x-www-form-urlencoded
//        Content-Length: 71

        String parameters = "last_name=Иванов&first_name=Иван&middle_name=Иванович-" + (char) new Random().nextInt() + new Random().nextInt();

        connection = (HttpURLConnection) new URL("https://api.hh.ru/me").openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Host", "api.hh.ru");
        connection.setRequestProperty("Accept", "*/*");
        connection.setRequestProperty("User-Agent", "Test/1.0 (test@test.ru)");
        connection.setRequestProperty("Authorization", "Bearer N5KGR3F2LK6IG7FJAF256DTFERJRVVADBADK2BSH98TKPDJ3NQG46Q19E0GHDUVE");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Content-Length", "" + parameters.getBytes("UTF-8").length);
        connection.setDoOutput(true);

        try (OutputStream output = connection.getOutputStream()) {
            output.write(parameters.getBytes("UTF-8"));
        }

        is = connection.getInputStream();
        rd = new BufferedReader(new InputStreamReader(is));
        response = new StringBuffer();
        while ((line = rd.readLine()) != null) {
            response.append(line);
            response.append('\n');
        }
        rd.close();

        System.out.println("r2 " + response);

//        GET /me HTTP/1.1
//        Host: api.hh.ru
//        Accept: */*
//        User-Agent: Test/1.0 (test@test.ru)
//        Authorization: Bearer LES9KE96GA7AE4RAE96CC2NC1PR7K6HG93835HKQQIPO6417MVCA65NM6UG9KMQ0

        connection = (HttpURLConnection) new URL("https://api.hh.ru/me").openConnection();
        connection.setRequestProperty("Host", "api.hh.ru");
        connection.setRequestProperty("Accept", "*/*");
        connection.setRequestProperty("User-Agent", "Test/1.0 (test@test.ru)");
        connection.setRequestProperty("Authorization", "Bearer N5KGR3F2LK6IG7FJAF256DTFERJRVVADBADK2BSH98TKPDJ3NQG46Q19E0GHDUVE");

        is = connection.getInputStream();
        rd = new BufferedReader(new InputStreamReader(is));
        response = new StringBuffer();
        while ((line = rd.readLine()) != null) {
            response.append(line);
            response.append('\n');
        }
        rd.close();

        System.out.println("r3 " + response);
//        Host: api.hh.ru
//        Accept: */*
//        User-Agent: Test/1.0 (test@test.ru)
//        Authorization: Bearer N5KGR3F2LK6IG7FJAF256DTFERJRVVADBADK2BSH98TKPDJ3NQG46Q19E0GHDUVE
//        Content-Type: application/x-www-form-urlencoded
//        Content-Length: 71

        parameters = "is_in_search=true" + (char) new Random().nextInt() + new Random().nextInt();

        connection = (HttpURLConnection) new URL("https://api.hh.ru/me").openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Host", "api.hh.ru");
        connection.setRequestProperty("Accept", "*/*");
        connection.setRequestProperty("User-Agent", "Test/1.0 (test@test.ru)");
        connection.setRequestProperty("Authorization", "Bearer N5KGR3F2LK6IG7FJAF256DTFERJRVVADBADK2BSH98TKPDJ3NQG46Q19E0GHDUVE");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Content-Length", "" + parameters.getBytes("UTF-8").length);
        connection.setDoOutput(true);

        try (OutputStream output = connection.getOutputStream()) {
            output.write(parameters.getBytes("UTF-8"));
        }

        is = connection.getInputStream();
        rd = new BufferedReader(new InputStreamReader(is));
        response = new StringBuffer();
        while ((line = rd.readLine()) != null) {
            response.append(line);
            response.append('\n');
        }
        rd.close();

        System.out.println("r4 " + response);

    }
}
