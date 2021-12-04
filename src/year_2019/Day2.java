package year_2019;

public class Day2 {

    static long[] real = {1,0,0,3,1,1,2,3,1,3,4,3,1,5,0,3,2,10,1,19,1,19,6,23,2,13,23,27,1,27,13,31,1,9,31,35,1,35,9,39,1,39,5,43,2,6,43,47,1,47,6,51,2,51,9,55,2,55,13,59,1,59,6,63,1,10,63,67,2,67,9,71,2,6,71,75,1,75,5,79,2,79,10,83,1,5,83,87,2,9,87,91,1,5,91,95,2,13,95,99,1,99,10,103,1,103,2,107,1,107,6,0,99,2,14,0,0};


    public static void main(String[] args) throws InterruptedException {



        /* Part 1 */
        // Solved in IntCodeTest


        /* Part 2 */

        /*
        The first four instructions only write to address 3, and address 3 is not read from again after 4th instruction,
        so we can effectively ignore those first four instructions (they don't affect what address 0 becomes)

        All the next instructions before the halt write to the address of their third param, except for the ultimate
        one which writes to address 0. Furthermore, address 1 is read in the first such instruction, and address 2 is
        not read until the second-to-last such instruction (and, from previous, neither is written to).

        So, we have:
        -> 4 instructions of effective no-ops
        -> (A lot - 2) instructions that effectively output address103 = f(address1) for some very convoluted f
        -> address107 = address103 + address2
        -> address0 = address107 + address6

        Considering that address6=2 (recall the "first" address is address0), and substituting, we have

        address0 = f(address1) + address2 + 2, for some known and convoluted function f.

        For this problem, then, we need address1 and address 2 s.t.
            19690720 = f(address1) + address2 + 2
        ==> 19690718 = f(address1) + address2

        Now consider that, because all of the instructions are either adds or multiples, and all the numbers are
        positive integers (proven by a quick scan), f(address1) is strictly increasing.

        Additionally, note that address2 has to be some number between 0 and 116 (the last address of the input) so that
        we can execute the first instruction (1, 0, 0, 3) without going out of bounds

        Finally, recall that we can compute f(address1) for any value of address1 simply by running the program and
        looking at address103.

        This suggests the following algorithm:
        */

        long a1 = 0;
        long a2;
        do {
            a1 += 1;
            real[1] = a1;
        } while ((a2 = 19690718 - IntCode.createAndRun(real).getMemory()[103]) >= 116);
        System.out.println((a1 * 100) + a2 == 9074);
    }
}
