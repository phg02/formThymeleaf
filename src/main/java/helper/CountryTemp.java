package helper;

import java.io.*;
import java.sql.*;
import java.util.Map;
import com.opencsv.*;
import com.opencsv.exceptions.CsvValidationException;

public class CountryTemp {
    private static final String CSV_FILE = "database/GlobalYearlyLandTempByCountry.csv";

    static void CountryTempTable() {
        try (Connection connection = DriverManager.getConnection(database.DATABASE)) {
            String query = "INSERT INTO CountryTemperature (country_code, year, AVG_temp, MIN_temp, MAX_temp) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);

            // create CSVReader object
            CSVReaderHeaderAware reader = new CSVReaderHeaderAware(new FileReader(CSV_FILE));
            // create Map object

            Map<String, String> line;
            Map<String, String> country_data = Country.Country_data();

            int count = 0;

            while ((line = reader.readMap()) != null) {
                String countryCode = country_data.get(line.get("Country"));

                String year = line.get("Year");

                String avgTemp = line.get("AverageTemperature");

                String minTemp = line.get("MinimumTemperature");

                String maxTemp = line.get("MaximumTemperature");

                statement.setString(1, countryCode);
                statement.setString(2, year);
                // remember to check null value by if statement
                if (avgTemp != null && !avgTemp.isEmpty()) {
                    statement.setDouble(3, Double.parseDouble(avgTemp));
                } else {
                    statement.setObject(3, null);
                }
                if (minTemp != null && !minTemp.isEmpty()) {
                    statement.setDouble(4, Double.parseDouble(minTemp));
                } else {
                    statement.setObject(4, null);
                }
                if (maxTemp != null && !maxTemp.isEmpty()) {
                    statement.setDouble(5, Double.parseDouble(maxTemp));
                } else {
                    statement.setObject(5, null);
                }
                System.out.println(
                        "Inserting " + countryCode + ", " + year + ", " + avgTemp + ", " + minTemp + ", " + maxTemp);
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
}
