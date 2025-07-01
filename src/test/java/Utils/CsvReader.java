package Utils;

import model.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvReader {

    public static List<User> readUsersFromCsv(String filePath) {
        List<User> users = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();

            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                String[] values = line.split(",");

                if (values.length < 17) {
                    System.out.println("⚠️ Skipping line " + lineNumber + ": not enough values");
                    continue;
                }

                boolean hasEmpty = Arrays.stream(values)
                        .map(String::trim)
                        .anyMatch(String::isEmpty);

                if (hasEmpty) {
                    System.out.println("⚠️ Skipping line " + lineNumber + ": contains empty fields");
                    continue;
                }

                User user = new User();
                user.setName(values[0].trim());
                user.setEmail(values[1].trim());
                user.setPassword(values[2].trim());
                user.setTitle(values[3].trim());
                user.setDay(values[4].trim());
                user.setMonth(values[5].trim());
                user.setYear(values[6].trim());
                user.setFirstName(values[7].trim());
                user.setLastName(values[8].trim());
                user.setCompany(values[9].trim());
                user.setAddress1(values[10].trim());
                user.setAddress2(values[11].trim());
                user.setCountry(values[12].trim());
                user.setState(values[13].trim());
                user.setCity(values[14].trim());
                user.setZipcode(values[15].trim());
                user.setMobileNumber(values[16].trim());

                users.add(user);
            }
        } catch (Exception e) {
            System.err.println("❌ Error reading users from CSV: " + e.getMessage());
            e.printStackTrace();
        }

        return users;
    }

}
