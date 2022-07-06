package utils;

import java.math.BigInteger;
import java.util.Arrays;

public class MathUtils {

    public static BigInteger lcm(BigInteger[] nums) {
        return Arrays.stream(nums).reduce(MathUtils::lcm).orElseGet(() -> BigInteger.valueOf(1L));
    }

    private static BigInteger lcm(BigInteger num1, BigInteger num2) {
        return num1.multiply(num2).divide(num1.gcd(num2));
    }
}
