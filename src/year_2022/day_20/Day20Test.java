package year_2022.day_20;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day20Test {

    @Test
    public void test_compute_grove_coordinates() {
        assertEquals(3, CircularDoublyLinkedList.compute_grove_coordinates("src/year_2022/day_20/test_inputs/day_20_sample_input.txt"));
        System.out.println(CircularDoublyLinkedList.compute_grove_coordinates("src/year_2022/day_20/test_inputs/day_20_input.txt"));
    }
}
