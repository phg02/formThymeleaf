package helper;

public class database {
    // Name of database file (contained in database folder)
    protected static final String DATABASE = "jdbc:sqlite:database/ClimateDB.db";

    public static void main(String[] args) {
        createDatabase.create();
        Country.countryTable();
        population.PopulationTable();
        CountryTemp.CountryTempTable();
        WorldTemperature.WorldTemperatureTable();
        State.StateTable();
        CityTemp.CityTempTable();
        System.out.println("Database created successfully!");
    }
}