package year_2019.IntCodeComputer;

import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;

import java.util.*;

class Memory {

    Map<Long, Long> memMap = new HashMap<>();

    public Memory(long[] initial_memory) {
        for (long i=0; i<initial_memory.length; i++) {
            memMap.put(i, initial_memory[(int) i]);
        }
    }

    public long read(long addr) {
        return memMap.getOrDefault(addr, 0L);
    }

    public void write(long addr, long val) {
        memMap.put(addr, val);
    }

    public long[] toArray() {
        long mx = memMap.keySet().stream().max(Comparator.naturalOrder()).orElse(0L);
        List<Long> lst = new ArrayList<>();
        for (int i=0; i<=mx; i++) {
            lst.add(read(i));
        }
        return Longs.toArray(lst);
    }
    }