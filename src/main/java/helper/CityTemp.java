package helper;

import java.io.*;
import java.sql.*;
import java.util.Map;
import com.opencsv.*;
import com.opencsv.exceptions.CsvValidationException;

public class CityTemp {
    private static final String CSV_FILE = "database/GlobalYearlyLandTempByCity.csv";

    static void CityTempTable() {
        try (Connection connection = DriverManager.getConnection(database.DATABASE)) {
            // Create a statment to make a table
            String query = "INSERT INTO CityTemperature (country_code, city_name, year, AVG_temp, MIN_temp, MAX_temp) VALUES(?, ?, ?, ?, ?, ?)";
            // Name the statement
            PreparedStatement statement = connection.prepareStatement(query);

            // Create a CSV reader object
            CSVReaderHeaderAware reader = new CSVReaderHeaderAware(new FileReader(CSV_FILE));
            // Create a Map object
            Map<String, String> line;
            Map<String, String> country_data = Country.Country_data();
            long count = 0;

            while ((line = reader.readMap()) != null) {
                String CountryCode = country_data.get(line.get("Country"));
                statement.setString(1, CountryCode);
                String CityName = line.get("City");
                statement.setString(2, CityName);
                String year = line.get("Year");
                statement.setInt(3, Integer.parseInt(year));
                // Check null values
                // If a value is a null value, then set that value to be NULL
                // Else, set the value to it.
                String AVGTemp = line.get("AverageTemperature");
                if (AVGTemp != null && !AVGTemp.isEmpty()) {
                    statement.setDouble(4, Double.parseDouble(AVGTemp));
                } else {
                    statement.setObject(4, null);
                }
                String MinTemp = line.get("MinimumTemperature");
                if (MinTemp != null && !MinTemp.isEmpty()) {
                    statement.setDouble(5, Double.parseDouble(MinTemp));
                } else {
                    statement.setObject(5, null);
                }
                String MaxTemp = line.get("MaximumTemperature");
                if (MaxTemp != null && !MaxTemp.isEmpty()) {
                    statement.setDouble(6, Double.parseDouble(MaxTemp));
                } else {
                    statement.setObject(6, null);
                }
                statement.executeUpdate();
                System.out.println("Inserting " + CountryCode + ", " + CityName + ", " + year + ", " + AVGTemp + ", "
                        + MinTemp + ", " + MaxTemp);
                count++;

            }
            System.out.println("Total inserts: " + count);
            connection.close();
        } catch (SQLException | IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}

