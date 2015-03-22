package com.rstyle.maxmoto1702;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static Logger LOG = LoggerFactory.getLogger(Main.class);

    public static String executeGet(String targetURL, String query, Map<String, String> headers) {
        HttpURLConnection connection = null;
        String charset;
        try {
//            charset = java.nio.charset.StandardCharsets.UTF_8.name();
            connection = (HttpURLConnection) new URL(!query.equals("") ? targetURL + "?" + query : targetURL).openConnection();
//            connection.setRequestProperty("Accept-Charset", charset);
//            connection.setRequestProperty("Cookie", "__gfp_64b=RNCwpxtyjJYmATLwxaPGvvRH1737Nv2mqg3Zpifowz7.F7; vishnu1.cuid=; crypted_id=8D53F8299B6509586824A2EC4B152EB4A5471C6F722104504734B32F0D03E9F8; vishnu1.userid=8D53F8299B6509586824A2EC4B152EB4A5471C6F722104504734B32F0D03E9F8; _xsrf=4fb08c0fda0764acf63531f0773f291f; hhtoken=xHazBUIscOrIFhwlGxaot81VDhgG; hhuid=GfqIVTAwxNvGKVUFZyIqVA--; __utma=1.924320329.1427017501.1427017501.1427017501.1; __utmb=1.4.10.1427017501; __utmc=1; __utmz=1.1427017501.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none)");

            if (headers != null) {
                for (String header : headers.keySet()) {
                    connection.setRequestProperty(header, headers.get(header));
                }
            }

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\n');
            }
            rd.close();
            return response.toString();

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        } finally {


        }
    }

    public static String executePost(String targetURL, String urlParameters) {
        URL url;
        URLConnection connection = null;
        String charset;
        try {
            url = new URL(targetURL);
            charset = java.nio.charset.StandardCharsets.UTF_8.name();
            connection = new URL(targetURL).openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Accept-Charset", charset);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);

            try (OutputStream output = connection.getOutputStream()) {
                output.write(urlParameters.getBytes(charset));
            }

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\n');
            }
            rd.close();
            return response.toString();

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        } finally {


        }
    }

    public static void main(String... args) throws IOException, InterruptedException {
//        LOG.info(executeGet("http://ya.ru/", ""));
//        LOG.info(executeGet("http://hh.ru/", ""));
//        LOG.info(executeGet("https://m.hh.ru/oauth/authorize", "response_type=code&client_id="));
        String clientId = "SRP5CUGB7ICSDHSGD74KAMV84HKLD934B401MBAPHKFCDU7P4UI6LSJBAIVPF5TP";
        String clientSecret = "KOAP32GGM1VP00ICNAHMRSH0FEHDIQAIP3U4AVIFD9920DJN6R5UIRMJS4AF2I1C";
        WebDriver driver = new ChromeDriver();
        String authorizationCode = getAuthorizationCode(clientId, driver);
        driver.quit();
        JSONObject tokenResponse = new JSONObject(executePost("https://m.hh.ru/oauth/token", "grant_type=authorization_code&client_id=" + clientId + "&client_secret=" + clientSecret + "&code=" + authorizationCode));
        String accessToken = tokenResponse.getString("access_token");
        String tokenType = tokenResponse.getString("token_type");
        long expiresIn = tokenResponse.getLong("expires_in");
        String refreshToken = tokenResponse.getString("refresh_token");
        LOG.info("Client ID " + clientId);
        LOG.info("Authorization Code " + authorizationCode);
        LOG.info("Access token " + accessToken);
        LOG.info("Token type " + tokenType);
        LOG.info("Expires in " + expiresIn);
        LOG.info("Refresh token " + refreshToken);

        Map<String, String> headers = new HashMap<>();
        headers.put("User-Agent", "Test/1.0 (test@test.ru)");
        headers.put("Host", "api.hh.ru");
        headers.put("Accept", "*/*");
        headers.put("Authorization", "Bearer " + accessToken);

        String response = executeGet("https://api.hh.ru/me", "", headers);

        LOG.info("Response /me \n" + response);
    }

    private static String getAuthorizationCode(String clientId, WebDriver driver) {

        driver.get("https://m.hh.ru/oauth/authorize?response_type=code&client_id=" + clientId);
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("smv@live.ru");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("17021989MaxMoto");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.equals("https://m.hh.ru/oauth/authorize?response_type=code&client_id=" + clientId)) {
            driver.findElement(By.xpath("//button[contains(@class, 'button_green')]")).click();
        }
        String authorizationCode = currentUrl.substring(currentUrl.indexOf("code=") + "code=".length());
        return authorizationCode;
    }

    private static void createScreenshotAttachment(WebDriver driver) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = "c:/ipcdata/screenshots/failed/" + System.currentTimeMillis() + screenshot.getName();
        FileUtils.copyFile(screenshot, new File(path));
    }

    public int method() {
        int var = 0;
        LOG.debug("" + var);
        return var;
    }
}