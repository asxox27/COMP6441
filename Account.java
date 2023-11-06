import java.util.Map;

public class Account {
    private String username;
    private String password;
    private String number;
    private String token;

    public Account(String username, String password, String number) {
        this.username = username;
        this.password = password;
        this.number = number;
        this.token = null;
    }

    public void auth() throws Exception {
        if (this.token == null) {
            this.token = Authentication.authenticate(username, password);
        }
    }

    public Map<String, Object> usage() throws Exception {
        // ensure token is valid
        if (this.token == null) {
            auth();
        }

        return API.getUsage(number, this.token);
    }

    public Map<String, Object> addOns() throws Exception {
        if (this.token == null) {
            auth();
        }

        return API.getAddOns(number, this.token);
    }

    public Map<String, Object> geofencing() throws Exception {
        if (this.token == null) {
            auth();
        }

        return API.getGeofencing(this.token);
    }
}
