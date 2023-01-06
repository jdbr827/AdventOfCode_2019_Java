package year_2022.day_20;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day20 {
    final static Integer DECRYPTION_KEY = 811589153;

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
        List<CircularDoublyLinkedListNode<Long>> dllNodes = lst.stream().map(CircularDoublyLinkedListNode::new).collect(Collectors.toList());
        CircularDoublyLinkedList<Long> dll = mixList(CircularDoublyLinkedList.fromDllNodes(dllNodes), dllNodes, (v) -> v);
        int zeroI = lst.indexOf(0L);
        dll.setHeadToNode(dllNodes.get(zeroI));
        return computeGroveCoordinates(dll);
    }

    public static long part2(String fileName) {
        System.out.println((-5) % 3);
        List<Long> lst = new Day20Scanner(fileName).createListToMix();
        // (a*b)%n = ((a%n) * (b%n)) % n
        int zeroI = lst.indexOf(0L);

        List<CircularDoublyLinkedListNode<Long>> dllNodes = lst.stream()
                .map(v1 -> (long) v1 * DECRYPTION_KEY)
                .map(CircularDoublyLinkedListNode::new)
                .collect(Collectors.toList());

        CircularDoublyLinkedList<Long> dll = CircularDoublyLinkedList.fromDllNodes(dllNodes);

        for (int i=0 ; i<10; i++) {
            mixList(dll, dllNodes, (v) -> (long) v);
        }

        dll.setHeadToNode(dllNodes.get(zeroI));
        return computeGroveCoordinates(dll);
    }

    private static long computeGroveCoordinates(CircularDoublyLinkedList<Long> dll) {
        return dll.findNthValueFromHead(1000) + dll.findNthValueFromHead(2000) + dll.findNthValueFromHead(3000);
    }
}
