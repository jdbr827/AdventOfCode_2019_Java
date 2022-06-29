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
        runNMinutes(200);
        System.out.println(totalLiveNow());
        //displayLayers();
    }

    private void runNMinutes(int n) throws Exception {
        for (int x = 0; x < n; x++) {
            runOneMinute();
        }
    }

    int totalLiveNow() throws Exception {
        int total = 0;

        for (Optional<BugLifeLayer> layer = Optional.of(headLayer); layer.isPresent(); layer = layer.get().next) {
            total += layer.get().totalLiveNow();
        }

        for (Optional<BugLifeLayer> layer = headLayer.prev; layer.isPresent(); layer = layer.get().prev) {
            total += layer.get().totalLiveNow();
        }
        return total;
    }

    public void runOneMinute() throws Exception {
        BugLifeLayer newHead = headLayer.createNextState();
        BugLifeLayer connectingPointer = newHead;
        Optional<BugLifeLayer> layer;
        BugLifeLayer newLayer;
        BugLifeLayer trailingPointer = headLayer;
        for (layer = headLayer.next; layer.isPresent(); layer = layer.get().next) {
            newLayer = layer.get().createNextState();
            connectingPointer.setNext(newLayer);
            connectingPointer = newLayer;
            trailingPointer = trailingPointer.next.get();
        }
        if (trailingPointer.totalLiveNow() != 0) {
            BugLifeLayer newHighestLayer = new BugLifeLayer();
            trailingPointer.setNext(newHighestLayer);
            //System.out.println(newHighestLayer.prev.get() == headLayer);
            newLayer = newHighestLayer.createNextState();
            connectingPointer.setNext(newLayer);
        }

        connectingPointer = newHead;
        trailingPointer = headLayer;
        for (layer = headLayer.prev; layer.isPresent(); layer = layer.get().prev) {
            newLayer = layer.get().createNextState();
            connectingPointer.setPrev(newLayer);
            connectingPointer = newLayer;
            trailingPointer = trailingPointer.prev.get();
        }
        if (trailingPointer.totalLiveNow() != 0) {
             BugLifeLayer newLowestLayer = new BugLifeLayer();
             trailingPointer.setPrev(newLowestLayer);
             newLayer = newLowestLayer.createNextState();
             connectingPointer.setPrev(newLayer);
        }

        headLayer = newHead;
    }

    void displayLayers() throws Exception {
        int idx = 0;

        for (Optional<BugLifeLayer> layer = Optional.of(headLayer); layer.isPresent(); layer = layer.get().next) {
           for (int x =0; x<5; x++) {
               String msg = "";
               for (int y=0; y<5; y++) {
                   if (x==2 && y==2) {
                       msg += String.valueOf(idx);
                   } else {
                       msg += layer.get().getValueAt(x, y) ? '#' : '.';
                   }
               }
               System.out.println(msg);
           }
           System.out.println();
           idx += 1;

        }

        idx = -1;
        for (Optional<BugLifeLayer> layer = headLayer.prev; layer.isPresent(); layer = layer.get().prev) {
           for (int x =0; x<5; x++) {
               String msg = "";
               for (int y=0; y<5; y++) {
                   if (x==2 && y==2) {
                       msg += String.valueOf(idx);
                   } else {
                       msg += layer.get().getValueAt(x, y) ? '#' : '.';
                   }
               }
               System.out.println(msg);
           }
           System.out.println();
           idx -= 1;
        }

    }


}

class BugLifeLayer {
    private Boolean[][] board;
    Optional<BugLifeLayer> next = Optional.empty();
    Optional<BugLifeLayer> prev = Optional.empty();

    String displayBoard() {
        return Arrays.deepToString(board);

    }
    public Boolean getValueAt(int x, int y) throws Exception {
        if (x==2 && y==2) {
            throw new Exception("Bug Life Layer has no value at middle!");
        }
        return board[x][y];
    }

    void setNext(BugLifeLayer layer) {
        next = Optional.of(layer);
        next.get().prev = Optional.of(this);
    }

