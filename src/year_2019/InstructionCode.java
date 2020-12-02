package year_2019;

import java.util.HashMap;
import java.util.Map;

enum InstructionCode {
        ADD(1),
        MULTIPLY(2),
        INPUT(3),
        OUTPUT(4),
        JUMP_IF_TRUE(5),
        JUMP_IF_FALSE(6),
        LESS_THAN(7),
        EQUALS(8),
        HALT_INSTRUCTION_CODE(99);

        private static final Map<Integer, InstructionCode> map = new HashMap<>();
        private final int opcodeNum;

        InstructionCode(int opcodeNum) {
            this.opcodeNum = opcodeNum;
        }

        static {
            for (InstructionCode iCode : InstructionCode.values()) {
                map.put(iCode.opcodeNum, iCode);
            }
        }

        public static InstructionCode valueOf(int iCode) {
            return map.get(iCode);
        }

    }
