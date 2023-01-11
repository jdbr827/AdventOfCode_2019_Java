package year_2022.day_06;

import lombok.Getter;

/**
 * Encapsulates the scanner and the "previous N" part of the problem.
 * With access control, we can abstract away how previous is updated.
 */
interface Day6DataStream {

    /**
     * Scans the next character of the stream
     */
    void scanNextChar();
    /**
     * @param x integer between 0 and N-1 inclusive.
     * @return the character that was scanned x scans ago (0 for most recently scanned)
     */
    Character getCharScannedXAgo(int x);

    /**
     * Returns the number of scans made so far by the stream
     * @return
     */
    int getNumScanned();
}


/*  Naive Solution: U = O(N); G = O(1) */
class Day6DataStream1 implements Day6DataStream {
    private final Day6Reader scanner;
    private final int N;
    private final Character[] previous;
    private int numScanned = 0;

    Day6DataStream1(Day6Reader scanner, int N) {
        this.scanner = scanner;
        this.N = N;
        this.previous = new Character[N];
    }

    public void scanNextChar() {
        if (N - 1 >= 0) System.arraycopy(previous, 0, previous, 1, N - 1);
        previous[0] = scanner.getNextChar();
        numScanned++;

    }

    @Override
    public Character getCharScannedXAgo(int x) {
        if (numScanned <= x) {
            throw new Error("We haven't scanned enough to go back that far yet.");
        }

        return previous[x];
    }

    @Override
    public int getNumScanned() {
        return numScanned;
    }
}


/*  "clock-face" implementation: U = O(1) and G = O(1) */
class Day6DataStream2 implements Day6DataStream {
    private final Day6Reader scanner;
    int N;
    private int head_idx = -1; // the index of previous of the last char scanned
    @Getter private final Character[] previous; //= new Character[N];
    private int numScanned = 0;

    Day6DataStream2(Day6Reader scanner, int N) {
        this.N = N;
        this.scanner = scanner;
        this.previous = new Character[N];
    }

    public void scanNextChar() {
        head_idx++;
        head_idx %= N;
        previous[head_idx] = scanner.getNextChar();
        numScanned++;
    }

    @Override
    public int getNumScanned() {
        return numScanned;
    }

    @Override
    public Character getCharScannedXAgo(int x) {
        if (numScanned <= x) {
            throw new Error("We haven't scanned enough to go back that far yet.");
        }

        return previous[(head_idx - x + N) % N];
    }
}