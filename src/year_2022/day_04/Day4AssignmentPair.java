package year_2022.day_04;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Day4AssignmentPair {
    int A_low;
    int A_high;
    int B_low;
    int B_high;

    public boolean completelyContains() {
        return ((A_low <= B_low && B_high <= A_high) || (B_low <= A_low && A_high <= B_high));
    }


    public boolean hasOverlap() {
        return !((A_low <= B_low && A_high < B_low) || (B_low <= A_low && B_high < A_low));
    }

}
