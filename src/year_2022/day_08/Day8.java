package year_2022.day_08;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Day8 {


    public static int part1(String fileName) throws FileNotFoundException {
       List<List<Integer>> trees = new Day8Scanner(fileName).readInMatrix();
       int N = trees.size();
       int M = trees.get(0).size();
       boolean[][] isVisible = new boolean[N][M];
       int count = 0;
       for (int i=0; i<N; i++) {
           for (int j=0; j<N; j++) {
               int finalJ = j;
               int finalI = i;
               if (IntStream.range(0, i).allMatch((i_prime -> trees.get(i_prime).get(finalJ) < trees.get(finalI).get(finalJ)))) {
                   isVisible[i][j] = true;
                   count += 1;
                   continue;
               }
               if (IntStream.range(i+1, N).allMatch((i_prime -> trees.get(i_prime).get(finalJ) < trees.get(finalI).get(finalJ)))) {
                   isVisible[i][j] = true;
                   count += 1;
                   continue;
               }
                if (IntStream.range(0, j).allMatch((j_prime -> trees.get(finalI).get(j_prime) < trees.get(finalI).get(finalJ)))) {
                   isVisible[i][j] = true;
                   count += 1;
                   continue;
               }
               if (IntStream.range(j+1, M).allMatch((j_prime -> trees.get(finalI).get(j_prime) < trees.get(finalI).get(finalJ)))) {
                   isVisible[i][j] = true;
                   count += 1;
                   continue;
               }
           }
       }
       return count;
    }

     public static long part2(String fileName) throws FileNotFoundException {
       List<List<Integer>> trees = new Day8Scanner(fileName).readInMatrix();
       int N = trees.size();
       int M = trees.get(0).size();
       long[][] scenicScore = new long[N][M];
       long bestScenicScore = 0;
       int count = 0;
       for (int i=0; i<N; i++) {
           for (int j=0; j<N; j++) {
               int finalJ = j;
               int finalI = i;
               long up = IntStream.range(1, i+1).takeWhile(i_prime -> trees.get(finalI - i_prime).get(finalJ) < trees.get(finalI).get(finalJ)).count() + ((IntStream.range(0, i).allMatch((i_prime -> trees.get(i_prime).get(finalJ) < trees.get(finalI).get(finalJ))))? 0 : 1 );
               //System.out.println(Arrays.toString(IntStream.range(1, i+1).takeWhile(i_prime -> trees.get(finalI - i_prime).get(finalJ) < trees.get(finalI).get(finalJ)).toArray()));
               long down = IntStream.range(i+1, N).takeWhile(i_prime -> trees.get(i_prime).get(finalJ) < trees.get(finalI).get(finalJ)).count() + ((IntStream.range(i+1, N).allMatch((i_prime -> trees.get(i_prime).get(finalJ) < trees.get(finalI).get(finalJ)))) ? 0 : 1);
               long left = IntStream.range(1, j+1).takeWhile(j_prime -> trees.get(finalI).get(finalJ - j_prime) < trees.get(finalI).get(finalJ)).count() + ((IntStream.range(0, j).allMatch((j_prime -> trees.get(finalI).get(j_prime) < trees.get(finalI).get(finalJ)))) ? 0 : 1);
               long right = IntStream.range(j+1, M).takeWhile(j_prime -> trees.get(finalI).get(j_prime) < trees.get(finalI).get(finalJ)).count() + ((IntStream.range(j+1, M).allMatch((j_prime -> trees.get(finalI).get(j_prime) < trees.get(finalI).get(finalJ)))) ? 0 : 1);
               //System.out.println(i + " " + j + " " + up + " " + down + " " + left + " " + right);
               scenicScore[finalI][finalJ] = up * down * left * right;
               bestScenicScore = Math.max(bestScenicScore, scenicScore[i][j]);

           }
       }
       return bestScenicScore;
    }
}
