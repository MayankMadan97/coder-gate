package com.github.codergate.services.utility;

import com.github.codergate.dto.controller.PullRequestRestRequest;
import com.github.codergate.utils.JwtUtils;
import com.github.codergate.utils.RestClient;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class WebHookListenerUtil {
    @Autowired
    RestClient restClient;


    public Boolean rejectPullRequest(String owner, String repositoryName, Integer pullRequestNumber,String installationId){

        String apiUrl = String.format("https://api.github.com/repos/%s/%s/pulls/%s", owner, repositoryName, pullRequestNumber);
        MultiValueMap<String,String> customHeaders = new LinkedMultiValueMap<>();
        customHeaders.add("X-HTTP-Method-Override", "PATCH");
        customHeaders.add("Content-Type", "application/json");
        PullRequestRestRequest pullRequestRestRequest = new PullRequestRestRequest();
        pullRequestRestRequest.setMessage("Sorry, this pull request cannot be merged at this time.");
        pullRequestRestRequest.setState("closed");
        restClient.invokeForPost(apiUrl,pullRequestRestRequest,customHeaders,installationId);

//        try {
//            URL url = new URL(apiUrl);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestProperty("X-HTTP-Method-Override", "PATCH");
//            conn.setRequestMethod("POST");
//            conn.setRequestProperty("Authorization", "token " + token);
//            conn.setRequestProperty("Accept", "application/vnd.github.v3+json");
//            conn.setRequestProperty("try {
////            URL url = new URL(apiUrl);
////            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
////            conn.setRequestProperty("X-HTTP-Method-Override", "PATCH");
////            conn.setRequestMethod("POST");
////            conn.setRequestProperty("Authorization", "token " + token);
////            conn.setRequestProperty("Accept", "application/vnd.github.v3+json");
////            conn.setRequestProperty("Content-Type", "application/json");
////            conn.setDoOutput(true);
////            conn.getOutputStream().write(payload.getBytes("UTF-8"));
////
////            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
////            String line;
////            StringBuilder response = new StringBuilder();
////            while ((line = reader.readLine()) != null) {
////                response.append(line);
////            }
////            reader.close();
////
////            System.out.println(response.toString());
////        } catch (Exception e) {
////            e.printStackTrace();
////        }Content-Type", "application/json");
//            conn.setDoOutput(true);
//            conn.getOutputStream().write(payload.getBytes("UTF-8"));
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            String line;
//            StringBuilder response = new StringBuilder();
//            while ((line = reader.readLine()) != null) {
//                response.append(line);
//            }
//            reader.close();
//
//            System.out.println(response.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return true;
    }
}
