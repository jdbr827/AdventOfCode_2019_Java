package year_2023.day_13;

import utils.AOCScanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day13 {

    List<List<String>> formations;

    Day13(String fileName) {
        AOCScanner scanner = new AOCScanner(fileName);
        formations = new ArrayList<>();
        while(scanner.hasNextLine()) {
            List<String> thisFormation = new ArrayList<>();
            String line;
            while (scanner.hasNextLine() && !(line = scanner.nextLine()).equals("")) {
                thisFormation.add(line);
            }
            formations.add(thisFormation);
        }
    }

    public int getReflectionTotal() {
        return formations.stream().map(Day13::getReflectionScore).reduce(0, Math::addExact);
    }

    static int getReflectionScore(List<String> formation) {
        int N = formation.size();
        int M = formation.get(0).length();

        for (int i=1; i<N; i++) {
            boolean candidateFlag = true;
            for (int di=0; i-di-1>=0 && i+di < N; di++) {
                int row_less_than_i = i-di-1;
                int row_greater_than_i = i + di;
                if (!formation.get(row_less_than_i).equals(formation.get(row_greater_than_i))) {
                        candidateFlag = false;
                        break;
                    }
                }
            if (candidateFlag) {
                return 100 * i;
            }
        }



        for (int i=1; i<M; i++) {
            boolean candidateFlag = true;
            for (int di=0; i-di-1 >= 0 && i+di < M; di++) {
                int col_less_than_i = i - di - 1;
                int col_greater_than_i = i + di;
                for (String s : formation) {
                    if (s.charAt(col_less_than_i) != (s.charAt(col_greater_than_i))) {
                        candidateFlag = false;
                        break;
                    }
                }
                if (!candidateFlag) {
                    break;
                }
            }
            if (candidateFlag) {
                return i;
            }
        }

        System.out.println("ISSUE!");
        System.out.println(formation);
        return 0;



    }
}
