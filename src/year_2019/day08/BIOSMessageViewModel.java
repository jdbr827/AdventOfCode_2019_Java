package year_2019.day08;

import year_2019.CartesianColorViewModel;
import year_2019.CartesianPoint;

import java.awt.*;

public class BIOSMessageViewModel extends CartesianColorViewModel {

    public BIOSMessageViewModel(int[][] grid) {
        super();
        for(int x=0; x<grid.length; x++) {
            for(int y=0; y<grid[0].length; y++) {
                setColorAtCartesian(new CartesianPoint(y, -x), grid[x][y] == 1 ? Color.WHITE : Color.BLACK);
            }
        }
    }

    @Override
    public Color getBackgroundColorAtCartesian(Point q) {
        return cartesianColorMap.getOrDefault(q, Color.GRAY);
    }

    @Override
    public Color getForegroundColorAtCartesian(Point q) {
        return getBackgroundColorAtCartesian(q);
    }


}
