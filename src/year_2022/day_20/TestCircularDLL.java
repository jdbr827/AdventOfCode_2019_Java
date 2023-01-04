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
        assertEquals(0, dll.findNthValueFromHead(0));
        assertEquals(4, dll.findNthValueFromHead(1000));
        assertEquals(-3, dll.findNthValueFromHead(2000));
        assertEquals(2, dll.findNthValueFromHead(3000));
    }

    @Test
    public void test_set_head_to_value_happy() {
        CircularDoublyLinkedList<Integer> dll = CircularDoublyLinkedList.fromList(List.of(1, 2, -3, 3, -2, 0, 4));
        dll.setHeadToValue(0);
        assertEquals(List.of(0, 4, 1, 2, -3, 3, -2), dll.toList());
    }

     @Test
    public void test_remove_head() {
        CircularDoublyLinkedList<Integer> dll = CircularDoublyLinkedList.fromList(List.of(1, 2, -3, 3, -2, 0, 4));
        dll.removeHead();
        assertEquals(List.of(2, -3, 3, -2, 0, 4), dll.toList());
    }

}
