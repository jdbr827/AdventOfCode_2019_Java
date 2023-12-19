package year_2023.day_12;

import lombok.AllArgsConstructor;
import utils.AOCScanner;
import year_2022.day_12.Matrix2D;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Day12 {
    Collection<SpringRow> springRowCollection;

    public Day12(String fileName) {
        AOCScanner scanner = new AOCScanner(fileName);
        springRowCollection = new ArrayList<>();
        scanner.forEachLine(line -> {
            String[] splitLine = line.split(" ");
            String rawReport = splitLine[0];
            List<Integer> contiguousGroupReport = Arrays.stream(splitLine[1].split(",")).map(Integer::parseInt).collect(Collectors.toList());
            springRowCollection.add(new SpringRow(rawReport, contiguousGroupReport));
        });
    }

    public int sumOfArrangementCounts() {
        List<Integer> arrangementCounts = springRowCollection.stream().map(SpringRow::getArrangementCount).collect(Collectors.toList());
        return arrangementCounts.stream().reduce(0, Math::addExact);
    }
}

@AllArgsConstructor
class SpringRow {
    String rawReport;
    List<Integer> contiguousGroupReport;

      Character getRawReportCharAt1Index(int index) {
            return rawReport.charAt(index - 1);
        }


    public int getArrangementCount() {
        int N = rawReport.length();
        int M = contiguousGroupReport.size();
        Integer[][] dpMatrix = new Integer[N + 1][M + 1];

        for (int r = 0; r <= N; r++) {
            for (int c = 0; c <= M; c++)
                dpMatrix[r][c] = 0;
        }

        dpMatrix[0][0] = 1;

        for (int r=1; r<=N; r++) {
            if (getRawReportCharAt1Index(r) != '#') {
                dpMatrix[r][0] = 1;
            } else {
                break;
            }
        }



        for (int c = 1; c <= M; c++) {
            int thisContiguousGroupSize = contiguousGroupReport.get(c - 1);
            for (int r = 1; r <= N; r++) {
                if (getRawReportCharAt1Index(r) != '#') {
                    dpMatrix[r][c] += dpMatrix[r - 1][c];
                }
                if (getRawReportCharAt1Index(r) != '.') {
                    int delta = r - thisContiguousGroupSize;
                    if (delta >= 0) {
                        boolean candidateFlag = true;
                        for (int i = 0; i < thisContiguousGroupSize; i++) {
                            if (getRawReportCharAt1Index(r - i) == '.') {
                                candidateFlag = false;
                                break;
                            }
                        }
                        if (candidateFlag) {
                            if (delta == 0) {
                                dpMatrix[r][c] += dpMatrix[0][c - 1];
                            } else if (getRawReportCharAt1Index(r - thisContiguousGroupSize) != '#') {
                                dpMatrix[r][c] += dpMatrix[r - thisContiguousGroupSize - 1][c - 1];
                            }
                        }
                    }
                }
            }


        }

        return dpMatrix[N][M];
    }
}


