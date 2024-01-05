package year_2023.day_19;


import lombok.AllArgsConstructor;
import utils.AOCScanner;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@AllArgsConstructor
public class Day19 {
    Map<String, Workflow> workflowMap = new HashMap<>();
    Collection<MachinePart> machineParts;

    // Part 2 Only;
    Queue<MachinePartSpace> machinePartSpaces = new LinkedList<>();

    private static final Pattern rulePattern = Pattern.compile("([xmsa])([><])([\\d]+):([\\w]+)");
    private static final Pattern machinePattern = Pattern.compile("\\{x=([\\d]+),m=([\\d]+),a=([\\d]+),s=([\\d]+)}");

    public Day19(String fileName) {
        AOCScanner scanner = new AOCScanner(fileName);

        String line;
        while (!Objects.equals(line = scanner.nextLine(), "")) {
            String[] splitLine = line.split("\\{");
            String workflowName = splitLine[0];
            String[] splitRules = splitLine[1].split(",");
            List<Rule> rules = new ArrayList<>();
            for (int i = 0; i < splitRules.length - 1; i++) {
                Matcher m = rulePattern.matcher(splitRules[i]);
                if (m.find()) {
                    String fieldName = m.group(1);
                    boolean greaterThan = m.group(2).equals(">");
                    int testNum = Integer.parseInt(m.group(3));
                    String action = m.group(4);
                    Rule rule = new Rule(fieldName, greaterThan, testNum, action);
                    rules.add(rule);
                }
            }
            String defaultAction = splitRules[splitRules.length - 1].substring(0, splitRules[splitRules.length - 1].length() - 1);

            Workflow workflow = new Workflow(rules, defaultAction);

            workflowMap.put(workflowName, workflow);
        }

        machineParts = new ArrayList<>();
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            Matcher m = machinePattern.matcher(line);
            if (m.find()) {
                machineParts.add(new MachinePart(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)), Integer.parseInt(m.group(3)), Integer.parseInt(m.group(4))));
            }

        }

    }

    public int sumRatingNumbersOfAcceptedMachineParts() {
        return machineParts.stream()
                .filter(MachinePart::check)
                .map(MachinePart::getRatingNumber)
                .reduce(0, Math::addExact);
    }

    public long countDistinctAcceptedMachineParts() {
        MachinePartSpace mps = new MachinePartSpace(1, 4000, 1, 4000, 1, 4000, 1, 4000, "in");
        long total = 0;
        machinePartSpaces.add(mps);
        while (!machinePartSpaces.isEmpty()) {
            MachinePartSpace thisMps = machinePartSpaces.remove();

            if (thisMps.currentWorkflow.equals("A")) {
                total += thisMps.getArea();
            } else if (!thisMps.currentWorkflow.equals("R")) {
                Workflow workflow = workflowMap.get(thisMps.currentWorkflow);
                workflow.applyToMachinePartSpace(thisMps);
            }
        }
        return total;


    }


    @AllArgsConstructor
    class MachinePart {
        final int x;
        final int m;
        final int a;
        final int s;

        int getField(String c) {
            switch (c) {
                case "x":
                    return x;
                case "m":
                    return m;
                case "a":
                    return a;
                case "s":
                    return s;
            }
            return 0; // Should never get here
        }

        boolean check() {
            String workflow = "in";
            while (!(workflow.equals("R") || workflow.equals("A"))) {
                workflow = workflowMap.get(workflow).apply(this);
            }
            return workflow.equals("A");
        }

        public int getRatingNumber() {
            return x + m + a + s;
        }
    }

    @AllArgsConstructor
    class Workflow implements Function<MachinePart, String> {
        final List<Rule> rules;
        final String defaultAction;

        public String apply(MachinePart machinePart) {
            String action;
            for (Rule rule : rules) {
                if ((action = rule.applyTo(machinePart)) != null) {
                    return action;
                }
            }
            return defaultAction;
        }

        private void applyToMachinePartSpace(MachinePartSpace thisMps) {
            for (Rule rule : rules) {
                thisMps = rule.applyRule(thisMps);
                if (thisMps == null) {
                    break;
                }
            }
            if (thisMps != null) {
                thisMps.currentWorkflow = defaultAction;
                machinePartSpaces.add(thisMps);
            }
        }
    }


    class Rule {
        final String fieldName;
        final boolean greaterThan;
        final int testNum;
        final String action;

        Rule(String fieldName, boolean greaterThan, int testNum, String action) {
            this.fieldName = fieldName;
            this.greaterThan = greaterThan;
            this.testNum = testNum;
            this.action = action;
        }

        Predicate<MachinePart> condition() {

            return greaterThan
                    ? machinePart -> machinePart.getField(fieldName) > testNum
                    : machinePart -> machinePart.getField(fieldName) < testNum;

        }


        /**
         * If the condition applies to the machine part, returns the next workflow to send to, otherwise null.
         *
         * @param machinePart the machine part under test
         * @return the next workflow to send the machine part to, if applicable, otherwise null.
         */
        String applyTo(MachinePart machinePart) {
            return condition().test(machinePart)
                    ? action
                    : null;
        }


        /**
         * Splits the MachinePartSpace according to the rule.
         * If a subset of the space is obeys the rule, queues a MachinePartSpace with that subset and the appropriate workflow.
         * @param machinePartSpace the space which we are splitting
         * @return the subset of the MachinePartSpace that disobeys the rule, or null if no such part exists.
         */
        MachinePartSpace applyRule(MachinePartSpace machinePartSpace) {
            if (greaterThan) {
                if (machinePartSpace.getMin(fieldName) > testNum) { // already true
                    machinePartSpace.setCurrentWorkflow(action);
                    machinePartSpaces.add(machinePartSpace);
                    return null;
                } else if (machinePartSpace.getMax(fieldName) > testNum) { // need to split
                    MachinePartSpace newMpsTrue = machinePartSpace.copy();
                    newMpsTrue.setMin(fieldName, testNum + 1);
                    newMpsTrue.setCurrentWorkflow(action);
                    machinePartSpaces.add(newMpsTrue);

                    machinePartSpace.setMax(fieldName, testNum);
                    return machinePartSpace;
                }
                // else already false
                return machinePartSpace;
            } else {
                if (machinePartSpace.getMax(fieldName) < testNum) { // already true
                    machinePartSpace.setCurrentWorkflow(action);
                    machinePartSpaces.add(machinePartSpace);
                    return null;
                } else if (machinePartSpace.getMin(fieldName) < testNum) { // need to split
                    MachinePartSpace newMpsTrue = machinePartSpace.copy();
                    newMpsTrue.setMax(fieldName, testNum - 1);
                    newMpsTrue.setCurrentWorkflow(action);
                    machinePartSpaces.add(newMpsTrue);


                    MachinePartSpace newMpsFalse = machinePartSpace.copy();
                    newMpsFalse.setMin(fieldName, testNum);
                    return newMpsFalse;
                }
                // else already false
                return machinePartSpace;
            }
        }
    }


}
