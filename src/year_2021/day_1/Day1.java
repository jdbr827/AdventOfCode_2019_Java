package year_2021.day_1;

import javafx.scene.layout.Priority;

import javax.print.attribute.IntegerSyntax;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day1 {


    public static int readInNumbers(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

        //PriorityQueue<Integer> bestSoFar = new PriorityQueue<>(3, Comparator.reverseOrder());
        ArrayList<Integer> elves = new ArrayList<>();
        int current = 0;

        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            if (data.isEmpty()) {
                elves.add(current);
                //bestSoFar.offer(current);
                current = 0;
            } else {
                current += Integer.parseInt(data);
            }
        }
        //System.out.println(bestSoFar);
        elves.sort(Comparator.reverseOrder());
        return elves.get(0) + elves.get(1) + elves.get(2);
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(readInNumbers("src/year_2021/day_1/day_1_input.txt"));
    }
}
