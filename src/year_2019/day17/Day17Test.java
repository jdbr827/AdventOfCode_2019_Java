package year_2019.day17;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day17Test {

    @Test
    void test_D17_P1() throws InterruptedException {
        assertEquals(3448, Day17.part1());
    }

    @Test
    void test_D17_P2() throws InterruptedException {
        Day17InputReader fakeScanner = Mockito.mock(Day17InputReader.class);
        Mockito.when(fakeScanner.getNextLine())
                .thenReturn("A,A,B,C,C,A,C,B,C,B")
                .thenReturn("L,4,L,4,L,6,R,10,L,6")
                .thenReturn("L,12,L,6,R,10,L,6")
                .thenReturn("R,8,R,10,L,6")
                .thenReturn("n");
        assertEquals(762405L, Day17.part2Helper(fakeScanner));
    }
}
