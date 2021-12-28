package avaj;

import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;

import avaj.aircrafts.Aircraft;
import avaj.aircrafts.Flyable;
import avaj.utils.Parser;
import avaj.weather.Tower;
import avaj.weather.WeatherTower;

public class Simulation {

    public static void main(String [] args) {
        Parser p = new Parser();
        WeatherTower weatherTower = new WeatherTower();

        p.readFile(args[0]);
        try {
            PrintWriter file = new PrintWriter(new BufferedWriter(new FileWriter("Simulation.txt")));

            Aircraft.setWriter(file);
            Tower.setWriter(file);
            for (Flyable flyable: p.getFlyables()) {
                flyable.registerTower(weatherTower);
            }
            for (int i = 0; i < p.simulationTurns; i++) 
            weatherTower.changeWeather();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}