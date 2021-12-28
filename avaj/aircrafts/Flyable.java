package avaj.aircrafts;

import avaj.weather.WeatherTower;

public interface Flyable {
    void updateCondition();
    void registerTower(WeatherTower weatherTower);
}