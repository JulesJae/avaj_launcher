package avaj.aircrafts;

public class Coordinates {
    private int longitude;
    private int latitude;
    private int height;

    Coordinates(int longitude, int latitude, int height) {
        this.longitude = longitude;
        this.latitude = latitude;
        if (height < 0)
            height = 0;
        else if (height > 100)
            height = 100;
        this.height = height;
    }

    public int getLongitude() { return longitude; }
    public int getLatitude() { return latitude; }
    public int getHeight() { return height; }

    public void setLatitude(int latitude) { this.latitude = latitude; }
    public void setLongitude(int longitude) { this.longitude = longitude; }
    public void setHeight(int height) { this.height = height; }
}