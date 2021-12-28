package avaj.aircrafts;

import java.io.PrintWriter;

public class Aircraft {
    protected long id;
    protected String name;
    protected Coordinates coordinates;

    protected static PrintWriter file;
    
    private static long idCounter = 0;

    public static void setWriter(PrintWriter writer) {
        file = writer;
    }

    public String getName() { return name; }

    protected Aircraft(String name, Coordinates coordinates) {
        this.id = nextId();
        this.name = name;
        this.coordinates = coordinates;
    }

    private long nextId() {
        return ++idCounter;
    }

    protected void log (String s) {
        file.println(s);
    }
}