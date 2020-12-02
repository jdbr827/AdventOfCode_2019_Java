package year_2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day2 {

    static class PasswordData {

        int i1;
        int i2;
        char magicChar;
        char[] code;

        PasswordData(String input) {
            Pattern pattern = Pattern.compile("(\\d*)-(\\d*) (\\w): (\\w*)");
            Matcher matcher = pattern.matcher(input);
            if (!matcher.find()) {
                throw new Error("Badly Formatted Pattern: " + input);

            }
            i1 = Integer.parseInt(matcher.group(1));
            i2 = Integer.parseInt(matcher.group(2));
            magicChar = matcher.group(3).toCharArray()[0];
            code = matcher.group(4).toCharArray();
        }

    }

    public static boolean isValidPassword1(PasswordData pData) {
        int count = 0;
        for (char c : pData.code) {
            if (c == pData.magicChar) {
                count += 1;
            }
        }
        return (count >= pData.i1 && count <= pData.i2);
    }

    public static boolean isValidPassword2(PasswordData pData) {
        return (pData.code[pData.i1 - 1] == pData.magicChar) ^ (pData.code[pData.i2 - 1] == pData.magicChar);
    }

    public static void main(String[] args) throws FileNotFoundException {
        final String INPUT_FILE = "C:\\Users\\Jake\\IdeaProjects\\AdventOfCode_2020\\src\\year_2020\\input_aoc_2020_2.txt";
        File file = new File(INPUT_FILE);
        Scanner scanner = new Scanner(file);

        int validPasswords1 = 0;
        int validPasswords2 = 0;
            while (scanner.hasNextLine()) {
                PasswordData pdata = new PasswordData(scanner.nextLine());
                validPasswords1 += isValidPassword1(pdata) ? 1 : 0;
                validPasswords2 += isValidPassword2(pdata) ? 1 : 0;
            }
        System.out.println(validPasswords1 == 378);
        System.out.println(validPasswords2 == 280);
    }
}
