package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.util.HashMap;
import java.util.Map;

import org.sqlite.JDBC;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin
 * by writing the raw HTML into a Java String object
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class PageST2A implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/world.html";

    // Name of the HTML file in the resources folder
    private static final String TEMPLATE = "level2.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        Map<String, Object> model = new HashMap<String, Object>();

        // Get year selection
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Integer> yearselection = new ArrayList<Integer>();
        ArrayList<info> infos = jdbc.getallYear();

        for (info time : infos) {
            yearselection.add(time.year);
        }
        model.put("yearselection", yearselection);

        // get year from form
        String startYear = context.sessionAttribute("startyear");
        String endYear = context.sessionAttribute("endyear");

        if (startYear == null || endYear == null || startYear.isEmpty() || endYear.isEmpty()) {
            model.put("title", new String("World View"));
        } else if (Integer.valueOf(startYear) > Integer.valueOf(endYear)) {
            model.put("title", new String("Error: The start year must be smaller than the end year!"));
        } else {
            model.put("title", new String("World View"));
        }

        // get data from database
        JDBCConnection jdbc1 = new JDBCConnection();
        ArrayList<info> result = jdbc1.lvl2query(startYear, endYear);
        model.put("answer", result);

        // }
        // get start year info
        JDBCConnection jdbc2 = new JDBCConnection();
        ArrayList<info> result2 = jdbc2.lv2queryYearReader(startYear);

        model.put("answer2", result2);

        // get end year info
        JDBCConnection jdbc3 = new JDBCConnection();
        ArrayList<info> result3 = jdbc3.lv2queryYearReader(endYear);

        model.put("answer3", result3);

        // get percentage change of AVG temp compare between start year and end year
        JDBCConnection jdbc4 = new JDBCConnection();
        double result4 = jdbc4.getPercentageTempChange(startYear, endYear);
        if (result4 > 0) {
            model.put("answer4", new String("+" + result4));

        } else {
            model.put("answer4", result4);
        }

        // get percentage change of population compare between start year and end year
        if (startYear == null || endYear == null) {
            model.put("answer5", new String("0.0"));
        } else {
            JDBCConnection jdbc5 = new JDBCConnection();
            double result5 = jdbc5.getPopNum(startYear);

            JDBCConnection jdbc6 = new JDBCConnection();
            double result6 = jdbc6.getPopNum(endYear);

            double percentage = ((result6 - result5) / result5) * 100;

            if (percentage > 0) {
                model.put("answer5", new String("+" + percentage));
            } else {
                model.put("answer5", percentage);
            }
        }

        // get ranking for AVG temp
        JDBCConnection jdbc7 = new JDBCConnection();
        ArrayList<info> result7 = jdbc7.rankingYearAsc(startYear, endYear);
        model.put("answer6", result7);

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.render(TEMPLATE, model);
    }

}
