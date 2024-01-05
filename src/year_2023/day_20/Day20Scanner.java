package year_2023.day_20;

import utils.AOCScanner;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day20Scanner {

    private static final Pattern rulePattern = Pattern.compile("([%&]?)([\\w]+) -> ([\\w]+], ]*[\\w]+])");

    public static Day20 scan(String fileName) {
        AOCScanner scanner = new AOCScanner(fileName);
        Queue<Day20.Message> messageQueue = new LinkedList<>();

        Map<String, CommunicationModule> moduleLibrary = new HashMap<>();
        moduleLibrary.put("button", new ButtonModule(messageQueue));



        scanner.forEachLine(line -> {
            Matcher m = rulePattern.matcher(line);
                if (m.find()) {
                    System.out.println(m.group(0));
                    String moduleType = m.group(1);
                    System.out.println(moduleType);
                    String moduleName = m.group(2);
                    System.out.println(moduleName);
                    String[] destinationModules = m.group(3).split(", ");
                    for (String destinationModule : destinationModules) {
                        System.out.println(destinationModule);
                    }

                    CommunicationModule newModule = createCommunicationModule(moduleType, moduleName, destinationModules, messageQueue);
                    moduleLibrary.put(moduleName, newModule);

                    System.out.println(m.groupCount());
                }
        });

        return new Day20(moduleLibrary);

    }

    private static CommunicationModule createCommunicationModule(String typeCode, String moduleName, String[] destinationModules, Queue<Day20.Message> messageQueue) {
        if (typeCode.equals("%")) {
                return new FlipFlopModule(destinationModules, messageQueue);
        } else if (typeCode.equals("&")) {
            return new ConjunctionModule(destinationModules, messageQueue);
        } else if (moduleName.equals("broadcaster")) {
            return new BroadcasterModule(destinationModules, messageQueue);
        }
        throw new Error ("Do not recognize communication module " + typeCode + moduleName);
    }

}
