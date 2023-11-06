import java.util.Map;

public class TestAccount {
    public static void main(String[] args) {
        try {
            String username = "your_username";
            String password = "your_password";
            String phoneNumber = "your_phone_number";

            Account account = new Account(username, password, phoneNumber);

            account.auth();

            Map<String, Object> usageData = account.usage();
            System.out.println("Usage Data: " + usageData);

            Map<String, Object> addonsData = account.addOns();
            System.out.println("Addons Data: " + addonsData);

            Map<String, Object> geofencingData = account.geofencing();
            System.out.println("Geofencing Data: " + geofencingData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
