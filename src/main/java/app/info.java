package app;

// Class representing all the attributes in the created Database.
public class info {
    // Country name
    public String country;

    // Country Code
    public String country_code;

    // Population
    public long population;

    // Average Temperature
    public double AVGtemp;

    // Minimum Temperature
    public double Mintemp;

    // Maximum Temperature
    public double Maxtemp;

    // City Name
    public String cityName;

    // State Name
    public String stateName;

    // Year
    public int year;

    // Create a default method.
    public info() {

    }

    // Adding attributes into the created method.
    public info(String country, String country_code, long population, double AVGtemp, double Mintemp, double Maxtemp,
            String cityName, String stateName, int year) {
        this.country = country;
        this.country_code = country_code;
        this.population = population;
        this.AVGtemp = AVGtemp;
        this.Mintemp = Mintemp;
        this.Maxtemp = Maxtemp;
        this.cityName = cityName;
        this.stateName = stateName;
        this.year = year;

    }
}
