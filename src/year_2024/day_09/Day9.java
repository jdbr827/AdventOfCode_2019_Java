package year_2024.day_09;

import lombok.AllArgsConstructor;
import lombok.ToString;
import utils.AOCScanner;


import java.util.*;

public class Day9 {
    List<Integer> initialFileSystem;


    @AllArgsConstructor
    @ToString
    static class FileInfo {
        int id;
        int size;
        int start;

        public long getCheckSum() {
            long tot = 0;
            //System.out.println(id + " " + start);
            for (int idx=start; idx<start+size; idx++) {
                tot += (long) id * idx;
            }
            return tot;
        }
    }


    public Day9(String inputFilename) {
        // Idea : A queue of free space blocks, a stack of taken blocks, ends when stack is bigger than queue.

        AOCScanner scanner = new AOCScanner(inputFilename);

        char[] chars = scanner.nextLine().toCharArray();

        initialFileSystem = new ArrayList<>();
        for (char c : chars) {
            initialFileSystem.add(Integer.parseInt(String.valueOf(c)));
        }

    }

    public long resultingFilesystemChecksum() {
        Deque<FileInfo> deque = new LinkedList<>();
        int totalFileSystemSize = initialFileSystem.getFirst();
        deque.add(new FileInfo(0, totalFileSystemSize, 0));
        for (int id=1; id*2 < initialFileSystem.size(); id++) {
            int freeSpaceBefore = initialFileSystem.get(id*2 - 1);
            int size = initialFileSystem.get(id*2);
            deque.add(new FileInfo(id, size, totalFileSystemSize + freeSpaceBefore));
            totalFileSystemSize += freeSpaceBefore + size;
        }

        //System.out.println(totalFileSystemSize);

        long total = 0L;
        int i = 0;
        while (!deque.isEmpty()) {
            if (deque.getFirst().start == i) {
                total += deque.getFirst().getCheckSum();
                i += deque.getFirst().size;
                deque.removeFirst();
            } else {
                total += (long) i * deque.getLast().id;
                deque.getLast().size -= 1;
                if (deque.getLast().size == 0) {
                    deque.removeLast();
                }
                i++;
            }
        }
        return total;
    }

    @AllArgsConstructor
    static class AvailableBlock {
        int size;
        int start;
    }

    public long resultingFilesystemChecksumWithoutFragmentation() {
        List<FileInfo> deque = new ArrayList<>();
        List<AvailableBlock> availableBlocks = new ArrayList<>();
        int totalFileSystemSize = initialFileSystem.getFirst();
        deque.add(new FileInfo(0, totalFileSystemSize, 0));
        for (int id=1; id*2 < initialFileSystem.size(); id++) {
            int freeSpaceBefore = initialFileSystem.get(id*2 - 1);
            int size = initialFileSystem.get(id*2);
            deque.add(new FileInfo(id, size, totalFileSystemSize + freeSpaceBefore));
            availableBlocks.add(new AvailableBlock(freeSpaceBefore, totalFileSystemSize));
            totalFileSystemSize += freeSpaceBefore + size;
        }

        long total = 0L;
        while (!deque.isEmpty()) {
            for (AvailableBlock block : availableBlocks) {
                if (block.start > deque.getLast().start) {
                    break;
                }
                if (block.size >= deque.getLast().size) {
                    deque.getLast().start = block.start;
                    block.start += deque.getLast().size;
                    block.size -= deque.getLast().size;
                    break;
                }
            }
            total += deque.getLast().getCheckSum();
            deque.removeLast();
        }
        return total;
    }
}
