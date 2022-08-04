package year_2019.day17;

import lombok.NoArgsConstructor;

import java.util.Scanner;

public interface Day17InputSource {
    String getNextLine();
}

@NoArgsConstructor
class Day17InputSourceImpl implements Day17InputSource {

    Scanner scanner = new Scanner(System.in);

    @Override
    public String getNextLine() {
        return scanner.nextLine();
    }
}
