package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.util.Map;
import java.util.HashMap;

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
public class PageST2B implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/country.html";
    public static final String TEMPLATE = "lv2Country.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a hash map to hold the data we want to pass to our template
        Map<String, Object> model = new HashMap<String, Object>();

        // add year selection
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Integer> yearselection = new ArrayList<Integer>();
        ArrayList<info> infos = jdbc.getallYear();
        for (info time : infos) {
            yearselection.add(time.year);
        }
        model.put("yearselection", yearselection);

        // add country selection
        JDBCConnection jdbc1 = new JDBCConnection();
        ArrayList<String> countryselection = new ArrayList<String>();
        ArrayList<info> infos1 = jdbc1.getallCountry();
        for (info time1 : infos1) {
            countryselection.add(time1.country);
        }
        model.put("countryselection", countryselection);

        // read value from form
        String country = context.sessionAttribute("country");
        String startYear = context.sessionAttribute("startyear1");
        String endYear = context.sessionAttribute("endyear1");
        System.out.println(country);
        System.out.println(startYear);

        // return country name
        if (country == null) {
            model.put("country", new String("Country View"));
        } else if (Integer.parseInt(startYear) > Integer.parseInt(endYear)) {
            model.put("country", new String("Error: Start year must be less than end year"));
        } else {
            model.put("country", new String("Viewing " + country));
        }

        // return info for country
        JDBCConnection jbdc2 = new JDBCConnection();
        ArrayList<info> infos2 = jbdc2.lvl2Bquery(startYear, endYear, country);
        model.put("answer", infos2);

        JDBCConnection jbdc3 = new JDBCConnection();
        ArrayList<info> infos3 = jbdc3.readCountryInfo(startYear, country);
        model.put("answer1", infos3);

        JDBCConnection jbdc4 = new JDBCConnection();
        ArrayList<info> infos4 = jbdc4.readCountryInfo(endYear, country);
        model.put("answer2", infos4);

        JDBCConnection jbdc5 = new JDBCConnection();
        double percentTemp = jbdc5.percentTempChange(startYear, endYear, country);
        if (percentTemp > 0) {
            model.put("percentTemp", new String("+" + percentTemp));
        } else {
            model.put("percentTemp", percentTemp);
        }

        if (country == null) {
            model.put("percentPop", new String("0.0"));
        } else {
            JDBCConnection jbdc6 = new JDBCConnection();
            double results6 = jbdc6.getPopulation(startYear, country);
            JDBCConnection jbdc7 = new JDBCConnection();
            double results7 = jbdc7.getPopulation(endYear, country);
            double percentPop = ((results7 - results6) / results6) * 100;
            if (percentPop > 0) {
                model.put("percentPop", new String("+" + percentPop));
            } else {
                model.put("percentPop", percentPop);
            }
            System.out.println(percentPop);
        }
        JDBCConnection jbdc8 = new JDBCConnection();
        double percentMinTempChange = jbdc8.percentMinTempChange(startYear, endYear, country);
        if (percentMinTempChange > 0) {
            model.put("percentMinTemp", new String("+" + percentMinTempChange));
        } else {
            model.put("percentMinTemp", percentMinTempChange);
        }

        JDBCConnection jbdc9 = new JDBCConnection();
        double percentMaxTempChange = jbdc9.percentMaxTempChange(startYear, endYear, country);
        if (percentMaxTempChange > 0) {
            model.put("percentMaxTemp", new String("+" + percentMaxTempChange));
        } else {
            model.put("percentMaxTemp", percentMaxTempChange);
        }

        JDBCConnection jbdc10 = new JDBCConnection();
        ArrayList<info> infos10 = jbdc10.lvl2AVGTempRankingASC(startYear, endYear, country);
        model.put("answer3", infos10);

        context.render(TEMPLATE, model);

    }
}
