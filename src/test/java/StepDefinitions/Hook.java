package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Hook {
    private static WebDriver driver;

    @Before
    public void setUp() {
        if (driver == null) {
            driver = new EdgeDriver();
            driver.manage().window().maximize();
        }
    }

    public void deleteUser(String email, String password) throws IOException {
        String urlStr = "https://automationexercise.com/api/deleteAccount";
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("DELETE");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        String urlParameters = "email=" + URLEncoder.encode(email, "UTF-8") +
                "&password=" + URLEncoder.encode(password, "UTF-8");

        try (DataOutputStream out = new DataOutputStream(conn.getOutputStream())) {
            out.writeBytes(urlParameters);
            out.flush();
        }

        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            throw new RuntimeException("Failed to delete account. Code: " + responseCode + ". Response: " + response);
        }

        System.out.println("Account successfully deleted via API.");
    }

    @After
     public void tearDown() throws IOException {
       /*  if (driver != null) {
             driver.quit();
             driver = null;
         }  */
        deleteUser("testuser67@example.com","Test123!");
        deleteUser("anna.kiss1@example.com","Anna123!");
        deleteUser("bence.nagy1@example.com","Bence321!");
        deleteUser("eva.toth1@example.com","Eva456!");

     }

    public static WebDriver getDriver() {
        return driver;
    }
}
