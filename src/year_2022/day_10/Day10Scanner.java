package year_2022.day_10;

import utils.AOCScanner;
import utils.ReadIn;

import java.util.Iterator;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day10Scanner extends AOCScanner implements Iterator<Day10Operation> {

    Pattern instructionPattern = Pattern.compile("noop|addx (-?[0-9]+)");

    public Day10Scanner(String fileName) {
        super(fileName);
    }

    @Override
    public boolean hasNext() {
        return scanner.hasNextLine();
    }

    @Override
    public Day10Operation next() {
        Matcher m = instructionPattern.matcher(scanner.nextLine());
        ReadIn.findOrElseThrow(m, "Could not match instruction pattern");
        //System.out.println(m.group(0));
        if (m.group(0).equals("noop")) {
            return Day10Operation.Noop();
        }
        return Day10Operation.addx(Integer.parseInt(m.group(1)));
    }


}
