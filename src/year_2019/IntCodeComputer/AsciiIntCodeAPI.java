package year_2019.IntCodeComputer;

import java.util.Optional;

public class AsciiIntCodeAPI {
    private final IntCodeAPI intCodeAPI;

    public AsciiIntCodeAPI(long[] tape) {
        this.intCodeAPI = new IntCodeAPI(tape);
    }

    public void startProgram() {
        this.intCodeAPI.startProgram();
    }

    public Optional<Character> waitForCharOutputOptionalSuspended() throws InterruptedException {
        return intCodeAPI
                .waitForOutputOptionalSuspended()
                .map(AsciiIntCodeAPI::convertLongToAsciiCharacter);
    }

    public Optional<Character> waitForCharOutputOptional() throws InterruptedException {
        return intCodeAPI
                .waitForOutputOptional()
                .map(AsciiIntCodeAPI::convertLongToAsciiCharacter);
    }

    public void sendCharInput(char c) {
        intCodeAPI.sendInput((long)(int)c);
    }

    public void getNextOutputsToString() throws InterruptedException {
        Optional<Character> output;
        StringBuilder sb;
        sb = new StringBuilder();
        while ((output = waitForCharOutputOptionalSuspended()).isPresent()) {
            char thisChar = output.get();
            sb.append(thisChar);
        }
        System.out.println(sb);
    }

    public static char convertLongToAsciiCharacter(long num) {
        return (char) Math.toIntExact(num);
    }
}
