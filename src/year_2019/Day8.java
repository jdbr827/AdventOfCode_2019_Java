package year_2019;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class Day8 {

    public static int count(int[] layer, int target) {
        int cnt = 0;
        for (int num : layer) {
            if (num == target) {
                cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        FileReader inputFileReader = new FileReader("./src/year_2019/day_8_input.txt");
        int layer_size = 25*6;
        List<int[]> inputList = new ArrayList<>();
        int r;
        int[] this_layer = new int[layer_size];
        int i = 0;
        while ((r = inputFileReader.read()) != -1) {
            char[] c = {(char) r};
            int z = Integer.parseInt(String.valueOf(c));
            //System.out.println(z);
            this_layer[i] = z;
            if (++i == layer_size) {
                //System.out.println(Arrays.toString(this_layer));
                inputList.add(this_layer);
                this_layer = new int[layer_size];
                i = 0;
            }
        }
        for (int[] l : inputList) {
            System.out.println(Arrays.toString(l));
        }

        int[] magicLayer = inputList.stream().min(Comparator.comparing((l) -> count(l, 0))).get();
        int ones = count(magicLayer, 1);
        int twos = count(magicLayer, 2);
        System.out.println(ones * twos);



    }
}
