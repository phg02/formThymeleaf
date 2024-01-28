package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.sqlite.JDBC;

import io.javalin.http.Context;
import io.javalin.http.Handler;

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
public class PageIndex implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/";
    public static final String TEMPLATE = "homepage.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a data model for the template engine
        Map<String, String> model = new HashMap<String, String>();

        // Temperature in min year
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<info> hihi = jdbc.getMinYearTemp();
        ArrayList<Double> mintemp = new ArrayList<Double>();
        StringBuilder sb = new StringBuilder();
        for (info hoho : hihi) {
            mintemp.add(hoho.AVGtemp);
            sb.append(hoho.AVGtemp).append(",");
        }
        sb.setLength(sb.length() - 1);
        String minTempString = sb.toString();
        model.put("mintemp", minTempString);

        // Temperature in max year
        JDBCConnection jbdc1 = new JDBCConnection();
        ArrayList<info> haha = jbdc1.getMaxYearTemp();
        ArrayList<Double> maxtemp = new ArrayList<Double>();
        StringBuilder sb1 = new StringBuilder();
        for (info huhu : haha) {
            maxtemp.add(huhu.AVGtemp);
            sb1.append(huhu.AVGtemp).append(",");
        }
        sb1.setLength(sb1.length() - 1);
        String maxTempString = sb1.toString();
        model.put("maxtemp", maxTempString);

        // Min year
        JDBCConnection jbdc2 = new JDBCConnection();
        ArrayList<info> hehe = jbdc2.getMinYearTemp();
        ArrayList<Integer> minyear = new ArrayList<Integer>();
        StringBuilder sb2 = new StringBuilder();
        for (info hyhy : hehe) {
            minyear.add(hyhy.year);
            sb2.append(hyhy.year).append(",");
        }
        sb2.setLength(sb2.length() - 1);
        String minYearString = sb2.toString();
        model.put("minyear", minYearString);

        // Max year
        JDBCConnection jbdc3 = new JDBCConnection();
        ArrayList<info> hoho = jbdc3.getMaxYearTemp();
        ArrayList<Integer> maxyear = new ArrayList<Integer>();
        StringBuilder sb3 = new StringBuilder();
        for (info hoho1 : hoho) {
            maxyear.add(hoho1.year);
            sb3.append(hoho1.year).append(",");
        }
        sb3.setLength(sb3.length() - 1);
        String maxYearString = sb3.toString();
        model.put("maxyear", maxYearString);

        // Population in min year
        JDBCConnection jbdc4 = new JDBCConnection();
        ArrayList<info> huhu = jbdc4.getMinYearPopulation();
        ArrayList<Long> minpopulation = new ArrayList<Long>();
        StringBuilder sb4 = new StringBuilder();
        for (info hehe1 : huhu) {
            minpopulation.add(hehe1.population);
            sb4.append(hehe1.population);
        }
        sb4.setLength(sb4.length() - 1);
        String minYearPopulation = sb4.toString();
        String minYearPopulation1 = minYearPopulation.substring(0, 1);
        minYearPopulation1 = minYearPopulation1.concat(".");
        String minYearPopulation2 = minYearPopulation.substring(1, 3);
        minYearPopulation2 = minYearPopulation2.concat("B");
        minYearPopulation = minYearPopulation1.concat(minYearPopulation2);
        model.put("minpop", minYearPopulation);

        // Population in max year
        JDBCConnection jbdc5 = new JDBCConnection();
        ArrayList<info> abc = jbdc5.getMaxYearPopulation();
        ArrayList<Long> maxpopulation = new ArrayList<Long>();
        StringBuilder sb5 = new StringBuilder();
        for (info abc1 : abc) {
            maxpopulation.add(abc1.population);
            sb5.append(abc1.population);
        }
        sb5.setLength(sb5.length() - 1);
        String maxYearPopulation = sb5.toString();
        String maxYearPopulation1 = maxYearPopulation.substring(0, 1);
        maxYearPopulation1 = maxYearPopulation1.concat(".");
        String maxYearPopulation2 = maxYearPopulation.substring(1, 2);
        maxYearPopulation2 = maxYearPopulation2.concat("3B");
        maxYearPopulation = maxYearPopulation1.concat(maxYearPopulation2);
        model.put("maxpop", maxYearPopulation);

        JDBCConnection jbdc6 = new JDBCConnection();
        ArrayList<info> squid = jbdc6.getMinYearPopulation();
        ArrayList<Integer> minyearpop = new ArrayList<Integer>();
        StringBuilder sb6 = new StringBuilder();
        for (info huhi : squid) {
            minyearpop.add(huhi.year);
            sb6.append(huhi.year).append(",");
        }
        sb6.setLength(sb6.length() - 1);
        String minyearofpop = sb6.toString();
        model.put("minyearpop", minyearofpop);

        JDBCConnection jbdc7 = new JDBCConnection();
        ArrayList<info> fish = jbdc7.getMaxYearPopulation();
        ArrayList<Integer> maxyearpop = new ArrayList<Integer>();
        StringBuilder sb7 = new StringBuilder();
        for (info fish9 : fish) {
            maxyearpop.add(fish9.year);
            sb7.append(fish9.year).append(",");
        }
        sb7.setLength(sb7.length() - 1);
        String maxyearofpop = sb7.toString();
        model.put("maxyearpop", maxyearofpop);

        context.render(TEMPLATE, model);

    }
}
