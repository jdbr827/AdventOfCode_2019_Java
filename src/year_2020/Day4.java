package year_2020;

import utils.ReadIn;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4 {

    static List<String> requiredKeys = List.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");
    static class Passport {

        Map<String, String> map = new HashMap<String, String>();
        Passport() {

        }

        void addData(String data) {
            String[] kv = data.split(":");
            map.put(kv[0], kv[1]);
        }

        int isValidYr(String yr) {
             Pattern pattern = Pattern.compile("(\\d){4}");
             Matcher matcher = pattern.matcher(yr);
             if (!matcher.find()) {
                 return -1;
             }
             return Integer.parseInt(yr);

        }

        boolean hasValidHeight() {
            Pattern height= Pattern.compile("(\\d*)(cm|in)");
            Matcher heightMatcher = height.matcher(map.get("hgt"));
            if (!heightMatcher.find()) {
                return false;
            }
            int hgt = Integer.parseInt(heightMatcher.group(1));
            if (heightMatcher.group(2).equals("cm")) {
                if (hgt >= 193 || hgt <= 150 ) {
                    return false;
                }
            } else {
                if (hgt >= 76 || hgt <= 59) {
                    return false;
                }
            }
            return true;
        }

        boolean hasValidHairColor() {
            Pattern hcl = Pattern.compile("#([0-9]|[a-f]){6}");
            Matcher hclMatcher = hcl.matcher(map.get("hcl"));
            return hclMatcher.find();
        }

        boolean hasValidEyeColor() {
            Pattern ecl = Pattern.compile("amb|blu|brn|grn|gry|hzl|oth");
            Matcher eclMatcher = ecl.matcher(map.get("ecl"));
            return eclMatcher.find();
        }

        boolean hasValidPid() {
             Pattern pid = Pattern.compile("[0-9]{9}");
             Matcher pidMatcher = pid.matcher(map.get("pid"));
            return pidMatcher.find();
        }

        boolean hasValidByr() {
            int byr = isValidYr(map.get("byr"));
            return byr >= 1920 && byr <= 2002;
        }

        boolean isValidIyr() {
            int iyr = isValidYr(map.get("iyr"));
            return iyr >= 2010 && iyr <= 2020;
        }

        boolean isValidEyr() {
            int eyr = isValidYr(map.get("eyr"));
            return eyr >= 2020 && eyr <= 2030;
        }

        boolean isValid() {
            if (!map.keySet().containsAll(requiredKeys)) {
                return false;
            }

            return (hasValidEyeColor() && hasValidHairColor() && hasValidPid() && hasValidHeight() && hasValidByr() && isValidEyr() && isValidIyr()
            );





        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("C:\\Users\\Jake\\IdeaProjects\\AdventOfCode_2020\\src\\year_2020\\input_aoc_2020_4.txt");
        Scanner scanner = new Scanner(file);

        int count = 0;
        Passport thisPassport = new Passport();
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            if (data.isEmpty()) {
                count += thisPassport.isValid() ? 1 : 0;
                thisPassport = new Passport();
            } else {
                String[] pieces = data.split(" ");
                for (String piece : pieces) {
                    thisPassport.addData(piece);
                }
            }
        }
        count += thisPassport.isValid() ? 1 : 0;

        System.out.println(count);
    }

}
