package year_2022.day_16;

import com.google.common.collect.Sets;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day16 {

    public static String part1(String fileName) throws FileNotFoundException {
        Day16Scanner myScanner = new Day16Scanner(fileName);

        Collection<Valve> valveGraph = myScanner.scanAll();

        // We know we never want to close an open pipe, and we never want to waste time opening a pipe with flow 0
        Set<Valve> nonzeroFlowValves = valveGraph.stream()
                .filter(valve -> valve.getFlowValue() > 0)
                .collect(Collectors.toSet());

        Set<Set<Valve>> valvePowerSet = Sets.powerSet(nonzeroFlowValves);






        return valvePowerSet.toString();

    }
}
