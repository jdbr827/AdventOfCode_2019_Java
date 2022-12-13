package year_2022.day_05;

import com.google.common.base.Splitter;
import utils.ReadIn;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day5Scanner {
    Scanner scanner;

    static final Pattern cratesPattern = Pattern.compile("\\[([A-Z])]");
    static final Pattern numbersPattern = Pattern.compile("\\s([0-9]+)\\s");

    public Day5Scanner(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        scanner = new Scanner(file);
    }

    public List<Character> getNextLine() {
        String line = scanner.nextLine();
        //System.out.println(line);
        List<String> splitLine = Splitter.fixedLength(4).splitToList(line);
        //System.out.println(splitLine.get(0).charAt(1));
        if (splitLine.get(0).charAt(1) == '1') {
            return List.of();
        }

        return splitLine.stream().map((crate) -> {
            if (crate.trim().isEmpty()) {
                return '7';
            } else {
                Matcher m = cratesPattern.matcher(crate);
                ReadIn.findOrElseThrow(m, "Could not match ranges pattern");
                return m.group(1).charAt(0);
            }
        }).collect(Collectors.toList());
    }
}
