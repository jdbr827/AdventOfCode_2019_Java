package year_2019.day24;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;

import static year_2019.day24.GameOfBugLife.readInBoard;


public class InfiniteGameOfBugLife {
    BugLifeLayer headLayer;


    public static void main(String[] args) throws IOException {
        new InfiniteGameOfBugLife(readInBoard());
    }

    InfiniteGameOfBugLife(Boolean[][] startingBoard) {
        headLayer = new BugLifeLayer(startingBoard);
        System.out.println(totalLiveNow());
    }

    int totalLiveNow() {
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
    Boolean[][] board;
    Optional<BugLifeLayer> next = Optional.empty();
    Optional<BugLifeLayer> prev = Optional.empty();

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

    public int totalLiveNow() {
        int total = 0;
        for (int x=0; x<5; x++) {
            for (int y=0; y<5; y++) {
                total += board[x][y] ? 1 : 0;
            }
        }
        return total;
    }
}