package avaj.aircrafts;

import avaj.weather.WeatherTower;

public class JetPlane extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

	JetPlane(String name, Coordinates coordinates) {
		super(name, coordinates);

	}

	public void updateCondition() {
        String weather = weatherTower.getWeather(coordinates);

		switch (weather) {
			default:
			case "RAIN":
				log(typeNameAndId() + "Damn it's raining not a problem for us");
				coordinates.setLatitude(coordinates.getLatitude() + 5);
				break;
			case "FOG":
				log(typeNameAndId() + "Time for fog ... not a problem for us");
				coordinates.setLatitude(coordinates.getLatitude() + 1);
				break;
			case "SUN":
				log(typeNameAndId() + "It's increadibly Sunny, not a problem for us");
				coordinates.setHeight(coordinates.getHeight() + 2);
				coordinates.setLatitude(coordinates.getLatitude() + 10);
				break;
			case "SNOW":
				log(typeNameAndId() + "Damn it's snowing ... not a problem for us");
				coordinates.setHeight(coordinates.getHeight() - 7);
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
        log(typeNameAndId() + "Fasten your seat belts and enjoy the trip.");
    }

    private String typeNameAndId() {
        return "JetPlane#" + name + "(" + id + "): ";
    }

}
