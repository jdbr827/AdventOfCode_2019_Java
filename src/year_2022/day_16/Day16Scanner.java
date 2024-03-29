package year_2022.day_16;

import utils.AOCScanner;
import utils.ReadIn;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day16Scanner extends AOCScanner {

    static final Pattern valvesPattern = Pattern.compile(
            "Valve ([A-Z][A-Z]) has flow rate=([0-9]+); tunnel[s]? lead[s]? to valve[s]? ([[A-Z][A-Z],[ ]]*)([A-Z][A-Z])"
    );


    public Day16Scanner(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    private Valve scanNextLine() {
        if (scanner.hasNextLine()) {
            String thisLine = scanner.nextLine();
            Matcher m = valvesPattern.matcher(thisLine);
            ReadIn.findOrElseThrow(m, "Could not match valves pattern");

            List<String> nbrList = m.group(3).isEmpty() ? new ArrayList<>() : new java.util.ArrayList<>(List.of(m.group(3).split(", ")));
            nbrList.add(m.group(4));

            return new Valve(m.group(1), Integer.parseInt(m.group(2)), nbrList);
        }
        return null;
    }


    public List<Valve> scanAll() {
        List<Valve> valves = new ArrayList<>();
        Valve thisValve;
        while ((thisValve = scanNextLine()) != null) {
            valves.add(thisValve);
        }
        return valves;
    }
}
