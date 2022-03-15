package year_2019.day15.model;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public enum DroidMazeOutputInstruction {
    WALL(0L, Color.BLACK),
    SPACE(1L, Color.WHITE),
    TANK(2L, Color.GREEN);

    final long outputInstruction;
    private final Color paintColor;
    static final Map<Long, DroidMazeOutputInstruction> map = new HashMap<>();

     static {
        for (DroidMazeOutputInstruction mode : DroidMazeOutputInstruction.values()) {
            map.put(mode.outputInstruction, mode);
        }
     }

    public static DroidMazeOutputInstruction valueOf(long num) {
        return map.get(num);
    }

    DroidMazeOutputInstruction(long outputInstruction, Color paintColor) {
         this.outputInstruction = outputInstruction;
         this.paintColor = paintColor;
    }

    public Color getPaintColor() {
        return paintColor;
    }
}
