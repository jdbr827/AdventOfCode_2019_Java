package year_2019;

import java.util.HashMap;
import java.util.Map;

enum ParameterMode {
    POSITION_MODE(0),
    IMMEDIATE_MODE(1),
    RELATIVE_MODE(2);

    private final static Map<Long, ParameterMode> map = new HashMap<>();
    private final long pCode;

    ParameterMode(long pCode) {
        this.pCode = pCode;
    }

     static {
        for (ParameterMode mode : ParameterMode.values()) {
            map.put(mode.pCode, mode);
        }
    }

    public static ParameterMode valueOf(long pCode) {
        return map.get(pCode);
    }

    /**
     * Returns the parameter mode of the paramNum^th parameter given the opcode
     * @param opcode the opcode for the instruction
     * @param paramNum denotes which parameter we are looking at [1-indexed]
     * @return The parameter mode for the operation
     */
    public static ParameterMode getParameterMode(long opcode, int paramNum) {
        return ParameterMode.valueOf((opcode / (long) Math.pow(10, paramNum+1)) % 10);
    }

}
