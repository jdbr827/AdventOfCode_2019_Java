package year_2022.day_06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Day6 {


    public static int part1(String fileName) throws FileNotFoundException {
       Day6Scanner scanner = new Day6Scanner(fileName);
       Character nextChar;
       int c = 0;

       Character[] previous = new Character[4];
       for (int i=0; i<4; i++) {
           previous[3-i] = scanner.getNextChar();
           c++;
       }

       while (true) {
           boolean allDiff = true;
           for (int i=0; i<4; i++) {
               for (int j = i + 1; j < 4; j++) {
                   if (previous[i].equals(previous[j])) {
                       allDiff = false;
                       break;
                   }
               }
           }
           if (allDiff) {return c;}

           for (int i=3; i>0; i--) {
               previous[i] = previous[i-1];
           }

           previous[0] = scanner.getNextChar();
           c++;
       }

    }
}
