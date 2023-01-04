package year_2022.day_20;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
public class CircularDoublyLinkedList<T> {
    @NotNull CircularDoublyLinkedList<T> prev = this;
    @NotNull CircularDoublyLinkedList<T> next = this;
    @NotNull T value;

    public void setNext(T newNext) {
        CircularDoublyLinkedList<T> newNextNode = new CircularDoublyLinkedList<>(newNext);
        this.next.prev = newNextNode;
        newNextNode.next = this.next;
        this.next = newNextNode;
        this.next.prev = this;
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


    public static void main(String[] args) {
        CircularDoublyLinkedList<Integer> dll = new CircularDoublyLinkedList<>(7);
        System.out.println(dll.checkConsistent());
        System.out.println(dll.toString());
        dll.setNext(8);
        dll.setPrev(-5);
        System.out.println(dll.toString());
        dll.setPrev(-2);
        System.out.println(dll.toString());
        dll.swapWithNext();
        System.out.println(dll.toString());
        System.out.println(dll.checkConsistent());


    }

}


