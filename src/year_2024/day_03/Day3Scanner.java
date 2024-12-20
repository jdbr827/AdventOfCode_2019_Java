package year_2024.day_03;

import utils.AOCScanner;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3Scanner extends AOCScanner {
    static final Pattern multOpPattern = Pattern.compile("mul\\(([0-9]{1,3}),([0-9]{1,3})\\)");

    public Day3Scanner(String fileName) {
        super(fileName);
    }


    public int scan() {
        AtomicInteger tot = new AtomicInteger();
        forEachLine((String line) -> {
            Matcher m = multOpPattern.matcher(line);
            while (m.find()) {
                //System.out.println("FOUND ONE!");
                //System.out.println(line.substring(m.start(), m.end()));
                tot.addAndGet(Integer.parseInt(m.group(1)) * Integer.parseInt(m.group(2)));
            }
        });
        return tot.intValue();
    }
}
