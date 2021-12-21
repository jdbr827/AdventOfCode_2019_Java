package year_2019.day15;

import java.util.HashMap;
import java.util.Map;

public enum DroidMazeOutputInstruction {
    WALL(0L),
    SPACE(1L),
    TANK(2L);

    final long outputInstruction;
    static final Map<Long, DroidMazeOutputInstruction> map = new HashMap<>();

     static {
        for (DroidMazeOutputInstruction mode : DroidMazeOutputInstruction.values()) {
            map.put(mode.outputInstruction, mode);
        }
     }

    public static DroidMazeOutputInstruction valueOf(long num) {
        return map.get(num);
    }

    DroidMazeOutputInstruction(long outputInstruction) {
        this.outputInstruction = outputInstruction;
    }
}
