package year_2019.day17;

import lombok.NoArgsConstructor;

import java.util.Scanner;

public interface Day17InputReader {
    String getNextLine();
}

@NoArgsConstructor
class Day17InputReaderImpl implements Day17InputReader {

    Scanner scanner = new Scanner(System.in);

    @Override
    public String getNextLine() {
        return scanner.nextLine();
    }
}
