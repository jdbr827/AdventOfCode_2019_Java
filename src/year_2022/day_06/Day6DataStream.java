package year_2022.day_06;

import lombok.Getter;

import java.io.FileNotFoundException;

/**
 * Encapsulates the scanner and the "previous N" part of the problem.
 * With access control, we can abstract away how previous is updated.
 */
interface Day6DataStream {
    void scanNextChar();

    /**
     * @param x integer between 0 and N-1 inclusive.
     * @return the character that was scanned x scans ago (0 for most recently scanned)
     */
    Character getCharScannedXAgo(int x);

    static Day6DataStream of(String fileName, int N, int method) throws FileNotFoundException {
        Day6Reader scanner = new Day6Reader(fileName);


        if (method == 1) {
            return new Day6DataStream1(scanner, N);
        }
        return new Day6DataStream2(scanner, N);
    }
}


/*  Naive Solution: U = O(N); G = O(1) */
class Day6DataStream1 implements Day6DataStream {
    private final Day6Reader scanner;
    private final int N;
    private final Character[] previous;

    Day6DataStream1(Day6Reader scanner, int N) {
        this.scanner = scanner;
        this.N = N;
        this.previous = new Character[N];
    }

    public void scanNextChar() {
        for (int i = N - 1; i > 0; i--) {
            previous[i] = previous[i - 1];
        }
        previous[0] = scanner.getNextChar();
    }

    @Override
    public Character getCharScannedXAgo(int x) {
        return previous[x];
    }
}


/*  "clock-face" implementation: U = O(1) and G = O(1) */
class Day6DataStream2 implements Day6DataStream {
    private final Day6Reader scanner;
    int N;
    private int head_idx = -1; // the index of previous of the last char scanned
    @Getter private final Character[] previous; //= new Character[N];

    Day6DataStream2(Day6Reader scanner, int N) {
        this.N = N;
        this.scanner = scanner;
        this.previous = new Character[N];
    }

    public void scanNextChar() {
        head_idx++;
        head_idx %= N;
        previous[head_idx] = scanner.getNextChar();
    }

    @Override
    public Character getCharScannedXAgo(int x) {
        return previous[(head_idx - x + N) % N];
    }
}