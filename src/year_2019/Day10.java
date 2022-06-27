package year_2019;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

// DOES NOT WORK!
public class Day10 {

     public static void main(String[] args) throws IOException {
         Set<Point> asteroidLocations = getAsteroidLocations();
         int mostSlopes = 0;
         Point bestPoint = null;
         for (Point asteroid1 : asteroidLocations) {
             Set<Point2D.Double> slopes = new HashSet<>();
             Set<Point> visiblePoints = new HashSet<>();
             for (Point asteroid2: asteroidLocations) {
                 if (asteroid1 != asteroid2) {
                     double magnitude = asteroid1.distance(asteroid2);
                     Point2D.Double slope = new Point2D.Double((asteroid2.getX() - asteroid1.getX())/magnitude, (asteroid2.getY() - asteroid1.getY())/magni
                         }
                     }
                     slopes.add(slope);
                 }
             }
             System.out.println(asteroid1 + " " + slopes.size() + slopes);
             if (mostSlopes < slopes.size()) {
                 bestPoint = asteroid1;
                 mostSlopes = slopes.size();
             }

         }
         System.out.println(bestPoint);
         System.out.println(mostSlopes);


     }

    private static Set<Point> getAsteroidLocations() throws IOException {
        Set<Point> asteroidLocations = new HashSet<>();
        FileReader inputFileReader = new FileReader("./src/year_2019/day_10_input.txt");
        int r;
        int x = 0;
        int y = 0;
        while ((r = inputFileReader.read()) != -1) {
            char c = (char) r;
            switch (c) {
                case '#':
                    asteroidLocations.add(new Point(x, y));
                    x += 1;
                    break;
                case '\n':
                    y += 1;
                    x = 0;
                    break;
                default:
                    x+=1;
            }
        }
        return asteroidLocations;
    }
}
