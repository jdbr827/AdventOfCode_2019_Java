package year_2019;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day12 {
}

class Moon {
    int[] position;
    int[] velocity = {0, 0, 0};

    Moon(int[] initialPosition) {
        this.position = initialPosition;
    }

    public void applyGravityFrom(Moon otherMoon) {
        for (int i = 0; i < 3; i++) {
            if (otherMoon.position[i] < this.position[i]) {
                velocity[i] -= 1;
            } else if (otherMoon.position[i] > this.position[i]) {
                velocity[i] += 1;
            }
        }
    }

    public void applyVelocity() {
        for(int i=0; i<3; i++) {
            position[i] += velocity[i];
        }
    }

    public int calculateTotalEnergy() {
        return calculatePotentialEnergy() * calculateKineticEnergy();
    }

    private int calculateKineticEnergy() {
        return Arrays.stream(velocity).map(Math::abs).sum();

    }

    private int calculatePotentialEnergy() {
        return Arrays.stream(position).map(Math::abs).sum();
    }
}

class MoonReader {
    private static final Pattern moonReadInPattern = Pattern.compile("<x=([-]?[0-9]+), y=([-]?[0-9]+), z=([-]?[0-9]+)>");
    String fileName;

    MoonReader(String fileName){
        this.fileName = fileName;
    };

    public List<Moon> readInMoons() throws IOException {
        List<Moon> moons = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = br.readLine()) != null) {
            moons.add(MoonReader.extractMoon(line));
        }
        return moons;
    };

    static Moon extractMoon(String line) {

        // create matcher for pattern p and given string
        Matcher m = moonReadInPattern.matcher(line);

        // if an occurrence if a pattern was found in a given string...
        if (m.find()) {
            int x = Integer.parseInt(m.group(1));
            int y = Integer.parseInt(m.group(2));
            int z = Integer.parseInt(m.group(3));
            return new Moon(new int[]{x,y,z});
        } else {
            throw new RuntimeException("Could not extract moon from Regex: " + line);
        }

    }
}

class SolarSystem {
    List<Moon> moons = new ArrayList<>();

    public SolarSystem(String s) throws IOException {
        this.moons = new MoonReader(s).readInMoons();
    }



    public static void main(String[] args) throws IOException {
        SolarSystem solarSystem = new SolarSystem("src/year_2019/day_12_input.txt");
        solarSystem.executeNTimeSteps(1000);
        System.out.println(solarSystem.calculateTotalEnergy());


    }

    private int calculateTotalEnergy() {
        return moons.stream().mapToInt(Moon::calculateTotalEnergy).sum();
    }

    private void executeNTimeSteps(int n) {
        for (int i=0; i<n; i++) {executeTimeStep();}
    }

    private void executeTimeStep() {
        applyGravity();
        applyVelocity();
    }

    private void applyVelocity() {
        for (Moon moon: moons) {
            moon.applyVelocity();
        }
    }

    private void applyGravity() {
        for (Moon moon1 : moons) {
            for (Moon moon2: moons) {
                moon1.applyGravityFrom(moon2);
            }
    }
}




}
