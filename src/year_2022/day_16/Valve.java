package year_2022.day_16;

import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Valve {
    String name;
    int flowValue;
    Collection<String> neighbors;


    public static final Map<String, Valve> lookupByName = new HashMap<>();

    Valve(String name, int flowValue, Collection<String> neighbors) {
        this.name = name;
        this.flowValue = flowValue;
        this.neighbors = neighbors;
        lookupByName.put(name, this);
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
