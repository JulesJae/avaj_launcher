package avaj.weather;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import avaj.aircrafts.Flyable;

public class Tower {
    private List<Flyable> observers = new ArrayList<Flyable>();
    private List<Flyable> toRemove = new ArrayList<Flyable>();

    private static PrintWriter file;

    public static void setWriter(PrintWriter writer) {
        file = writer;
    }

    public void register(Flyable flyable) {
        if (!observers.contains(flyable))
            observers.add(flyable);
    }

    public void unregister(Flyable flyable) {
        toRemove.add(flyable);
        file.println("Tower: Roger that, you're now unregistered from WeatherTower");
    }

    protected void conditionsChanged() {
        for (Flyable flyable: observers) {
            flyable.updateCondition();
        }
        if (toRemove.size() > 0) {
            observers.removeAll(toRemove);
            toRemove.clear();
        }
    }

}