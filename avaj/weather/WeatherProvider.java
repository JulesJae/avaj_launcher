package avaj.weather;

import avaj.aircrafts.Coordinates;

public class WeatherProvider {
	static private WeatherProvider weatherProvider;
	static private String [] weather = {
		"RAIN",
		"FOG",
		"SUN",
		"SNOW"
	};

	private WeatherProvider() {

	}

	static public WeatherProvider getProvider() {
		if (weatherProvider == null)
			weatherProvider = new WeatherProvider();
		return weatherProvider;
	}

	public String getCurrentWeather(Coordinates coordinates) {
		int rand = 1;
		int height = coordinates.getHeight();
		int lon = coordinates.getLongitude();
		int lat = coordinates.getLatitude();

		while (height >=0 && lon >= 0 && lat >= 0) {
			rand += (height + lon) * lat;
			height -= 1;
			lon -= lat % 2;
			lat -= lon % 3;
		}
		return weather[rand % 4];
	}
}