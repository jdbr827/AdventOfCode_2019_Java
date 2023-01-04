package year_2022.day_20;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class CircularDoublyLinkedList<T> {
    CircularDoublyLinkedListNode<T> head;
    int size;

    CircularDoublyLinkedList(CircularDoublyLinkedListNode<T> head) {
        this.head = head;
        CircularDoublyLinkedListNode<T> ptr;
        size = 1;
        for (ptr = head.next; ptr != head; ptr=ptr.next) {
            size++;
        }
    }

    /**
     * Removes the head node and sets the head at the next node
     * @return the next node
     */
    public void removeHead() {
        // TODO empty case
        head.next.prev = head.prev;
        head.prev.next = head.next;
        head = head.next;
        size--;
    }




    public T findNthValueFromHead(int N) {
        CircularDoublyLinkedListNode<T> ptr = findNthElementFromHead(N);
        return ptr.value;
    }

    private CircularDoublyLinkedListNode<T> findNthElementFromHead(int N) {
        CircularDoublyLinkedListNode<T> ptr = head;
        for (int i = 0; i<N % size; i++) {
            ptr = ptr.next;
        }
        return ptr;
    }

    public List<T> toList() {
        // TODO empty case
        List<T> lst = new ArrayList<>();
        CircularDoublyLinkedListNode<T> ptr;
        lst.add(head.value);
        for (ptr=head.next; ptr != head; ptr=ptr.next) {
            lst.add(ptr.value);
        }
        return lst;
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


    public void moveForwardN(long N) {
        N = N % (size-1);
        if (N >= 0) {
            for (int i = 0; i < N; i++) {
                head.swapWithNext();
            }
        } else {
            for (int i=0; i>N; i--){
                head.swapWithPrev();
            }
        }
    }

    public void setHeadToValue(T value) {
       setHeadToNode(findElementWithValue(value));
    }

    public CircularDoublyLinkedListNode<T> findElementWithValue(T value) {
        CircularDoublyLinkedListNode<T> originalHead = head;
        CircularDoublyLinkedListNode<T> ptr;
        for (ptr = originalHead.next; ptr != originalHead; ptr= ptr.next) {
            if (ptr.value == value) {
                return ptr;
            }
        }
        // Spinny!
        System.out.println("Could not find value " + value + " in Circular DLL " + this);
        return null;
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
