package StepDefinitions;

import Utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import model.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Hook {
    private static WebDriver driver;
    public static List<User> registeredUsers = Collections.synchronizedList(new ArrayList<>());
    private static int userIndex = 0;
    public static WebDriver getDriver() {
        return driver;
    }


    public static int getUserIndex() {
        return userIndex;
    }

    public static void incrementUserIndex() {
        userIndex++;
    }



    @Before
    public void setUp() {
        WebDriver driver = new EdgeDriver(); // vagy ChromeDriver, stb.
        driver.manage().window().maximize();
        DriverManager.setDriver(driver);

    }

    public void deleteUser(String email, String password) {
        try {
            URL url = new URL("https://automationexercise.com/api/deleteAccount");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            String params = String.format("email=%s&password=%s",
                    URLEncoder.encode(email, StandardCharsets.UTF_8),
                    URLEncoder.encode(password, StandardCharsets.UTF_8));

            try (OutputStream os = connection.getOutputStream()) {
                os.write(params.getBytes(StandardCharsets.UTF_8));
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("✅ Account deleted successfully.");
            } else {
                String errorResponse = new BufferedReader(
                        new InputStreamReader(connection.getErrorStream(), StandardCharsets.UTF_8))
                        .lines()
                        .collect(Collectors.joining("\n"));
                throw new RuntimeException("❌ Failed to delete account. Code: " + responseCode +
                        "\nResponse: " + errorResponse);
            }

        } catch (IOException e) {
            throw new RuntimeException("⚠️ Error while deleting user: " + e.getMessage(), e);
        }

    }


    @After
    public void tearDown() throws IOException {
        if (Hook.registeredUsers != null && !Hook.registeredUsers.isEmpty()) {
            for (User user : Hook.registeredUsers) {
                deleteUser(user.getEmail(), user.getPassword());
            }
            Hook.registeredUsers.clear();
        }

        DriverManager.quitDriver();

    }
}
