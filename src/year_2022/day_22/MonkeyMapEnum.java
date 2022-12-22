package year_2022.day_22;

import java.util.HashMap;
import java.util.Map;

public enum MonkeyMapEnum {
    OPEN_SPACE('.'),
    WALL('#'),
    WARP_ZONE(' ');

    final char diagramNotation;

    MonkeyMapEnum(char diagramNotation) {
        this.diagramNotation = diagramNotation;
    }

    private static final Map<Character, MonkeyMapEnum> map = new HashMap<>();

    static {
        for (MonkeyMapEnum val : MonkeyMapEnum.values()) {
            map.put(val.diagramNotation, val);
        }
     }

     public static MonkeyMapEnum of(Character c) {
        return map.get(c);
     }
}
