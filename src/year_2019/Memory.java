package year_2019;

import com.google.common.primitives.Ints;

import java.util.*;

class Memory {

    Map<Integer, Integer> memMap = new HashMap<>();

    public Memory(int[] initial_memory) {
        for (int i=0; i<initial_memory.length; i++) {
            memMap.put(i, initial_memory[i]);
        }
    }

    public int read(int addr) {
        return memMap.getOrDefault(addr, 0);
    }

    public void write(int addr, int val) {
        memMap.put(addr, val);
    }

    public int[] toArray() {
        int mx = memMap.keySet().stream().max(Comparator.naturalOrder()).orElse(0);
        List<Integer> lst = new ArrayList<>();
        for (int i=0; i<=mx; i++) {
            lst.add(read(i));
        }
        return Ints.toArray(lst);
    }
    }