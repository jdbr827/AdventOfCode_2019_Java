package year_2022.day_04;

import utils.AOCScanner;
import utils.ReadIn;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4Scanner extends AOCScanner implements Iterator<Day4AssignmentPair> {
    static final Pattern rangesPattern = Pattern.compile("([0-9]+)-([0-9]+),([0-9]+)-([0-9]+)");


    public Day4Scanner(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    @Override
    public boolean hasNext() {
        return scanner.hasNextLine();
    }

    @Override
    public Day4AssignmentPair next() {
        Matcher m = rangesPattern.matcher(scanner.nextLine());
        ReadIn.findOrElseThrow(m, "Could not match ranges pattern");
        return new Day4AssignmentPair(
                Integer.parseInt(m.group(1)),
                Integer.parseInt(m.group(2)),
                Integer.parseInt(m.group(3)),
                Integer.parseInt(m.group(4))
        );
    }
}
