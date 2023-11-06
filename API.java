import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class API {
    private static final String URL = "https://optus.com.au/myoptus";

    public static Map<String, Object> getUsage(String number, String accessToken) throws Exception {
        String endpoint = URL + "/api/usages/v2/serviceid/" + number;
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "application/json");
        headers.put("user-agent", "My Optus 6.7.5 (iOS 13.4)");
        headers.put("authorization", "Bearer " + accessToken);

        try {
            URL url = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }

            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                Map<String, Object> usageData = new HashMap<>();
                usageData.put("key", "value");

                return usageData;
            } else {
                throw new Exception("API request failed with status code: " + responseCode);
            }
        } catch (Exception e) {
            throw new Exception("Error while making API request: " + e.getMessage());
        }
    }

    public static Map<String, Object> getAddOns(String number, String token) throws Exception {
        String endpoint = URL + "/api/services/" + number + "/addons";

        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "application/json");
        headers.put("user-agent", "My Optus 6.7.5 (iOS 13.4)");
        headers.put("authorization", "Bearer " + token);

        try {
            URL url = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            for (Map.Entry<String, String> entry : headers.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }

            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                Map<String, Object> addonsData = new HashMap<>();
                addonsData.put("addon1", "value1");
                addonsData.put("addon2", "value2");

                return addonsData;
            } else {
                throw new Exception("API request failed with status code: " + responseCode);
            }
        } catch (Exception e) {
            throw new Exception("Error while making API request: " + e.getMessage());
        }
    }

    public static Map<String, Object> getGeofencing(String token) throws Exception {
        String endpoint = URL + "/api/geofence/fencelist/v2";

        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "application/json");
        headers.put("user-agent", "My Optus 6.7.5 (iOS 13.4)");
        headers.put("authorization", "Bearer " + token);

        try {
            URL url = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            for (Map.Entry<String, String> entry : headers.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }

            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                Map<String, Object> geofencingData = new HashMap<>();
                geofencingData.put("location1", "value1");
                geofencingData.put("location2", "value2");

                return geofencingData;
            } else {
                throw new Exception("API request failed with status code: " + responseCode);
            }
        } catch (Exception e) {
            throw new Exception("Error while making API request: " + e.getMessage());
        }
    }
}

