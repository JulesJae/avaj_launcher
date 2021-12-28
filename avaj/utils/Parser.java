package avaj.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import avaj.aircrafts.Flyable;
import avaj.aircrafts.AircraftFactory;

public class Parser {
	public int simulationTurns;
	List<Flyable> flyables = new ArrayList<Flyable>();

	static int
		TYPE = 0,
		NAME = 1,
		LONG = 2,
		LAT = 3,
		HEIGHT = 4;

	static String [] TYPES = {
		"Baloon",
		"JetPlane",
		"Helicopter"
	};

	public List<Flyable> getFlyables() { return flyables; }

	public void readFile(String filePath) {
		int lineNumber = 0;
		String line = null;

		try {
			BufferedReader file = new BufferedReader(new FileReader(filePath));

			line = file.readLine();
			getSimulationTurns(line);
			while ((line = file.readLine()) != null) {
				lineNumber++;
				parseLine(line);
			}
			file.close();
		} catch(IOException e) {
			logError("IOException - " + e.getMessage());
		} catch (NumberFormatException e) {
			logError("NumberFormatException at line " + lineNumber + ": '" + line + "' -> must only contains positive digits");
		} catch (Exception e) {
			logError("Exception at line " + lineNumber + " - " + e.getMessage());
		}
	}

	private void getSimulationTurns(String line) throws NumberFormatException {
		simulationTurns = Integer.parseInt(line);
		if (simulationTurns < 0)
			throw new NumberFormatException();
	}

	private boolean checkType(String type) {
		return Arrays.asList(TYPES).contains(type);
	}

	private void parseLine(String line) throws Exception {
		String [] tokens;

		line = line.trim().replace(" +", " ");
		tokens = line.split(" ");
		if (tokens.length == 1)
			return ;
		if (tokens.length != 5)
			throw new Exception("Aircraft line bad format: '" + line + "'");
		if (checkType(tokens[TYPE])
			&& checkCoordinate(tokens[LONG])
			&& checkCoordinate(tokens[LAT])
			&& checkCoordinate(tokens[HEIGHT])) {
			flyables.add(AircraftFactory.newAircraft(tokens[TYPE], tokens[NAME], Integer.parseInt(tokens[LONG]), Integer.parseInt(tokens[LAT])
				, Integer.parseInt(tokens[HEIGHT])));
		} else
		throw new Exception("Aircraft line bad format: '" + line + "'");
	}

	private boolean checkCoordinate(String val) {
		try {
			int numVal = Integer.parseInt(val);
			if (numVal >= 0)
				return true;
		} catch (NumberFormatException e) {
			return false;
		}
		return false;
	}

	private void logError(String s) {
		System.out.println(s);
		System.exit(0);
	}
}
