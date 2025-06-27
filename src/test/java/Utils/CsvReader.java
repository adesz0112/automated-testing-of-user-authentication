package Utils;

import model.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

    public class CsvReader {

        public static List<User> readUsersFromCsv(String filePath) {
            List<User> users = new ArrayList<>();

            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                br.readLine();

                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");

                    User user = new User();
                    user.setName(values[0]);
                    user.setEmail(values[1]);
                    user.setPassword(values[2]);
                    user.setTitle(values[3]);
                    user.setDay(values[4]);
                    user.setMonth(values[5]);
                    user.setYear(values[6]);
                    user.setFirstName(values[7]);
                    user.setLastName(values[8]);
                    user.setCompany(values[9]);
                    user.setAddress1(values[10]);
                    user.setAddress2(values[11]);
                    user.setCountry(values[12]);
                    user.setState(values[13]);
                    user.setCity(values[14]);
                    user.setZipcode(values[15]);
                    user.setMobileNumber(values[16]);

                    users.add(user);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return users;
        }

}
