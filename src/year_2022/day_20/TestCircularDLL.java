package year_2022.day_20;

import org.junit.jupiter.api.Test;

import java.util.List;

public class TestCircularDLL {

    @Test
    public void test_circular_dll() {

    }

    @Test
    public void test_set_head_to_value_happy() {
        CircularDoublyLinkedList<Integer> dll = CircularDoublyLinkedList.fromList(List.of(1, 2, -3, 3, -2, 0, 4));
        dll.setHeadToValue(0);

    }
}
