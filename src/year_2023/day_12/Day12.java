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

        long[][] dpMatrix = new long[N + 1][M + 1]; // Initialized to all 0


        dpMatrix[0][0] = 1L;
        // Case of prefix of no #s
        for (int r=1; r<=N; r++) {
            if (springMightBeOperational(r)) {
                dpMatrix[r][0] = 1L;
            } else {
                break;
            }
        }



        for (int c = 1; c <= M; c++) {
            int thisContiguousGroupSize = contiguousGroupReport.get(c - 1);
            for (int r = 1; r <= N; r++) {
                if (springMightBeOperational(r)) {
                    dpMatrix[r][c] += dpMatrix[r - 1][c];
                }
                if (springMightBeDamaged(r)) {
                    int delta = r - thisContiguousGroupSize;
                    if (delta >= 0) {
                        if (IntStream.rangeClosed(r - thisContiguousGroupSize + 1, r).allMatch(this::springMightBeDamaged)) {
                            if (delta == 0) { // We have covered the whole row
                                dpMatrix[r][c] += (c == 1) ? 1 : 0; // check if any contiguous groups left
                            } else if (springMightBeOperational(r - thisContiguousGroupSize)) {
                                // check the rest of the row and the rest of the contiguous groups
                                dpMatrix[r][c] += dpMatrix[r - thisContiguousGroupSize - 1][c - 1];
                            }
                        }
                    }
                }
            }


        }

        return dpMatrix[N][M];
    }

    private boolean springMightBeDamaged(int r) {
        return reportIndex(r) != '.';
    }

    private boolean springMightBeOperational(int r) {
        return reportIndex(r) != '#';
    }
}


