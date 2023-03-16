package com.github.codergate.services.utility;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class WebHookListenerUtil {
    public Boolean rejectPullRequest(String owner, String repositoryName, Integer pullRequestNumber,String token){

        String apiUrl = String.format("https://api.github.com/repos/%s/%s/pulls/%s", owner, repositoryName, pullRequestNumber);

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("X-HTTP-Method-Override", "PATCH");
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "token " + token);
            conn.setRequestProperty("Accept", "application/vnd.github.v3+json");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String payload = "{\"state\":\"closed\",\"message\":\"Sorry, this pull request cannot be merged at this time.\"}";
            conn.getOutputStream().write(payload.getBytes("UTF-8"));

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
