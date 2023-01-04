package year_2022.day_20;

import java.util.List;
import java.util.stream.Collectors;

public class Day20 {
    public static int mixList(List<Integer> inList) {
        List<CircularDoublyLinkedListNode<Integer>> dllNodes = inList.stream().map(CircularDoublyLinkedListNode::new).collect(Collectors.toList());
        CircularDoublyLinkedList<Integer> dll = CircularDoublyLinkedList.fromDllNodes(dllNodes);
        for (CircularDoublyLinkedListNode<Integer> node : dllNodes) {
            dll.setHeadToNode(node);
            //System.out.println(dll);
            dll.moveForwardN(node.value);
            //System.out.println(dll);
        }

        dll.setHeadToValue(0);
        System.out.println(dll.toString());
        //return " " + dll.findNthElementFromHead(1000) + " " + dll.findNthElementFromHead(2000) + " " + dll.findNthElementFromHead(3000);
        return dll.findNthElementFromHead(1000) + dll.findNthElementFromHead(2000) + dll.findNthElementFromHead(3000);
    }

    public static int compute_grove_coordinates(String fileName) {
        List<Integer> lst = new Day20Scanner(fileName).createListToMix();
        return mixList(lst);
    }
}
