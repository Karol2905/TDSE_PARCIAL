package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class ProxyController{

    private String getService1() {
        return System.getenv("MATH_SERVICE_1") != null
                ? System.getenv("MATH_SERVICE_1")
                : "http://localhost:8080";
    }

    private String getService2() {
        return System.getenv("MATH_SERVICE_2") != null
                ? System.getenv("MATH_SERVICE_2")
                : "http://localhost:8081";
    }

    @GetMapping("/tribonacci")
    public String tribonacci(@RequestParam(value = "n") int n) {

        try {
            return callService(getService1() + "/tribonacci?n=" + n);
        } catch (Exception e) {
            System.out.println("Service 1 caído");
            try {
                return callService(getService2() + "/tribonacci?n=" + n);
            } catch (Exception e2) {
                return "{\"error\": \"Ambos servicios caídos\"}";
            }
        }
    }


    private String callService(String targetUrl) throws Exception {
        URL obj = new URL(targetUrl);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setConnectTimeout(3000);

        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuffer response = new StringBuffer();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } else {
            throw new Exception("Servicio respondió con código: " + responseCode);
        }
    }
}


