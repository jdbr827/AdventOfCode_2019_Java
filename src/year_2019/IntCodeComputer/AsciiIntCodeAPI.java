package year_2019.IntCodeComputer;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

public class AsciiIntCodeAPI {
    private final IntCodeAPI intCodeAPI;
    @Getter @Setter(AccessLevel.PRIVATE) private Long lastLongOutput = null;


    public AsciiIntCodeAPI(long[] tape) {
        this.intCodeAPI = new IntCodeAPI(tape);
    }

    public void startProgram() {
        this.intCodeAPI.startProgram();
    }

    public Optional<Character> waitForCharOutputOptionalSuspended() throws InterruptedException {
        Optional<Long> longOptional = intCodeAPI.waitForOutputOptionalSuspended();
        longOptional.ifPresent(this::setLastLongOutput);
        return longOptional.map(AsciiIntCodeAPI::convertLongToAsciiCharacter);
    }

    public Optional<Character> waitForCharOutputOptional() throws InterruptedException {
       Optional<Long> longOptional = intCodeAPI.waitForOutputOptional();
        longOptional.ifPresent(this::setLastLongOutput);
        return longOptional.map(AsciiIntCodeAPI::convertLongToAsciiCharacter);
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

    public Optional<Long> waitForNextOutputLong() throws InterruptedException {
        return intCodeAPI.waitForOutputOptional();
    }

    public static char convertLongToAsciiCharacter(long num) {
        return (char) Math.toIntExact(num);
    }
}
