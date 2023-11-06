import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.net.URL;

public class Authentication {
    private static final String URL = "https://optus.com.au/myoptus";
    private static final String USERNAME = "optus-username";
    private static final String PASSWORD = "alice?_123";
    private static final String CREDENTIAL = USERNAME + ":" + PASSWORD;

    static String authenticate(String username, String password) throws Exception {
        String endpoint = URL + "/auth/token";

        Map<String, String> data = new HashMap<>();
        data.put("scope", "account");
        data.put("username", username);
        data.put("password", password);
        data.put("grant", "register");

        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "application/json");
        headers.put("user-agent", "My Optus 6.7.5 (iOS 13.4)");
        headers.put("authorisation", "basic" + Base64.getEncoder().encodeToString(CREDENTIAL.getBytes()));

        HttpURLConnection connection = (HttpURLConnection) new URL(endpoint).openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            connection.setRequestProperty(entry.getKey(), entry.getValue());
        }

        try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
            // build the request body with authentication data
            StringBuilder requestBody = new StringBuilder();
            for (Map.Entry<String, String> entry : data.entrySet()) {
                if (requestBody.length() > 0) {
                    requestBody.append("&");
                }
                requestBody.append(entry.getKey()).append("=").append(entry.getValue());
            }
            outputStream.writeBytes(requestBody.toString());
        }

        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            try (InputStream responseStream = connection.getInputStream()) {
                String jsonResponse = new BufferedReader(new InputStreamReader(responseStream)).readLine();
                return parseAccessToken(jsonResponse);
            } catch (IOException e) {
                throw new IOException("Error reading response: " + e.getMessage());
            }
        } else {
            throw new Exception("Authentication failed with status code: " + responseCode);
        }
    }

    static String parseAccessToken(String jsonResponse) throws Exception {
        Map<String, String> jsonMap = parseJsonResponse(jsonResponse);

        if (jsonMap.containsKey("access_token")) {
            return jsonMap.get("access_token");
        } else {
            throw new Exception("Access token not found in the JSON response");
        }
    }

    private static Map<String, String> parseJsonResponse(String jsonResponse) throws Exception {
        Map<String, String> jsonMap = new HashMap<>();

        String[] pairs = jsonResponse.split(",");
        for (String pair : pairs) {
            String[] keyValue = pair.split(":");
            if (keyValue.length == 2) {
                String key = keyValue[0].replaceAll("\"", "").trim();
                String value = keyValue[1].replaceAll("\"", "").trim();
                jsonMap.put(key, value);
            }
        }

        return jsonMap;
    }
}
