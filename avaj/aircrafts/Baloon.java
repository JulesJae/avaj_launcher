package avaj.aircrafts;

import avaj.weather.WeatherTower;

public class Baloon extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

	Baloon(String name, Coordinates coordinates) {
		super(name, coordinates);		
	}

	public void updateCondition() {
		String weather = weatherTower.getWeather(coordinates);

		switch (weather) {
			default:
			case "RAIN":
				log(typeNameAndId() + "Damn it's raining let's reduce the height");
				coordinates.setHeight(coordinates.getHeight() - 5);
				break;
			case "FOG":
				log(typeNameAndId() + "Time for fog ... Height minus 3");
				coordinates.setHeight(coordinates.getHeight() - 3);
				break;
			case "SUN":
				log(typeNameAndId() + "It's increadibly Sunny, go Higher !");
				coordinates.setHeight(coordinates.getHeight() + 5);
				coordinates.setLongitude(coordinates.getLongitude() + 2);
				break;
			case "SNOW":
				log(typeNameAndId() + "Damn it's snowing ... Height minus 15 !");
				coordinates.setHeight(coordinates.getHeight() - 15);
				break;
		}
		if (coordinates.getHeight() <= 0) {
			log(typeNameAndId() + "Tower I'm landing at long: " + coordinates.getLongitude() + ", lat: " + coordinates.getLatitude());
			weatherTower.unregister(this);
		}
	}
	
    public void registerTower (WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
        log(typeNameAndId() + "Now ready for touching the sun.");
    }

    private String typeNameAndId() {
        return "Baloon#" + name + "(" + id + "): ";
    }

}