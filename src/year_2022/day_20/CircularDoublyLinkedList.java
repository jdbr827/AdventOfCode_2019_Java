package year_2022.day_20;

import java.util.List;
import java.util.stream.Collectors;


public class CircularDoublyLinkedList<T> {
    CircularDoublyLinkedListNode<T> head;

    CircularDoublyLinkedList(CircularDoublyLinkedListNode<T> head) {
        this.head = head;
    }

    public T findNthElementFromHead(int N) {
        CircularDoublyLinkedListNode<T> ptr = head;
        for (int i=0; i<N; i++) {
            ptr = ptr.next;
        }
        return ptr.value;
    }

    static <T> CircularDoublyLinkedList<T> fromList(List<T> lst) {
        List<CircularDoublyLinkedListNode<T>> dllNodes = lst.stream().map(CircularDoublyLinkedListNode::new).collect(Collectors.toList());
        return fromDllNodes(dllNodes);
    }

    public static <T> CircularDoublyLinkedList<T> fromDllNodes(List<CircularDoublyLinkedListNode<T>> dllNodes) {
        CircularDoublyLinkedListNode<T> head = dllNodes.get(0);
        for (int i = 1; i < dllNodes.size(); i++) {
            head.setPrevNode(dllNodes.get(i));
        }
        return new CircularDoublyLinkedList<>(head);
    }

    public void setHeadToNode(CircularDoublyLinkedListNode<T> node) {
        this.head = node; // I hope its in the underlying LL!
    }

    public void moveForwardN(int N) {
        head.moveForwardN(N);
    }

    public void setHeadToValue(T value) {
        CircularDoublyLinkedListNode<T> originalHead = head;
        CircularDoublyLinkedListNode<T> ptr;
        for (ptr = originalHead.next; ptr != originalHead; ptr= ptr.next) {
            if (ptr.value == value) {
                setHeadToNode(ptr);
                return;
            }
        }
        // Spinny!
        System.out.println("Could not find value " + value + " in Circular DLL " + this);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[");
        CircularDoublyLinkedListNode<T> ptr = head;
        s.append(ptr.value);
        while ((ptr = ptr.next) != head) {
            s.append(", ");
            s.append(ptr.value);
        }
        s.append("]");
        return s.toString();
    };
}
