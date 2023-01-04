package year_2022.day_20;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day20 {
    final static Integer DECRYPTION_KEY = 811589153;
    @NotNull

    private static CircularDoublyLinkedList<Long> mixList(List<Long> inList) {
        List<CircularDoublyLinkedListNode<Long>> dllNodes = inList.stream().map(CircularDoublyLinkedListNode::new).collect(Collectors.toList());
        CircularDoublyLinkedList<Long> dll = CircularDoublyLinkedList.fromDllNodes(dllNodes);
        return mixList(dll, dllNodes, (v) -> (long) v);
    }

     private static <T> CircularDoublyLinkedList<T> mixList(
             CircularDoublyLinkedList<T> listToMix,
             List<CircularDoublyLinkedListNode<T>> mixingOrder,
             Function<T, Long> mixNumberFunction
             ) {
        for (CircularDoublyLinkedListNode<T> node : mixingOrder) {
            listToMix.setHeadToNode(node);
            listToMix.moveForwardN(mixNumberFunction.apply(node.value));
        }
        return listToMix;
    }

    public static long part1(String fileName) {
        List<Long> lst = new Day20Scanner(fileName).createListToMix();
        CircularDoublyLinkedList<Long> dll = mixList(lst);

        dll.setHeadToValue(0L);
        return computeGroveCoordinates(dll);
    }

    public static long part2(String fileName) {
        System.out.println((-5) % 3);
        List<Long> lst = new Day20Scanner(fileName).createListToMix();
        // (a*b)%n = ((a%n) * (b%n)) % n
        int n = lst.size();
        int zeroI = lst.indexOf(0L);
        List<Long> longList = lst.stream()
                .map(v -> (long) v * DECRYPTION_KEY)
                .collect(Collectors.toList());
        List<CircularDoublyLinkedListNode<Long>> dllNodes = longList.stream().map(CircularDoublyLinkedListNode::new).collect(Collectors.toList());
        CircularDoublyLinkedList<Long> dll = CircularDoublyLinkedList.fromDllNodes(dllNodes);
        for (int i=0 ; i<10; i++) {
            dll = mixList(dll, dllNodes, (v) -> (long) v);
        }
        dll.setHeadToNode(dllNodes.get(zeroI));
        System.out.println(dll);
        return computeGroveCoordinates(dll);
    }

    private static long computeGroveCoordinates(CircularDoublyLinkedList<Long> dll) {
        return dll.findNthValueFromHead(1000) + dll.findNthValueFromHead(2000) + dll.findNthValueFromHead(3000);
    }


    public static long compute_grove_coordinates_2(String s) {
        return 0L;
    }
}
