package app;

import java.util.ArrayList;

import helper.WorldTemperature;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class for Managing the JDBC Connection to a SQLLite Database.
 * Allows SQL queries to be used with the SQLLite Databse in Java.
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class JDBCConnection {

    // Name of database file (contained in database folder)
    public static final String DATABASE = "jdbc:sqlite:database/ClimateDB.db";

    /**
     * This creates a JDBC Object so we can keep talking to the database
     */
    public JDBCConnection() {
        System.out.println("Created JDBC Connection Object");
    }

    public ArrayList<info> getMinYearTemp() {
        // Create the ArrayList of LGA objects to return
        ArrayList<info> min = new ArrayList<info>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT AVG_temp, year FROM WorldTemperature \n" + //
                    "WHERE year = (SELECT MIN(year)FROM WorldTemperature);";

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                info info = new info();
                double avgTemp = results.getDouble("AVG_temp");
                int year = results.getInt("year");

                info.AVGtemp = avgTemp;
                info.year = year;

                // Add the lga object to the array
                min.add(info);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return min;
    }

    public ArrayList<info> getMaxYearTemp() {
        // Create the ArrayList of LGA objects to return
        ArrayList<info> max = new ArrayList<info>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT AVG_temp, year FROM WorldTemperature WHERE year = (SELECT MAX(year)FROM WorldTemperature)";

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                info info = new info();
                double avgTemp = results.getDouble("AVG_temp");
                int year = results.getInt("year");

                info.AVGtemp = avgTemp;
                info.year = year;

                // Add the lga object to the array
                max.add(info);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return max;
    }

    public ArrayList<info> getMinYearPopulation() {
        // Create the ArrayList of LGA objects to return
        ArrayList<info> min = new ArrayList<info>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT pop_num, year FROM Population p JOIN Country c ON p.country_code = c.country_code \r\n"
                    + //
                    "WHERE p.country_code =\"WLD\" AND p.year=(SELECT MIN(year)FROM Population);";

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                info info = new info();
                long population = results.getLong("pop_num");
                int year = results.getInt("year");

                info.population = population;
                info.year = year;

                // Add the lga object to the array
                min.add(info);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return min;
    }

    public ArrayList<info> getMaxYearPopulation() {
        // Create the ArrayList of LGA objects to return
        ArrayList<info> max = new ArrayList<info>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT pop_num, year FROM Population p JOIN Country c ON p.country_code = c.country_code \r\n"
                    + //
                    "WHERE p.country_code =\"WLD\" AND p.year=(SELECT MAX(year)FROM Population);";

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                info info = new info();
                long population = results.getLong("pop_num");
                int year = results.getInt("year");

                info.population = population;
                info.year = year;

                // Add the lga object to the array
                max.add(info);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return max;
    }

    public ArrayList<info> getallYear() {
        // Create the ArrayList of LGA objects to return
        ArrayList<info> yearlist = new ArrayList<info>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DISTINCT year FROM Population ORDER BY year ASC;";

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            while (results.next()) {
                info info = new info();
                // Lookup the columns we need
                info.year = results.getInt("year");
                // Add the lga object to the array
                yearlist.add(info);

            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return yearlist;
    }

    public ArrayList<info> getallCountry() {
        ArrayList<info> countrylist = new ArrayList<info>();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE);

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT country_name FROM Country WHERE country_name <> 'World' ORDER BY country_name;";

            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                info info = new info();
                info.country = results.getString("country_name");
                countrylist.add(info);
            }

            statement.close();
        }

        catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

        return countrylist;
    }

    public ArrayList<info> getallCity() {
        ArrayList<info> citylist = new ArrayList<info>();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE);

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT DISTINCT city_name FROM CityTemperature ORDER BY city_name;";

            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                info info = new info();
                info.cityName = results.getString("city_name");
                citylist.add(info);
            }

            statement.close();
        }

        catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

        return citylist;
    }

    public ArrayList<info> lvl2query(String one, String two) {
        ArrayList<info> lvl2list = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT wt.year ,pop_num ,AVG_temp, MIN_temp, MAX_TEMP FROM WorldTemperature wt JOIN Population p  WHERE wt.year>="
                    + one + " AND wt.year<=" + two + " AND p.country_code= 'WLD' AND p.year = wt.year GROUP BY wt.year";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.population = results.getLong("pop_num");
                info.AVGtemp = results.getDouble("AVG_temp");
                info.Mintemp = results.getDouble("MIN_temp");
                info.Maxtemp = results.getDouble("MAX_TEMP");
                lvl2list.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return lvl2list;

    }

    public ArrayList<info> lv2queryYearReader(String one) {
        ArrayList<info> lvl2list = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT wt.year ,pop_num ,AVG_temp, MIN_temp, MAX_TEMP FROM WorldTemperature wt JOIN Population p  WHERE wt.year= "
                    + one + " AND p.country_code= 'WLD' AND  p.year = wt.year GROUP BY wt.year";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.population = results.getLong("pop_num");
                info.AVGtemp = results.getDouble("AVG_temp");
                info.Mintemp = results.getDouble("MIN_temp");
                info.Maxtemp = results.getDouble("MAX_TEMP");
                lvl2list.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return lvl2list;

    }

    public double getPercentageTempChange(String start, String end) {
        Connection connection = null;
        double percentage = 0;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT (wt2.AVG_temp-wt1.AVG_temp)/wt1.AVG_temp * 100 AS Percent FROM (WorldTemperature wt1 JOIN Country c1 ON c1.country_code = wt1.country_code) "
                    +
                    "JOIN (WorldTemperature wt2 JOIN Country c2 ON c2.country_code = wt2.country_code) " +
                    "WHERE wt1.year = " + start + " AND wt2.year = " + end + " AND c1.country_name = c2.country_name;";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            percentage = results.getDouble("Percent");
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return percentage;
    }

    public ArrayList<info> rankingYearAsc(String startyear, String endyear) {
        ArrayList<info> rankinglist = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT c.country_name, AVG_temp, year FROM WorldTemperature wt JOIN Country c ON c.country_code = wt.country_code WHERE year >= "
                    + startyear + " AND year <= " + endyear + " ORDER BY AVG_temp ASC;";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.AVGtemp = results.getDouble("AVG_temp");
                rankinglist.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return rankinglist;
    }

    public ArrayList<info> rankingYearDesc(String startyear, String endyear) {
        ArrayList<info> rankinglist = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT c.country_name, AVG_temp, year FROM WorldTemperature wt JOIN Country c ON c.country_code = wt.country_code WHERE year >= "
                    + startyear + " AND year <= " + endyear + " ORDER BY AVG_temp DESC;";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.AVGtemp = results.getDouble("AVG_temp");
                rankinglist.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return rankinglist;
    }

    public long getPopNum(String year) {
        Connection connection = null;
        long pop = 0;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT DISTINCT pop_num FROM Population p WHERE p.country_code='WLD' AND p.year= " + year;

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            pop = results.getLong("pop_num");
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return pop;
    }

    public ArrayList<info> lvl2Bquery(String one, String two, String three) {
        ArrayList<info> lvl2Blist = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT CT.year,  c.country_name, CASE WHEN CT.year >= 1960 THEN P.POP_NUM ELSE NULL END AS pop_num, AVG_temp, MIN_temp, MAX_temp FROM CountryTemperature CT JOIN Country C ON CT.country_code = C.country_code LEFT JOIN Population P ON CT.country_code = P.country_code AND CT.year = P.year  WHERE CT.year >= "
                    + one + " AND CT.year <= " + two + " AND c.country_name like '%" + three
                    + "%' GROUP BY CT.year, P.POP_NUM";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.country = results.getString("country_name");
                info.population = results.getLong("pop_num");
                info.AVGtemp = results.getDouble("AVG_temp");
                info.Mintemp = results.getDouble("MIN_temp");
                info.Maxtemp = results.getDouble("MAX_TEMP");
                lvl2Blist.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return lvl2Blist;

    }

    public ArrayList<info> readCountryInfo(String one, String two) {
        ArrayList<info> infos = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT CT.year,  c.country_name, CASE WHEN CT.year >= 1960 THEN P.POP_NUM ELSE NULL END AS pop_num, AVG_temp, MIN_temp, MAX_temp FROM CountryTemperature CT JOIN Country C ON CT.country_code = C.country_code LEFT JOIN Population P ON CT.country_code = P.country_code AND CT.year = P.year  WHERE CT.year = "
                    + one + " AND c.country_name like '%" + two
                    + "%' GROUP BY CT.year, P.POP_NUM";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);

            info info = new info();
            info.year = results.getInt("year");
            info.population = results.getLong("pop_num");
            info.AVGtemp = results.getDouble("AVG_temp");
            info.Mintemp = results.getDouble("MIN_temp");
            info.Maxtemp = results.getDouble("MAX_TEMP");
            infos.add(info);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return infos;

    }

    public double percentPop(String one, String two, String three) {
        Connection connection = null;
        double percentage = 0;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT (p2.pop_num-p1.pop_num)/p1.pop_num * 100 AS Percent FROM (Population p1 JOIN Country c1 ON c1.country_code = p1.country_code) "
                    +
                    "JOIN (Population p2 JOIN Country c2 ON c2.country_code = p2.country_code) " +
                    "WHERE p1.year = " + one + " AND p2.year = " + two + " AND c1.country_name = c2.country_name;";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            percentage = results.getDouble("Percent");
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return percentage;
    }

    public double percentTempChange(String one, String two, String country) {
        Connection connection = null;
        double percentage = 0;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT (wt2.AVG_temp-wt1.AVG_temp)/wt1.AVG_temp *100 AS Percent FROM (CountryTemperature wt1 JOIN Country c1 ON c1.country_code = wt1.country_code) \n"
                    + //
                    "JOIN (CountryTemperature wt2 JOIN Country c2 ON c2.country_code = wt2.country_code) \n" + //
                    "WHERE wt1.year = " + one + " AND wt2.year = " + two
                    + " AND c1.country_name = c2.country_name AND c1.country_name = '" + country + "';";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            percentage = results.getDouble("Percent");
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return percentage;
    }

    public long getPopulation(String year, String country) {
        Connection connection = null;
        long population = 0;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT DISTINCT pop_num FROM Population p JOIN Country c ON p.country_code = c.country_code WHERE c.country_name = '"
                    + country + "' and p.year = " + year;
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            population = results.getLong("pop_num");
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return population;
    }

    public double percentMinTempChange(String start, String end, String country) {
        Connection connection = null;
        double percentage = 0;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT (ct2.MIN_temp - ct1.MIN_temp) / ct1.MIN_temp * 100 AS Percent FROM (CountryTemperature ct1 JOIN Country c1 ON ct1.country_code = c1.country_code) \n"
                    + "JOIN (CountryTemperature ct2 JOIN Country c2 ON ct2.country_code = c2.country_code) \n"
                    + "WHERE ct1.year = " + start + " and ct2.year = " + end
                    + " AND c1.country_name = c2.country_name AND c1.country_name = '" + country + "';";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            percentage = results.getDouble("Percent");
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return percentage;
    }

    public double percentMaxTempChange(String start, String end, String country) {
        Connection connection = null;
        double percentage = 0;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT (ct2.MAX_temp - ct1.MAX_temp) / ct1.MAX_temp * 100 AS Percent FROM (CountryTemperature ct1 JOIN Country c1 ON ct1.country_code = c1.country_code) \n"
                    + "JOIN (CountryTemperature ct2 JOIN Country c2 ON ct2.country_code = c2.country_code) \n"
                    + "WHERE ct1.year = " + start + " and ct2.year = " + end
                    + " AND c1.country_name = c2.country_name AND c1.country_name = '" + country + "';";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            percentage = results.getDouble("Percent");
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return percentage;

    }

    // read state info
    public ArrayList<info> readStateInfo(String one, String two, String three, String foru) {
        ArrayList<info> infos = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT ST.year, ST.state_name, ST.AVG_temp, ST.MIN_temp, ST.MAX_temp FROM COUNTRY C JOIN StateTemperature ST ON (C.COUNTRY_CODE = ST.COUNTRY_CODE) "
                    +
                    "WHERE  ST.year >=" + one + " AND ST.year <=" + two + " AND C.COUNTRY_NAME LIKE '%" + three
                    + "%' AND ST.STATE_NAME LIKE '%" + foru + "%';";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.stateName = results.getString("state_name");
                info.AVGtemp = results.getDouble("AVG_temp");
                info.Mintemp = results.getDouble("MIN_temp");
                info.Maxtemp = results.getDouble("MAX_temp");
                infos.add(info);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return infos;

    }

    // Read city info
    public ArrayList<info> readCityInfo(String one, String two, String three, String four) {
        ArrayList<info> infos = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT CT.year, CT.city_name, CT.AVG_temp, CT.MIN_temp, CT.MAX_temp" +
                    " FROM COUNTRY C JOIN CityTemperature CT ON (C.COUNTRY_CODE = CT.COUNTRY_CODE)" +
                    " WHERE  CT.year >= " + one + " AND CT.year <= " + two + " AND C.COUNTRY_NAME LIKE '%" + three
                    + "%'  AND CT.CITY_NAME LIKE '%" + four + "%';";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.stateName = results.getString("city_name");
                info.AVGtemp = results.getDouble("AVG_temp");
                info.Mintemp = results.getDouble("MIN_temp");
                info.Maxtemp = results.getDouble("MAX_temp");
                infos.add(info);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return infos;

    }

    public ArrayList<info> cityReader(String one, String two, String three) {
        ArrayList<info> cityData = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT CT.year, CT.city_name, CT.AVG_temp, CT.MIN_temp, CT.MAX_temp \n" +
                    " FROM COUNTRY C JOIN CityTemperature CT ON (C.COUNTRY_CODE = CT.COUNTRY_CODE) \n" +
                    " WHERE  CT.year = " + one + " AND C.COUNTRY_NAME LIKE '%" + two + "%' AND CT.CITY_NAME LIKE '%"
                    + three + "%'";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);

            info info = new info();
            info.year = results.getInt("year");
            info.AVGtemp = results.getDouble("AVG_temp");
            info.Mintemp = results.getDouble("MIN_temp");
            info.Maxtemp = results.getDouble("MAX_TEMP");
            cityData.add(info);

            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return cityData;

    }

    public ArrayList<info> stateReader(String one, String two, String three) {
        ArrayList<info> stateData = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT S.year, S.state_name, S.AVG_temp, S.MIN_temp, S.MAX_temp \n" +
                    " FROM COUNTRY C JOIN StateTemperature S ON (C.COUNTRY_CODE = S.COUNTRY_CODE) \n" +
                    " WHERE  S.year = " + one + " AND C.COUNTRY_NAME LIKE '%" + two + "%' AND S.STATE_NAME LIKE '%"
                    + three + "%'";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);

            info info = new info();
            info.year = results.getInt("year");
            info.AVGtemp = results.getDouble("AVG_temp");
            info.Mintemp = results.getDouble("MIN_temp");
            info.Maxtemp = results.getDouble("MAX_TEMP");
            stateData.add(info);
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return stateData;

    }

    public double getAVGchangeState(String start, String end, String country, String state) {
        Connection connection = null;
        double percentage = 0;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "SELECT (st2.AVG_temp-st1.AVG_temp)/st1.AVG_temp * 100 AS Percent FROM (StateTemperature st1 JOIN Country c1 ON c1.country_code = st1.country_code) \n"
                    + "JOIN (StateTemperature st2 JOIN Country c2 ON c2.country_code = st2.country_code) \n"
                    + "WHERE st1.year = " + start + " and st2.year = " + end
                    + " AND c1.country_name = c2.country_name AND c1.country_name = '" + country
                    + "' AND st1.state_name LIKE '%" + state + "%' AND st2.state_name LIKE '%" + state + "%';";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            percentage = results.getDouble("Percent");
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return percentage;
    }

    public double getAVGchangeCity(String start, String end, String country, String city) {
        Connection connection = null;
        double percentage = 0;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "SELECT (st2.AVG_temp-st1.AVG_temp)/st1.AVG_temp * 100 AS Percent FROM (CityTemperature st1 JOIN Country c1 ON c1.country_code = st1.country_code) \n"
                    + "JOIN (CityTemperature st2 JOIN Country c2 ON c2.country_code = st2.country_code) \n"
                    + "WHERE st1.year = " + start + " and st2.year = " + end
                    + " AND c1.country_name = c2.country_name AND c1.country_name = '" + country
                    + "' AND st1.city_name LIKE '%" + city + "%' AND st2.city_name LIKE '%" + city + "%';";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            percentage = results.getDouble("Percent");
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return percentage;
    }

    public double MinChangeState(String start, String end, String state, String country) {
        Connection connection = null;
        double percentage = 0;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "SELECT (st2.Min_temp-st1.Min_temp)/st1.Min_temp * 100 AS Percent FROM (StateTemperature st1 JOIN Country c1 ON c1.country_code = st1.country_code) \n"
                    + "JOIN (StateTemperature st2 JOIN Country c2 ON c2.country_code = st2.country_code) \n"
                    + "WHERE st1.year = " + start + " and st2.year = " + end
                    + " AND c1.country_name = c2.country_name AND c1.country_name = '" + country
                    + "' AND st1.state_name LIKE '%" + state + "%' AND st2.state_name LIKE '%" + state + "%';";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            percentage = results.getDouble("Percent");
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return percentage;
    }

    public double MaxChangeState(String start, String end, String state, String country) {
        Connection connection = null;
        double percentage = 0;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "SELECT (st2.Max_temp-st1.Max_temp)/st1.Max_temp * 100 AS Percent FROM (StateTemperature st1 JOIN Country c1 ON c1.country_code = st1.country_code) \n"
                    + "JOIN (StateTemperature st2 JOIN Country c2 ON c2.country_code = st2.country_code) \n"
                    + "WHERE st1.year = " + start + " and st2.year = " + end
                    + " AND c1.country_name = c2.country_name AND c1.country_name = '" + country
                    + "' AND st1.state_name LIKE '%" + state + "%' AND st2.state_name LIKE '%" + state + "%';";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            percentage = results.getDouble("Percent");
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return percentage;
    }
    public ArrayList<info> lvl2AVGTempRankingTable(String one, String two, String three) {
        ArrayList<info> AVGTempRanking = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT CT.year,  c.country_name, AVG_temp FROM CountryTemperature CT JOIN Country C ON CT.country_code = C.country_code WHERE CT.year >= "
                    + one + " AND CT.year <= " + two + " AND c.country_name like '%" + three + "%' ORDER BY AVG_temp ASC";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.AVGtemp = results.getDouble("AVG_temp");
                AVGTempRanking.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return AVGTempRanking;

    }

}
// TODO: Add your required methods here
