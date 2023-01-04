package year_2022.day_20;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCircularDLL {

    @Test
    public void test_circular_dll() {

    }

    @Test
    public void test_find_Nth_element_from_head() {
        CircularDoublyLinkedList<Integer> dll = CircularDoublyLinkedList.fromList(List.of(0, 3, -2, 1, 2, -3, 4));
        assertEquals(0, dll.findNthElementFromHead(0));
        assertEquals(4, dll.findNthElementFromHead(1000));
        assertEquals(-3, dll.findNthElementFromHead(2000));
        assertEquals(2, dll.findNthElementFromHead(3000));
    }

    @Test
    public void test_set_head_to_value_happy() {
        CircularDoublyLinkedListNode<Integer> dll = CircularDoublyLinkedListNode.fromList(List.of(1, 2, -3, 3, -2, 0, 4));
        dll.setHeadToValue(0);

    }
}
