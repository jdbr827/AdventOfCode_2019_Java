package year_2019.day12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class MoonReader {
    private static final Pattern moonReadInPattern = Pattern.compile("<x=([-]?[0-9]+), y=([-]?[0-9]+), z=([-]?[0-9]+)>");
    String fileName;

    MoonReader(String fileName) {
        this.fileName = fileName;
    }

    public List<Moon> readInMoons() throws IOException {
        List<Moon> moons = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = br.readLine()) != null) {
            moons.add(MoonReader.extractMoon(line));
        }
        return moons;
    }

    static Moon extractMoon(String line) {

        // create matcher for pattern p and given string
        Matcher m = moonReadInPattern.matcher(line);

        // if an occurrence if a pattern was found in a given string...
        if (m.find()) {
            int x = Integer.parseInt(m.group(1));
            int y = Integer.parseInt(m.group(2));
            int z = Integer.parseInt(m.group(3));
            return new Moon(new int[]{x, y, z});
        } else {
            throw new RuntimeException("Could not extract moon from Regex: " + line);
        }

    }
}
