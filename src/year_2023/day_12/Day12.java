package year_2023.day_12;

import lombok.AllArgsConstructor;
import utils.AOCScanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
public class Day12 {
    Collection<SpringRow> springRowCollection;


    static Day12 createNewDay12(String fileName) {
        AOCScanner scanner = new AOCScanner(fileName);
        Collection<SpringRow> springRowCollection = new ArrayList<>();
        scanner.forEachLine(line -> {
            String[] splitLine = line.split(" ");
            String rawReport = splitLine[0];
            List<Integer> contiguousGroupReport = Arrays.stream(splitLine[1].split(",")).map(Integer::parseInt).collect(Collectors.toList());
            springRowCollection.add(new SpringRow(rawReport, contiguousGroupReport));
        });
        return new Day12(springRowCollection);
    }

    static Day12 createDay12Part2(String fileName) {
        AOCScanner scanner = new AOCScanner(fileName);
        Collection<SpringRow> springRowCollection = new ArrayList<>();
        scanner.forEachLine(line -> {
            String[] splitLine = line.split(" ");
            String rawReport = splitLine[0];
            List<Integer> contiguousGroupReport = Arrays.stream(splitLine[1].split(",")).map(Integer::parseInt).collect(Collectors.toList());

            StringBuilder unfoldedRawReport = new StringBuilder(rawReport);
            List<Integer> unfoldedContiguousGroupReport = new ArrayList<>(contiguousGroupReport);
            for (int i=0; i<4; i++) {
                unfoldedRawReport.append("?");
                unfoldedRawReport.append(rawReport);
                unfoldedContiguousGroupReport.addAll(contiguousGroupReport);
            }

            springRowCollection.add(new SpringRow(unfoldedRawReport.toString(), unfoldedContiguousGroupReport));
        });
        return new Day12(springRowCollection);

    }

    public long sumOfArrangementCounts() {
        return springRowCollection.stream()
                .map(SpringRow::getArrangementCount)
                .reduce(0L, Math::addExact);

    }
}

@AllArgsConstructor
class SpringRow {
    String rawReport;
    List<Integer> contiguousGroupReport;

      Character reportIndex(int index) {
            return rawReport.charAt(index - 1);
        }


    public long getArrangementCount() {
        int N = rawReport.length();
        int M = contiguousGroupReport.size();

        long[] lastArray = new long[N+1];
        long[] thisArray = new long[N+1];



        /*
         At Iteration c:
          thisArray[r] = # of arrangements of the first r characters of the raw report that satisfy the first c contiguous groups
          lastArray[r] = # of arrangments of the first r characters of the raw report that satisfy the first c-1 contiguous groups
         */


        lastArray[0] = 1L;
        // Case of prefix of no #s
        for (int r=1; r<=N; r++) {
            if (springMightBeOperational(r)) {
                lastArray[r] = 1L;
            } else {
                break;
            }
        }



        for (int c = 1; c <= M; c++) {
            int thisContiguousGroupSize = contiguousGroupReport.get(c - 1);
            for (int r = 1; r <= N; r++) {
                if (springMightBeOperational(r)) {
                    thisArray[r] += thisArray[r - 1];
                }
                if (springMightBeDamaged(r)) {
                    int startOfDamagedGroup = r - thisContiguousGroupSize;
                    if (startOfDamagedGroup >= 0) {
                        if (springMightEndDamagedGroupOfAtLeastLength(r, thisContiguousGroupSize)) {
                            if (startOfDamagedGroup == 0) { // We have covered the whole row
                                thisArray[r] += (c == 1) ? 1 : 0; // check if any contiguous groups left
                            } else if (springMightBeOperational(r - thisContiguousGroupSize)) { // make sure group is not longer
                                // check the rest of the row and the rest of the contiguous groups
                                thisArray[r] += lastArray[startOfDamagedGroup - 1];
                            }
                        }
                    }
                }
            }

            lastArray = thisArray.clone();
            thisArray = new long[N+1];


        }

        return lastArray[N];
    }

    private boolean springMightEndDamagedGroupOfAtLeastLength(int r, int length) {
        return r >= length && IntStream.rangeClosed(r - length + 1, r).allMatch(this::springMightBeDamaged);
    }

    private boolean springMightBeDamaged(int r) {
        return reportIndex(r) != '.';
    }

    private boolean springMightBeOperational(int r) {
        return reportIndex(r) != '#';
    }
}


