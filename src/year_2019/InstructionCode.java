package year_2019;

import java.util.HashMap;
import java.util.Map;

enum InstructionCode {
        ADD_INSTRUCTION_CODE(1),
        MULTIPLY_INSTRUCTION_CODE(2),
        INPUT_INSTRUCTION_CODE(3),
        OUTPUT_INSTRUCTION_CODE(4),
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
