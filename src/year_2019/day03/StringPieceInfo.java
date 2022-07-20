package year_2019.day03;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static utils.ReadIn.findOrElseThrow;

class StringPieceInfo {
    char direction;
    int magnitude;

    private static final Pattern threadSegmentPattern = Pattern.compile("([RLUD])([0-9]*)");

    StringPieceInfo(String instruction) {
        Matcher m = threadSegmentPattern.matcher(instruction);
        findOrElseThrow(m, "Could not match pattern to " + instruction);
        direction = m.group(1).charAt(0);
        magnitude = Integer.parseInt(m.group(2));
    }

    public static List<StringPieceInfo> makeStringThreadInstructions(String thread) {
        return Arrays.stream(thread.split(","))
                .map(StringPieceInfo::new)
                .collect(Collectors.toList());
    }


}
