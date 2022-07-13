package year_2019.day08;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static year_2019.day08.Day8.readNextLayer;


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

    public static int readNextNumber(FileReader inputFileReader) throws IOException {
        int r;
        if ((r = inputFileReader.read()) != -1) {
            char[] c = {(char) r};
            return Integer.parseInt(String.valueOf(c));
        }
        return -1;
    }

    public static int[] readNextLayer(FileReader inputFileReader, int layerSize) throws IOException {
        int[] this_layer = new int[layerSize];
        int i = 0;
        int z;
        while ((z = readNextNumber(inputFileReader)) != -1) {
            this_layer[i] = z;
            if (++i == layerSize) {
                return this_layer;
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        PixelatedImage image = new PixelatedImage(25, 6, "src/year_2019/day08/day_8_input.txt");

        /* Part 1 */
        int[] magicLayer = image.inputList.stream().min(Comparator.comparing((l) -> count(l, 0))).get();
        int ones = count(magicLayer, 1);
        int twos = count(magicLayer, 2);
        System.out.println(ones * twos);

        /* Part 2 */
        int[][][] layered_image = new int[image.numLayers][image.layerWidth][image.layerHeight];
        int i;
        for (i=0; i<image.numLayers; i++) {
            int[] oneDimLayer = image.inputList.get(i);
            int[][] twoDimLayer = new int[image.layerHeight][image.layerWidth];
            for (int j=0; j<image.layerSize; j++) {
                twoDimLayer[j / image.layerWidth][j % image.layerWidth] = oneDimLayer[j];
            }
            layered_image[i] = twoDimLayer;
        }

        int[][] processedImage = new int[image.layerHeight][image.layerWidth];
        for (i=0; i<image.layerWidth; i++) {
            for (int j=0; j<image.layerHeight; j++){
                for (int l=0; l<image.numLayers; l++) {
                    if (layered_image[l][j][i] != 2) {
                        processedImage[j][i] = layered_image[l][j][i];
                        break;
                    }
                }
            }
        }

        for (int[] row: processedImage){
            System.out.println(Arrays.toString(row)); // makes YEHEF
        }

        new Day8View(processedImage);




    }
}


class PixelatedImage {
    public int layerWidth;
    public int layerHeight;
    public List<int[]> inputList;
    public int numLayers;
    public int layerSize;

    public PixelatedImage(int layerWidth, int layerHeight, String fileName) throws IOException {
        FileReader inputFileReader = new FileReader(fileName);
        this.layerWidth = 25;
        this.layerHeight = 6;
        layerSize = layerWidth*layerHeight;
        inputList = new ArrayList<>();
        int[] thisLayer;
        numLayers = 0;
        while ((thisLayer = readNextLayer(inputFileReader, layerSize)) != null) {
            inputList.add(thisLayer);
            numLayers++;
        }
        for (int[] l : inputList) {
            System.out.println(Arrays.toString(l));
        }
    }

}