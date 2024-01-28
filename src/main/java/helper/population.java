package helper;

import java.io.*;
import java.sql.*;
import java.util.Map;
import com.opencsv.*;
import com.opencsv.exceptions.CsvValidationException;

public class population {
    private static final String CSV_FILE = "database/Population.csv";

    static void PopulationTable() {
        try (Connection connection = DriverManager.getConnection(database.DATABASE)) {
            // add table name, column name
            String query = "INSERT INTO Population (country_code, year, pop_num) VALUES (?, ?, ?)";
            // name your statement
            PreparedStatement statement = connection.prepareStatement(query);

            // create CSVReader object
            CSVReaderHeaderAware reader = new CSVReaderHeaderAware(new FileReader(CSV_FILE));
            // create Map object
            Map<String, String> line;
            long count = 0;

            while ((line = reader.readMap()) != null) {
                for (int year = 1960; year <= 2013; year++) {
                    String countrycode = line.get("Country Code");
                    statement.setString(1, countrycode);
                    String population = line.get(Integer.toString(year));
                    statement.setInt(2, year);
                    if (population.isEmpty()) {
                        statement.setObject(3, null);
                    } else {
                        statement.setLong(3, Long.parseLong(population));
                    }
                    statement.executeUpdate();
                    ++count;
                    System.out.println(statement);

                }
                System.out.println("Inserted " + count);
            }
            connection.close();

        } catch (SQLException | IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}
