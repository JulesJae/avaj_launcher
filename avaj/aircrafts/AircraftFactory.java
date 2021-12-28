package avaj.aircrafts;

public class AircraftFactory {

    private AircraftFactory() {}

    static public Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
        Coordinates coordinates = new Coordinates(longitude, latitude, height);
        switch (type){
            default:
            case "Baloon":
                return new Baloon(name, coordinates);
            case "JetPlane":
                return new JetPlane(name, coordinates);
            case "Helicopter":
                return new Helicopter(name, coordinates);
        }
    }
}