    void setPrev(BugLifeLayer layer) {
        prev = Optional.of(layer);
        prev.get().next = Optional.of(this);
    }



    void setValueAt(int x, int y, Boolean b) throws Exception {
        if (x==2 && y==2) {
            throw new Exception("Bug Life Layer has no value at middle!");
        }
        board[x][y] = b;

    }



    BugLifeLayer() {
        this(createEmptyBoard());
    }

    static private Boolean[][] createEmptyBoard() {
        Boolean[][] myBoard = new Boolean[5][5];
        for (int x=0; x<5; x++) {
            for (int y=0; y<5; y++) {
                if (x == 2 && y == 2) {continue;}
                myBoard[x][y] = false;
            }
        }
        return myBoard;
    }

    BugLifeLayer(Boolean[][] board) {
        this.board = board;
//        System.out.println(Arrays.deepToString(this.board));
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

    Boolean getPrevValueAt(int x, int y) throws Exception {
        if (prev.isPresent()) {
            return prev.get().getValueAt(x, y);
        }
        return false;
    }

    Boolean getNextValueAt(int x, int y) throws Exception {
        if (next.isPresent()) {
            return next.get().getValueAt(x, y);
        }
        return false;
    }

    public int countLiveNeighbors(int x, int y) throws Exception {
        return countLiveLeftNeighbors(x, y) + countLiveRightNeighbors(x, y) + countLiveAboveNeighbors(x, y) + countLiveBelowNeighbors(x, y);
    }

    private int countLiveBelowNeighbors(int x, int y) throws Exception {
        if (x == 4) {
            return getPrevValueAt(3, 2) ? 1 : 0;
        }
        if (x == 1 && y == 2) {
            int tot = 0;
            for (int yLower = 0; yLower < 5 ; yLower++) {
                tot += getNextValueAt(0, yLower) ? 1 : 0;
            }
            return tot;
        }
        return getValueAt(x+1, y) ? 1 : 0;
    }

    private int countLiveAboveNeighbors(int x, int y) throws Exception {
        if (x == 0) {
            return getPrevValueAt(1, 2) ? 1 : 0;
        }
        if (x == 3 && y == 2) {
            int tot = 0;
            for (int yLower = 0; yLower < 5 ; yLower++) {
                tot += getNextValueAt(4, yLower) ? 1 : 0;
            }
            return tot;
        }
        return getValueAt(x-1, y) ? 1 : 0;
    }

    private int countLiveRightNeighbors(int x, int y) throws Exception {
        if (y == 4) {
            return getPrevValueAt(2, 3) ? 1 : 0;
        }
        else if (x == 2 && y == 1) {
            int tot = 0;
            for (int xLower = 0; xLower < 5 ; xLower++) {
                tot += getNextValueAt(xLower, 0) ? 1 : 0;
            }
            return tot;
        }
        return getValueAt(x, y+1) ? 1 : 0;
    }

    private int countLiveLeftNeighbors(int x, int y) throws Exception {

        if (y == 0) {
            return getPrevValueAt(2, 1) ? 1 : 0;
        }
        if (x == 2 && y == 3) {
            int tot = 0;
            for (int xLeft = 0; xLeft < 5; xLeft++) {
                tot += getNextValueAt(xLeft, 4) ? 1 : 0;
            }
            return tot;
        }
        return getValueAt(x, y-1) ? 1 : 0;
    }

    public BugLifeLayer createNextState() throws Exception {
        Boolean[][] newBoard = new Boolean[5][5];
        for (int x=0; x<5; x++) {
            for (int y = 0; y < 5; y++) {
                if (x == 2 && y == 2) {
                    continue;
                }
                newBoard[x][y] = determineNewState(x, y);
            }
        }
        return new BugLifeLayer(newBoard);

    };

    private boolean determineNewState(int x, int y) throws Exception {
        boolean currentState = this.getValueAt(x, y);
        int liveNeighbors = countLiveNeighbors(x, y);
        if (currentState) {
            return liveNeighbors == 1;
        } else {
            return liveNeighbors == 1 || liveNeighbors == 2;
        }
    }
}