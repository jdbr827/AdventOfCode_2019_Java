package year_2022.day_04;

import utils.ReadIn;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4Scanner {
    private Scanner scanner;
    static final Pattern rangesPattern = Pattern.compile("([0-9]+)-([0-9]+),([0-9]+)-([0-9]+)");


    public Day4Scanner(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        scanner = new Scanner(file);
    }

    public Matcher getNextLine() {
        if (scanner.hasNextLine()) {
            Matcher m = rangesPattern.matcher(scanner.nextLine());
            ReadIn.findOrElseThrow(m, "Could not match ranges pattern");
            return m;
        }
        return null;
    }


}
