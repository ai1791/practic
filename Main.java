package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Program main class
 */
public class Main {
    public static final String FILE_PATH = "Cities.csv"; // path to file

    /**
     * Function to get a city entry from a line in a file
     * @param row row file line
     * @return list of parameters read from string
     */
    private static List<String> getCityFromRow(String row) {
        List<String> params = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(row)) {
            rowScanner.useDelimiter(";");
            while (rowScanner.hasNext())
                params.add(rowScanner.next());
        }
        return params;
    }

    /**
     * Function to get records from a file
     * @param path path to file to read records from
     * @return returns a list of lines read from the file. The string is a list of parameters
     */
    private static List<List<String>> getRecordsFromFile(String path) {
        List<List<String>> rows = new ArrayList<>();
        try (Scanner scan = new Scanner(new File(path))) {
            while (scan.hasNextLine())
                rows.add(getCityFromRow(scan.nextLine()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found", e);
        }
        return rows;
    }

    /**
     * Function to sort cities by name, case insensitive
     * @param cities list of cities
     */
    private static void sortingByName(List<City> cities){
        System.out.println("Sort by name: ");
        System.out.println(cities.stream().sorted(new Comparator<City>(){
            public int compare (City c1, City c2){
                return c1.getName().toLowerCase().compareTo(c2.getName().toLowerCase());
            }
        }).toList());
    }

    /**
     * Sort function by district and name, case sensitive
     * @param cities list of cities
     */
    private static void sortingByDistrictAndName(List<City> cities){
        System.out.println("Sort by district and name: ");
        System.out.println(cities.stream().sorted(Comparator.comparing(City::getDistrict).thenComparing(City::getName)).toList());
    }

    /**
     * Function to find the city with the largest population in the array of cities
     * Displays the city index and the number of inhabitants
     * @param cities list of cities
     */
    private static void showMaxCityPopulation(List<City> cities) {
        City[] citiesArray = cities.toArray(new City[cities.size()]);
        int max_population = -1, index = 0, length = citiesArray.length;
        for (int i = 0; i < length; i++) {
            if (max_population < citiesArray[i].getPopulation()) {
                index = i;
                max_population = citiesArray[i].getPopulation();
            }
        }
        System.out.println("Max population: ");
        System.out.println("[" + index + "] = " + citiesArray[index].getPopulation());
    }

    /**
     * Function to count the number of cities in a region
     * @param cities list of cities
     */
    private static void countCitiesInRegion(List<City> cities){
        Map<String, Long> result = cities.stream().collect(Collectors.groupingBy(City::getRegion, Collectors.counting()));
        result.forEach((r, c) -> System.out.println(r + " - " + c));
    }

    /**
     * Main program method
     * @param args arguments for method
     */
    public static void main(String[] args) {
        List<List<String>> rows;
        try {
            rows = getRecordsFromFile(FILE_PATH);
        } catch (RuntimeException e) {
            System.out.println("Error reading records from file. ");
            return;
        }
        List<City> cities = rows.stream().map(City::rowToCity).toList();
        displays all records to console
        System.out.println(cities);

        sorting by name
        sortingByName(cities);

        sorting by district and name
        sortingByDistrictAndName(cities);

        show max city population and index of city with max population
        showMaxCityPopulation(cities);

        show list of rows "region - count of cities in this region"
        countCitiesInRegion(cities);
    }
}
