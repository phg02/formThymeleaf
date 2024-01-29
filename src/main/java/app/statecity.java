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

public class statecity implements Handler {
    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/citystate.html";

    // Name of the HTML file in the resources folder
    private static final String TEMPLATE = ("citystate.html");

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        Map<String, Object> model = new HashMap<String, Object>();

        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Integer> yearselection = new ArrayList<Integer>();
        ArrayList<info> infos = jdbc.getallYear();

        for (info time : infos) {
            yearselection.add(time.year);
        }
        model.put("yearselection", yearselection);

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        // COUNTRY SELECTION
        JDBCConnection jdbc1 = new JDBCConnection();
        ArrayList<String> countryselection = new ArrayList<String>();
        ArrayList<info> infos1 = jdbc1.getallCountry();

        for (info time1 : infos1) {
            countryselection.add(time1.country);
        }
        model.put("countryselection", countryselection);

        String country = context.sessionAttribute("country");
        JDBCConnection jdbc99 = new JDBCConnection();
        ArrayList<info> stateselection = new ArrayList<info>();
        stateselection = jdbc99.getallState(country);
        model.put("stateselection", stateselection);
        String state = context.sessionAttribute("state");
        String city = context.sessionAttribute("city");
        String startYear = context.sessionAttribute("startYear");
        String endYear = context.sessionAttribute("endYear");

        System.out.println(country + " " + state + " " + city + " " + startYear + " " + endYear);
        System.out.println(state);

        if (state == null || state.isEmpty()) {
            if (country == null) {
                model.put("title", new String("City/State View"));
            } else if (Integer.parseInt(startYear) > Integer.parseInt(endYear)) {
                model.put("title", new String("Error: Start year must be less than end year"));
            } else {
                model.put("title", new String("Viewing " + country + ", " + city));
            }
            System.out.println("hehe you choose city");
            System.out.println(country + " " + city + " " + startYear + " " + endYear);
            JDBCConnection jbdc3 = new JDBCConnection();
            ArrayList<info> infos3 = jbdc3.readCityInfo(startYear, endYear, country, city);
            model.put("tableInfo", infos3);
            JDBCConnection jbdc4 = new JDBCConnection();
            ArrayList<info> cityData = jbdc4.cityReader(startYear, country, city);
            model.put("startYearInfo", cityData);
            JDBCConnection jbdc6 = new JDBCConnection();
            ArrayList<info> cityData2 = jbdc6.cityReader(endYear, country, city);
            model.put("endYearInfo", cityData2);

            JDBCConnection jbdc8 = new JDBCConnection();
            double percentageAVG = jbdc8.getAVGchangeCity(startYear, endYear, country, city);
            System.out.println(percentageAVG);
            model.put("percentageAVG", percentageAVG);

        } else if (city == null || city.isEmpty()) {
            if (country == null) {
                model.put("title", new String("City/State View"));
            } else if (Integer.parseInt(startYear) > Integer.parseInt(endYear)) {
                model.put("title", new String("Error: Start year must be less than end year"));
            } else {
                model.put("title", new String("Viewing " + country + ", " + state));
            }
            System.out.println("hehe you choose state");
            System.out.println(country + " " + state + " " + startYear + " " + endYear);
            JDBCConnection jdbc2 = new JDBCConnection();
            ArrayList<info> infos2 = jdbc2.readStateInfo(startYear, endYear, country, state);
            model.put("tableInfo", infos2);
            JDBCConnection jbdc5 = new JDBCConnection();
            ArrayList<info> stateData = jbdc5.stateReader(startYear, country, state);
            model.put("startYearInfo", stateData);
            JDBCConnection jbdc7 = new JDBCConnection();
            ArrayList<info> stateData2 = jbdc7.stateReader(endYear, country, state);
            model.put("endYearInfo", stateData2);
            JDBCConnection jbdc8 = new JDBCConnection();
            double percentageAVG = jbdc8.getAVGchangeState(startYear, endYear, country, state);
            if (percentageAVG > 0) {
                model.put("percentageAVG", new String("+" + percentageAVG));
            } else {
                model.put("percentageAVG", percentageAVG);
            }
            JDBCConnection jbdc9 = new JDBCConnection();
            double percentageMin = jbdc9.MinChangeState(startYear, endYear, state, country);
            if (percentageMin > 0) {
                model.put("percentageMin", new String("+" + percentageMin));
            } else {
                model.put("percentageMin", percentageMin);
            }
            JDBCConnection jbdc10 = new JDBCConnection();
            double percentageMax = jbdc10.MaxChangeState(startYear, endYear, state, country);
            if (percentageMax > 0) {
                model.put("percentageMax", new String("+" + percentageMax));
            } else {
                model.put("percentageMax", percentageMax);
            }
        } else {
            System.out.println("Error huhu.");
        }

        context.render(TEMPLATE, model);
    }
}
