package year_2019.day24;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;

import static year_2019.day24.GameOfBugLife.readInBoard;


public class InfiniteGameOfBugLife {
    BugLifeLayer headLayer;


    public static void main(String[] args) throws Exception {
        new InfiniteGameOfBugLife(readInBoard());
    }

    InfiniteGameOfBugLife(Boolean[][] startingBoard) throws Exception {
        headLayer = new BugLifeLayer(startingBoard);
        System.out.println(totalLiveNow());
    }

    int totalLiveNow() throws Exception {
        int total = 0;
        Optional<BugLifeLayer> layerPointer = Optional.of(headLayer);
        while (layerPointer.isPresent()) {
            total += layerPointer.get().totalLiveNow();
            layerPointer = layerPointer.get().next;
        }
        layerPointer = headLayer.prev;
        while (layerPointer.isPresent()) {
            total += layerPointer.get().totalLiveNow();
            layerPointer = layerPointer.get().prev;
        }
        return total;
    };


}

class BugLifeLayer {
    private Boolean[][] board;
    Optional<BugLifeLayer> next = Optional.empty();
    Optional<BugLifeLayer> prev = Optional.empty();

    Boolean getValueAt(int x, int y) throws Exception {
        if (x==2 && y==2) {
            throw new Exception("Bug Life Layer has no value at middle!");
        }
        return board[x][y];


    }

    void setValueAt(int x, int y, Boolean b) throws Exception {
        if (x==2 && y==2) {
            throw new Exception("Bug Life Layer has no value at middle!");
        }
        board[x][y] = b;

    }
    BugLifeLayer() {
        this(new Boolean[5][5]);
    }

    BugLifeLayer(Boolean[][] board) {
        this.board = board;
        System.out.println(Arrays.deepToString(this.board));
    }


    protected BugLifeLayer getOrCreateNext() {
        if (!next.isPresent()) {
            next = Optional.of(new BugLifeLayer());
            next.get().prev = Optional.of(this);
        }
        return next.get();
    }

    protected BugLifeLayer getOrCreatePrev() {
        if (!prev.isPresent()) {
            prev = Optional.of(new BugLifeLayer());
            prev.get().next = Optional.of(this);
        }
        return prev.get();
    }

    public int totalLiveNow() throws Exception {
        int total = 0;
        for (int x=0; x<5; x++) {
            for (int y=0; y<5; y++) {
                if (x == 2 && y == 2) {continue;}
                total += getValueAt(x, y) ? 1 : 0;
            }
        }
        return total;
    }

    public int getLiveNeighbors(int x, int y) throws Exception {

        int liveNeighbors = 0;
        if (x == 0) {
            liveNeighbors += getOrCreatePrev().getValueAt(2, 1) ? 1 : 0;
        }
        if (y == 0) {
            liveNeighbors += getOrCreatePrev().getValueAt(1, 2) ? 1 : 0;
        }
        if (x == 4) {
            liveNeighbors += getOrCreatePrev().getValueAt(3, 2) ? 1 : 0;
        }
        if (y == 4) {
            liveNeighbors += getOrCreatePrev().getValueAt(2, 3) ? 1 : 0;
        }
        if (x == 2 && y == 1) {
            for (int yLower = 0; yLower < 5 ; yLower++) {
                liveNeighbors += getOrCreateNext().getValueAt(0, yLower) ? 1 : 0;
            }
        }
        if (x == 2 && y == 3) {
            for (int yLower = 0; yLower < 5 ; yLower++) {
                liveNeighbors += getOrCreateNext().getValueAt(4, yLower) ? 1 : 0;
            }
        }
        if (x == 1 && y == 2) {
            for (int xLower = 0; xLower < 5 ; xLower++) {
                liveNeighbors += getOrCreateNext().getValueAt(xLower, 9) ? 1 : 0;
            }
        }
        if (x == 1 && y == 3) {
            for (int xLower = 0; xLower < 5 ; xLower++) {
                liveNeighbors += getOrCreateNext().getValueAt(xLower, 4) ? 1 : 0;
            }
        }
        return liveNeighbors;
    }
}