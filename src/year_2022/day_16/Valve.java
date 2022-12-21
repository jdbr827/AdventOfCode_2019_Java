package year_2022.day_16;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Valve {
    private final @Getter String name;
    private final @Getter int flowValue;
    private final Collection<String> neighbors;


    public static final Map<String, Valve> lookupByName = new HashMap<>();

    Valve(String name, int flowValue, Collection<String> neighbors) {
        this.name = name;
        this.flowValue = flowValue;
        this.neighbors = neighbors;
        lookupByName.put(name, this);
    }

    static Valve createErrorValve(String sName) {
        return new Valve("WHOOPS " + sName + " not recognized valve!", -1, null);

    }

    public Collection<Valve> getNeighbors() {
        return neighbors.stream().map((sName) -> lookupByName.getOrDefault(sName, createErrorValve(sName))).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Valve{" +
                "name='" + name + '\'' +
                ", flowValue=" + flowValue +
                ", neighbors=" + neighbors +
                '}';
    }
}
