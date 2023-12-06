package year_2023.day_06;

public class Day6 {

    public static int marginOfErrorToBeatRecord(int time, int record) {
        double sqrtAmnt = Math.sqrt(Math.pow(time, 2) - 4*record);

        double smallerRoot = (time - sqrtAmnt) / 2;
        int smallestWin = (int) Math.ceil(smallerRoot);
        if (smallerRoot == smallestWin) {
            smallestWin++;
        }

        double largerRoot = (time + sqrtAmnt) / 2;
        int largestWin = (int) Math.floor((time + sqrtAmnt) / 2 );
        if (largerRoot == largestWin) {
            largestWin--;
        }
        return 1 + largestWin - smallestWin;
    }
}
