package helper;

import java.io.*;
import java.sql.*;
import java.util.Map;
import com.opencsv.*;
import com.opencsv.exceptions.CsvValidationException;

public class WorldTemperature {
    private static final String CSV_FILE = "database/GlobalYearlyTemp.csv";

    static void WorldTemperatureTable() {
        try (Connection connection = DriverManager.getConnection(database.DATABASE)) {
            // add table name, column name
            String query = "INSERT INTO WorldTemperature (year, country_code, AVG_temp, MIN_temp, MAX_temp, L_O_AVG_temp, L_O_MIN_temp, L_O_MAX_temp) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            // name your statement
            PreparedStatement statement = connection.prepareStatement(query);

            // create CSVReader object
            CSVReaderHeaderAware reader = new CSVReaderHeaderAware(new FileReader(CSV_FILE));
            // Create a Map object
            Map<String, String> line;

            long count = 0;
            System.out.println("Inserting data into WorldTemperature table...");
            while ((line = reader.readMap()) != null) {
                String YEAR = line.get("Year");
                String CountryCode = "WLD";
                String AvgTemp = line.get("AverageTemperature");
                String MinTemp = line.get("MinimumTemperature");
                String MaxTemp = line.get("MaximumTemperature");
                String LOAvgTemp = line.get("LandOceanAverageTemperature");
                String LOMinTemp = line.get("LandOceanMinimumTemperature");
                String LOMaxTemp = line.get("LandOceanMaximumTemperature");
                // Insert the data into the object
                statement.setInt(1, Integer.parseInt(YEAR));
                statement.setString(2, CountryCode);
                statement.setDouble(3, Double.parseDouble(AvgTemp));
                statement.setDouble(4, Double.parseDouble(MinTemp));
                statement.setDouble(5, Double.parseDouble(MaxTemp));
                // Check if a data is a null value
                // If it is a null value, then set it to NULL
                // If not, then set the value to it.
                if (LOAvgTemp != null && !LOAvgTemp.isEmpty()) {
                    statement.setDouble(6, Double.parseDouble(LOAvgTemp));
                } else {
                    statement.setObject(6, null);
                }
                if (LOMinTemp != null && !LOMinTemp.isEmpty()) {
                    statement.setDouble(7, Double.parseDouble(LOMinTemp));
                } else {
                    statement.setObject(7, null);
                }
                if (LOMaxTemp != null && !LOMaxTemp.isEmpty()) {
                    statement.setDouble(8, Double.parseDouble(LOMaxTemp));
                } else {
                    statement.setObject(8, null);
                }
                System.out.println("Inserting " + YEAR + ", " + CountryCode + ", " + AvgTemp + ", " + MinTemp + ", "
                        + MaxTemp + ", " + LOAvgTemp + ", " + LOMinTemp + ", " + LOMaxTemp);
                statement.executeUpdate();
                count++;
            }
            System.out.println("Total insert: " + count);
            connection.close();
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
