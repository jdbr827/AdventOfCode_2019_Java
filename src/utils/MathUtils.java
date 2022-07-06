package utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MathUtils {

    public static BigInteger lcm(BigInteger[] nums) {
        return Arrays.stream(nums).reduce(MathUtils::lcm).orElseGet(() -> BigInteger.valueOf(1L));
    }

    public static BigInteger lcm(int[] nums) {
        BigInteger[] bigInts = new BigInteger[nums.length];
        for (int i=0; i<nums.length; i++) {
            bigInts[i] = BigInteger.valueOf(nums[i]);
        }
        return lcm(bigInts);
    }
    private static BigInteger lcm(BigInteger num1, BigInteger num2) {
        return num1.multiply(num2).divide(num1.gcd(num2));
    }
}
