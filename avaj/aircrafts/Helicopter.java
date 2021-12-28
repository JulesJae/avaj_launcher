package avaj.aircrafts;

import avaj.weather.WeatherTower;

public class Helicopter extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

	Helicopter(String name, Coordinates coordinates) {
		super(name, coordinates);

	}

    public void registerTower (WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
        log(typeNameAndId() + "Now ready for Climat Changes.");
    }

	public void updateCondition() {
		String weather = weatherTower.getWeather(coordinates);

		switch (weather) {
			default:
			case "RAIN":
				log(typeNameAndId() + "Damn it's raining enjoy the noise");
				coordinates.setLongitude(coordinates.getLongitude() + 5);
				break;
			case "FOG":
				log(typeNameAndId() + "Time for fog ... I see nothing");
				coordinates.setLongitude(coordinates.getLongitude() + 1);
				break;
			case "SUN":
				log(typeNameAndId() + "It's increadibly Sunny, ok for a loop ?");
				coordinates.setHeight(coordinates.getHeight() + 2);
				coordinates.setLongitude(coordinates.getLongitude() + 10);
				break;
			case "SNOW":
				log(typeNameAndId() + "Damn it's snowing ... It's dangerous");
				coordinates.setHeight(coordinates.getHeight() - 12);
				break;
		}
		if (coordinates.getHeight() <= 0) {
			log(typeNameAndId() + "Tower I'm landing at long: " + coordinates.getLongitude() + ", lat: " + coordinates.getLatitude());
			weatherTower.unregister(this);
		}
	}

    private String typeNameAndId() {
        return "Helicopter#" + name + "(" + id + "): ";
    }

}
