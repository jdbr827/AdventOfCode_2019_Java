package year_2022.day_20;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@AllArgsConstructor
public class CircularDoublyLinkedList<T> {
    @NotNull CircularDoublyLinkedList<T> prev = this;
    @NotNull CircularDoublyLinkedList<T> next = this;
    @NotNull T value;

    public void setNext(T newNext) {
        CircularDoublyLinkedList<T> newNextNode = new CircularDoublyLinkedList<>(newNext);
        setNextNode(newNextNode);
    }

    public void setNextNode(CircularDoublyLinkedList<T> newNextNode) {
        this.next.prev = newNextNode;
        newNextNode.next = this.next;
        this.next = newNextNode;
        this.next.prev = this;
    }

    public void setPrevNode(CircularDoublyLinkedList<T> newPrevNode) {
        prev.setNextNode(newPrevNode);
    }

    public void setPrev(T newPrev) {
        CircularDoublyLinkedList<T> prev = this.prev;
        prev.setNext(newPrev);
    }

    public boolean checkConsistent() {
        CircularDoublyLinkedList<T> ptr = this;
        do {
            if (ptr.next.prev != ptr) {
                return false;
            }
        } while ((ptr = ptr.next) != this);
        return true;
    };

    public void swapWithNext() {
        this.next.prev = this.prev;
        this.prev.next = this.next;
        this.next.next.prev = this;
        this.next = this.next.next;
        this.prev = this.prev.next;
        this.prev.next = this;
    }

    public void swapWithPrev() {
        this.prev.swapWithNext();
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[");
        CircularDoublyLinkedList<T> ptr = this;
        s.append(ptr.value);
        while ((ptr = ptr.next) != this) {
            s.append(", ");
            s.append(ptr.value);
        }
        s.append("]");
        return s.toString();
    };

    public CircularDoublyLinkedList<T> setHeadToNode(CircularDoublyLinkedList<T> node) {
        return node;
    }


    static <T> CircularDoublyLinkedList<T> fromList(List<T> lst) {
        List<CircularDoublyLinkedList<T>> dllNodes = lst.stream().map(CircularDoublyLinkedList::new).collect(Collectors.toList());
        return fromDllNodes(dllNodes);
    }

    public static <T> CircularDoublyLinkedList<T> fromDllNodes(List<CircularDoublyLinkedList<T>> dllNodes) {
        CircularDoublyLinkedList<T> dll = dllNodes.get(0);
        for (int i = 1; i < dllNodes.size(); i++) {
            dll.setPrevNode(dllNodes.get(i));
        }
        return dll;
    }

    public void moveForwardN(int N) {
        if (N >= 0) {
            for (int i = 0; i < N; i++) {
                swapWithNext();
            }
        } else {
            for (int i=0; i>N; i--){
                swapWithPrev();
            }
        }
    }

    public CircularDoublyLinkedList<T> setHeadToValue(T val) {
        if (val == this.value) {
            return this;
        }
        for (CircularDoublyLinkedList<T> ptr = this.next; ptr!=this; ptr=ptr.next) {
            if (ptr.value == val) {
                return ptr;
            }
        }
        System.out.println("Could not find value " + val + " in Circular DLL " + this);
        return new CircularDoublyLinkedList<T>(val);
    }

    public static int mixList(List<Integer> inList) {
         List<CircularDoublyLinkedList<Integer>> dllNodes = inList.stream().map(CircularDoublyLinkedList::new).collect(Collectors.toList());
        CircularDoublyLinkedList<Integer> dll = fromDllNodes(dllNodes);
        for (CircularDoublyLinkedList<Integer> node : dllNodes) {
            dll = dll.setHeadToNode(node);
            //System.out.println(dll);
            dll.moveForwardN(node.value);
            //System.out.println(dll);
        }

        dll = dll.setHeadToValue(0);
        System.out.println(dll.toString());
        //return " " + dll.findNthElementFromHead(1000) + " " + dll.findNthElementFromHead(2000) + " " + dll.findNthElementFromHead(3000);
        return dll.findNthElementFromHead(1000) + dll.findNthElementFromHead(2000) + dll.findNthElementFromHead(3000);
    }

    public static int compute_grove_coordinates(String fileName) {
        List<Integer> lst = new Day20Scanner(fileName).createListToMix();
        return mixList(lst);
    }

    public T findNthElementFromHead(int N) {
        CircularDoublyLinkedList<T> ptr = this;
        for (int i=0; i<N; i++) {
            ptr = ptr.next;
        }
        return ptr.value;
    }

}


