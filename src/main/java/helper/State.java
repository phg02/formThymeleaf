package helper;

import java.io.*;
import java.sql.*;
import java.util.Map;
import com.opencsv.*;
import com.opencsv.exceptions.CsvValidationException;

public class State {
    private static final String CSV_FILE = "database/GlobalYearlyLandTempByState.csv";

    static void StateTable(){
        try (Connection connection = DriverManager.getConnection(database.DATABASE)) {
            String query = "INSERT INTO StateTemperature (year,AVG_temp,Min_temp,Max_temp,state_name,country_code) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            CSVReaderHeaderAware reader = new CSVReaderHeaderAware(new FileReader(CSV_FILE));
            // create Map object
            Map<String, String> line;
            Map<String, String> country_data = Country.Country_data();
            int count = 0;

            while ((line = reader.readMap())!= null) {
                String year = line.get("Year");

                String averageTemperature = line.get("AverageTemperature");
                
                String minimumTemperature = line.get("MinimumTemperature");
                
                String maximumTemperature = line.get("MaximumTemperature");
                
                String state = line.get("State");
                
                //String country = line.get("country_code");
                String CountryCode = country_data.get(line.get("Country"));

                System.out.println("Inserting " + year + " " + averageTemperature + " " + minimumTemperature + " " + maximumTemperature + " " + state + " " + CountryCode + " into State table...");
                ++count;
                statement.setString(1, year);
                if (averageTemperature != null && !averageTemperature.isEmpty()) {
                    statement.setDouble(2, Double.parseDouble(averageTemperature));
                } else {
                    statement.setObject(2, null);
                }
                
                if (minimumTemperature != null && !minimumTemperature.isEmpty()) {
                    statement.setDouble(3, Double.parseDouble(minimumTemperature));
                } else {
                    statement.setObject(3, null);
                }
                
                if (maximumTemperature != null && !maximumTemperature.isEmpty()) {
                    statement.setDouble(4, Double.parseDouble(maximumTemperature));
                } else {
                    statement.setObject(4, null);
                }
                //statement.setString(2, averageTemperature);
                //statement.setString(3, minimumTemperature);
                //statement.setString(4, maximumTemperature);
                if (state != null && !state.isEmpty()) {
                    statement.setString(5, state);
                } else {
                    statement.setObject(5, null);
                    continue;
                }
                statement.setString(6, CountryCode);
                statement.executeUpdate();
                
                
                
            }
            System.out.println("Inserted " + count);
            connection.close();

        }catch (SQLException | IOException | CsvValidationException e) {
                e.printStackTrace();
        }
    }
}