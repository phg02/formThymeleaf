package helper;

import java.io.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import com.opencsv.*;
import com.opencsv.exceptions.CsvValidationException;

public class Country {
    private static final String CSV_FILE = "database/Population.csv";

    static void countryTable() {
        try (Connection connection = DriverManager.getConnection(database.DATABASE)) {
            // add table name, column name
            String query = "INSERT INTO Country (country_name, country_code) VALUES (?, ?)";
            // name your statement
            PreparedStatement statement = connection.prepareStatement(query);

            // create CSVReader object
            CSVReaderHeaderAware reader = new CSVReaderHeaderAware(new FileReader(CSV_FILE));
            // create Map object
            Map<String, String> line;
            int count = 0;

            while ((line = reader.readMap()) != null) {
                String country_name = line.get("Country Name");
                String country_code = line.get("Country Code");
                System.out.println("Inserting " + country_name + " " + country_code + " into Country table...");
                statement.setString(1, country_name);
                // remember to check null value by if statement
                // if (amount != null && !amount.isEmpty()) {
                // preparedStatement.setLong(3, Long.parseLong(amount));
                // } else{
                // preparedStatement.setObject(3, null);
                // }
                statement.setString(2, country_code);
                statement.executeUpdate();
                // insert count++ if you want to count how many rows are inserted
                ++count;
            }
            System.out.println("Inserted " + count);
            connection.close();

        } catch (SQLException | IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> Country_data() {
        Map<String, String> countryData = new HashMap<String, String>();
        try (Connection connection = DriverManager.getConnection(database.DATABASE)) {
            // Create a statement
            Statement statement = connection.createStatement();

            // Execute the query to fetch country data
            String getAllCountry = "SELECT country_name, country_code FROM Country";
            ResultSet resultSet = statement.executeQuery(getAllCountry);

            // Iterate over the result set and populate the map
            while (resultSet.next()) {
                String countryName = resultSet.getString("country_name");
                String countryCode = resultSet.getString("country_code");
                countryData.put(countryName, countryCode);
            }
            // Close the result set, statement, and connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countryData;
    }
}
