package year_2023.day_19;

import javafx.util.Pair;
import lombok.AllArgsConstructor;
import lombok.Data;
import utils.AOCScanner;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            for (int i=0; i<splitRules.length - 1; i++) {
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
            String defaultAction = splitRules[splitRules.length - 1].substring(0, splitRules[splitRules.length-1].length() - 1);

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
        machinePartSpaces.add(mps);
        long total = 0;
        while (!machinePartSpaces.isEmpty()) {
            MachinePartSpace thisMps = machinePartSpaces.remove();
            if (!thisMps.currentWorkflow.equals("R")) {
                if (thisMps.currentWorkflow.equals("A")) {
                    total += thisMps.getArea();
                } else {
                    Workflow workflow = workflowMap.get(thisMps.currentWorkflow);

                    boolean flag = false;
                    for (Rule rule : workflow.rules) {
                        if (!thisMps.applyRule(rule)) {
                            flag=true;
                            break;
                        }
                    }
                    if (!flag) {
                        thisMps.currentWorkflow = workflow.defaultAction;
                        machinePartSpaces.add(thisMps);
                    }
                }
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
    }


    @AllArgsConstructor
    class Rule {
        final String fieldName;
        final boolean greaterThan;
        final int testNum;
        final String action;

        Predicate<MachinePart> condition() {
            return greaterThan
                    ? machinePart -> machinePart.getField(fieldName) > testNum
                    : machinePart -> machinePart.getField(fieldName) < testNum;

        }


        /**
         * If the condition applies to the machine part, returns the next workflow to send to, otherwise null.
         * @param machinePart the machine part under test
         * @return the next workflow to send the machine part to, if applicable, otherwise null.
         */
        String applyTo(MachinePart machinePart) {
            return condition().test(machinePart)
                    ? action
                    : null;
        }
    }

    @AllArgsConstructor
    @Data
    class MachinePartSpace {
        int xMin;
        int xMax;
        int mMin;
        int mMax;
        int aMin;
        int aMax;
        int sMin;
        int sMax;
        String currentWorkflow;

        /**
         * If a subset of the space is true to the rule, adds an MPS with that subset and the workflow it should go
         * to to the queue.
         * If a subset of the space is false to the rule, edits this to be that subset
         * @param rule the rule being applies
         * @return whether or not we need to keep processing this MPS
         */
        boolean applyRule(Rule rule) {
            if (rule.greaterThan) {
                if (getMin(rule.fieldName) > rule.testNum) { // already true
                    MachinePartSpace newMps = this.copy();
                    newMps.setCurrentWorkflow(rule.action);
                    machinePartSpaces.add(newMps);
                    return false;
                } else if (getMax(rule.fieldName) > rule.testNum) { // need to split
                    MachinePartSpace newMpsTrue = this.copy();
                    newMpsTrue.setMin(rule.fieldName, rule.testNum + 1);
                    newMpsTrue.setCurrentWorkflow(rule.action);
                    machinePartSpaces.add(newMpsTrue);

                    this.setMax(rule.fieldName, rule.testNum);
                    return true;
                }
                // else already false, dead end do nothing
                return false;
            } else {
                if (getMax(rule.fieldName) < rule.testNum) { // already true
                    MachinePartSpace newMps = this.copy();
                    newMps.setCurrentWorkflow(rule.action);
                    machinePartSpaces.add(newMps);
                    return false;
                } else if (getMin(rule.fieldName) < rule.testNum) { // need to split
                    MachinePartSpace newMpsTrue = this.copy();
                    newMpsTrue.setMax(rule.fieldName, rule.testNum - 1);
                    newMpsTrue.setCurrentWorkflow(rule.action);
                    machinePartSpaces.add(newMpsTrue);


                    this.setMin(rule.fieldName, rule.testNum);
                    return true;
                }
                // else already false, dead end do nothing
                return false;
            }
        }

        private void setMax(String fieldName, int i) {
            switch (fieldName) {
                case "x":
                    setXMax(i);
                    break;
                case "m":
                    setMMax(i);
                    break;
                case "a":
                    setAMax(i);
                    break;
                case "s":
                    setSMax(i);
                    break;
            }
            // Should never get here
        }

        private void setMin(String fieldName, int i) {
             switch (fieldName) {
                case "x":
                    setXMin(i);
                    break;
                case "m":
                    setMMin(i);
                    break;
                case "a":
                    setAMin(i);
                    break;
                case "s":
                    setSMin(i);
                    break;
            }
            // Should never get here
        }

        private int getMax(String fieldName) {
            switch (fieldName) {
                case "x":
                    return xMax;
                case "m":
                    return mMax;
                case "a":
                    return aMax;
                case "s":
                    return sMax;
            }
            return 0; // Should never get here
        }

        private MachinePartSpace copy() {
            return new MachinePartSpace(xMin, xMax, mMin, mMax, aMin, aMax, sMin, sMax, currentWorkflow);
        }

        private int getMin(String fieldName) {
            switch (fieldName) {
                case "x":
                    return xMin;
                case "m":
                    return mMin;
                case "a":
                    return aMin;
                case "s":
                    return sMin;
            }
            return 0; // Should never get here
        }


        public long getArea() {
            long xDim = (long) (xMax - xMin + 1);
            long mDim = (long) (mMax- mMin + 1);
            long aDim = (long) (aMax - aMin + 1);
            long sDim = (long) (sMax - sMin + 1);
            return Math.multiplyExact(Math.multiplyExact(xDim, mDim), Math.multiplyExact(aDim, sDim));
        }
    }
}
