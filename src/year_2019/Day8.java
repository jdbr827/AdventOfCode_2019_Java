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
        int layer_width = 25; int layer_height = 6;
        int layer_size = layer_width*layer_height;
        List<int[]> inputList = new ArrayList<>();
        int r;
        int[] this_layer = new int[layer_size];
        int i = 0;
        int num_layers = 0;
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
                num_layers++;
            }
        }
        for (int[] l : inputList) {
            System.out.println(Arrays.toString(l));
        }

        /* Part 1 */
        int[] magicLayer = inputList.stream().min(Comparator.comparing((l) -> count(l, 0))).get();
        int ones = count(magicLayer, 1);
        int twos = count(magicLayer, 2);
        System.out.println(ones * twos);

        /* Part 2 */
        int[][][] layered_image = new int[num_layers][layer_width][layer_height];
        for (i=0; i<num_layers; i++) {
            int[] oneDimLayer = inputList.get(i);
            int[][] twoDimLayer = new int[layer_height][layer_width];
            for (int j=0; j<layer_size; j++) {
                twoDimLayer[j / layer_width][j % layer_width] = oneDimLayer[j];
            }
            layered_image[i] = twoDimLayer;
        }

        int[][] image = new int[layer_height][layer_width];
        for (i=0; i<layer_width; i++) {
            for (int j=0; j<layer_height; j++){
                for (int l=0; l<num_layers; l++) {
                    if (layered_image[l][j][i] != 2) {
                        image[j][i] = layered_image[l][j][i];
                        break;
                    }
                }
            }
        }

        for (int[] row: image){
            System.out.println(Arrays.toString(row)); // makes YEHEF
        }




    }
}
