public class Test {

    public static void main(String[] args) {
        testAuthentication();
        testAPI();
        testAccount();
    }

    public static void testAuthentication() {
        try {
            String accessToken = Authentication.authenticate("testUser", "testPassword");
            System.out.println("Access Token: " + accessToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testAPI() {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testAccount() {
        try {
            Account account = new Account("testUser", "testPassword", "testNumber");
            account.auth();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
