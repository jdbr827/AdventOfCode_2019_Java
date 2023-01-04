package year_2022.day_20;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@AllArgsConstructor
public class CircularDoublyLinkedListNode<T> {
    @NotNull CircularDoublyLinkedListNode<T> prev = this;
    @NotNull CircularDoublyLinkedListNode<T> next = this;
    @NotNull T value;

    public void setNextNode(CircularDoublyLinkedListNode<T> newNextNode) {
        this.next.prev = newNextNode;
        newNextNode.next = this.next;
        this.next = newNextNode;
        this.next.prev = this;
    }

    public void setPrevNode(CircularDoublyLinkedListNode<T> newPrevNode) {
        prev.setNextNode(newPrevNode);
    }


    public boolean checkConsistent() {
        CircularDoublyLinkedListNode<T> ptr = this;
        do {
            if (ptr.next.prev != ptr) {
                return false;
            }
            if (ptr.prev.next != ptr) {
                return false;
            }
        } while ((ptr = ptr.next) != this);
        return true;
    };


    /**
     * Removes the current node and sets the head at the next node
     * @return the next node
     */
    public CircularDoublyLinkedListNode<T> removeThisNode() {
        this.next.prev = this.prev;
        this.prev.next = this.next;
        return this.next;
    }

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

}


