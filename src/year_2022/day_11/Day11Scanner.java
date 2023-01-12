package year_2022.day_11;

import utils.AOCScanner;
import utils.ReadIn;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day11Scanner extends AOCScanner {

    static final Pattern monkeyPattern = Pattern.compile("Monkey ([0-9]*):");
    static final Pattern startingItemsPattern = Pattern.compile("Starting items: ([[0-9]*,[ ]]*)");
    static final Pattern operationPattern = Pattern.compile("Operation: new = old ([\\+\\-\\*]) (old|[0-9]*)");
    static final Pattern testPattern = Pattern.compile("Test: divisible by ([0-9]*)");
    static final Pattern testTruePattern = Pattern.compile("If true: throw to monkey ([0-9]*)");
    static final Pattern testFalsePattern = Pattern.compile("If false: throw to monkey ([0-9]*)");

    int versionNum;

    public KeepAwaySimulation scanMonkeys() {
         while (scanner.hasNextLine()) {
            monkeys.add(scanNextMonkey());
        }
        return new KeepAwaySimulation(monkeys);
    }


    public static final Map<Character, BiFunction<Long, Long, Long>> operationMap = Map.of(
            '+', Math::addExact,
            '-', Math::subtractExact,
            '*', Math::multiplyExact,
            '/', Math::floorDiv
    );

    List<Monkey> monkeys = new ArrayList<>();

    private Monkey scanNextMonkey() {
        Matcher m = monkeyPattern.matcher(scanner.nextLine());
        ReadIn.findOrElseThrow(m, "Could not match ranges pattern");
        //System.out.println(m.group(0));

        Matcher m2 = startingItemsPattern.matcher(scanner.nextLine());
        ReadIn.findOrElseThrow(m2, "Could not match starting items pattern");

        Queue<Item> worryQ = Stream.of(m2.group(1).split(", "))
                .map(Long::parseLong)
                .map(l -> Item.newItem(l, versionNum))
                .collect(Collectors.toCollection(LinkedList::new));

        //System.out.println(worryList.toString());

        Matcher m3 = operationPattern.matcher(scanner.nextLine());
        ReadIn.findOrElseThrow(m3, "Could not match operation pattern");
        //System.out.println(m3.group(0));

        Matcher m4 = testPattern.matcher(scanner.nextLine());
        ReadIn.findOrElseThrow(m4, "Could not match test pattern");
        //System.out.println(m4.group(0));

        Matcher m5 = testTruePattern.matcher(scanner.nextLine());
        ReadIn.findOrElseThrow(m5, "Could not match test true pattern");
        //System.out.println(m5.group(0));

        Matcher m6 = testFalsePattern.matcher(scanner.nextLine());
        ReadIn.findOrElseThrow(m6, "Could not match test false pattern");
        //System.out.println(m6.group(0));

        if (scanner.hasNextLine()) {
            scanner.nextLine(); // blank
        }

        Function<Long, Long> inspectFunc = (Long old) -> operationMap.get(m3.group(1).charAt(0)).apply(old, m3.group(2).equals("old") ? old : Long.parseLong(m3.group(2)));

        Consumer<Item> inspectConsumer = (Item item) -> item.setWorryLevel(inspectFunc.apply(item.getWorryLevel()));
        Function<Item, Monkey> throwFunc = (Item item) -> (item.getWorryLevel() % Integer.parseInt(m4.group(1))) == 0 ? monkeys.get(Integer.parseInt(m5.group(1))) : monkeys.get(Integer.parseInt(m6.group(1)));


        return new Monkey(worryQ, inspectConsumer, throwFunc);


    }

    public Day11Scanner(String fileName, int versionNum) {
        super(fileName);
        this.versionNum = versionNum;
    }
}
