package src;

import java.util.List;

/**
 * Class for city
 */
public class City {
    private String name;
    private String region;
    private String district;
    private int population;
    private String foundation;

    /**
     * Class constructor
     * @param name name of city
     * @param region region
     * @param district district
     * @param population population
     * @param foundation foundation or first mention
     * @throws IllegalArgumentException exception if invalid argument passed
     */
    public City(String name, String region, String district, int population, String foundation) throws IllegalArgumentException {
        setName(name);
        setRegion(region);
        setDistrict(district);
        setPopulation(population);
        setFoundation(foundation);
    }

    /**
     * Function to convert string parameter list to city
     * @param row list of row parameters
     * @return new city on successful creation
     * @throws IllegalArgumentException exception if the number of parameters in the list is less than 5
     * Exception if a new city cannot be created
     */
    public static City rowToCity(List<String> row) throws IllegalArgumentException {
        String name, region, district, populationStr, foundation;
        if (row.size() < 5)
            throw new IllegalArgumentException("Incorrect entry with  id:" + row.get(0));
        name = row.get(1);
        region = row.get(2);
        district = row.get(3);
        populationStr = row.get(4);
        int population = getPopulationFromStr(populationStr);
        foundation = row.size() < 6 ? null : row.get(5);
        try {
            return new City(name, region, district, population, foundation);
        } catch (IllegalArgumentException e) {
            System.out.println("Error creating city record");
        }
        return null;
    }

    /**
     *
     Function to get the population from a string (converts the string to an integer)
     * @param strPopulation population, as a string
     * @return population, as an integer
     */
    private static int getPopulationFromStr(String strPopulation) {
        int population;
        try {
            population = Integer.parseInt(strPopulation);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Население должно быть целым числом", e);
        }
        return population;
    }

    /**
     * Function to get city name
     * @return city name
     */
    public String getName() {
        return name;
    }

    /**
     * Function to set a city name
     * @param name name to set for the city
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Function to get region
     * @return region of city
     */
    public String getRegion() {
        return region;
    }

    /**
     * Function to set region
     * @param region region to set
     */
    public void setRegion(String region){
        this.region = region;
    }

    /**
     * Function to get district
     * @return district of city
     */
    public String getDistrict() {
        return district;
    }

    /**
     * Function to set district
     * @param district district to set
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * Function to get population of city
     * @return population of city
     */
    public int getPopulation() {
        return population;
    }

    /**
     * Function to set the value of the number of inhabitants of the city
     * @param population population number of inhabitants to be set
     * @throws IllegalArgumentException exception if population is less than or equal to 0
     */
    public void setPopulation(int population) throws IllegalArgumentException {
        if (!(population>0))
            throw new IllegalArgumentException("Incorrect population");
        this.population = population;
    }

    /**
     * Function to get city foundation
     * @return if the given city has no founding record, it returns "нет"
     * if there is a record, then returns it
     */
    public String getFoundation() {
        if (foundation == null) return "нет";
        return foundation;
    }

    /**
     * Function to set foundation
     * @param foundation foundation to set
     */
    public void setFoundation(String foundation) {
        this.foundation = foundation;
    }

    /**
     * Overriding the toString() Method
     * @return string in given city model view
     */
    @Override
    public String toString() {
        return "City{" +
                "name='" + getName() + '\'' +
                ", region='" + getRegion() + '\'' +
                ", district='" + getDistrict() + '\'' +
                ", population=" + getPopulation() +
                ", foundation='" + getFoundation() + '\'' + '}' + '\n';
    }
}