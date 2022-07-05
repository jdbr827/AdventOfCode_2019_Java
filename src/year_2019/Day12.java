package year_2019;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day12 {
}

class Point3D {
    int x;
    int y;
    int z;
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
}

class SolarSystem {
    List<Moon> moons = new ArrayList<>();

    public SolarSystem(String s) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s));
        String line;
        while ((line = br.readLine()) != null) {
            moons.add(extractMoon(line));
        }

    }

    private Moon extractMoon(String line) {
        Pattern p = Pattern.compile("<x=([-]?[0-9]+), y=([-]?[0-9]+), z=([-]?[0-9]+)>");
        // create matcher for pattern p and given string
        Matcher m = p.matcher(line);

        // if an occurrence if a pattern was found in a given string...
        if (m.find()) {
            // ...then you can use group() methods.
            System.out.println(m.group(0)); // whole matched expression
            System.out.println(m.group(1)); // first expression from round brackets (Testing)
            System.out.println(m.group(2)); // second one (123)
            System.out.println(m.group(3)); // third one (Testing)
            return new Moon(new int[]{Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)), Integer.parseInt(m.group(3))});
        } else {
            throw new RuntimeException("Could not extract moon from Regex: " + line);
        }

    }

    public static void main(String[] args) throws IOException {
        SolarSystem solarSystem = new SolarSystem("src/year_2019/day_12_input.txt");
        solarSystem.executeNTimeSteps(1000);
        System.out.println(solarSystem.calculateTotalEnergy());


    }

    private int calculateTotalEnergy() {
        return 0;
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
