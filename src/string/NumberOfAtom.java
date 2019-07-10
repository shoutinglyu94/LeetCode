package string;

import java.util.TreeMap;

public class NumberOfAtom {

    public static void  main(String[] args) {
        String s = "Be32((H))2";
        System.out.println(countOfAtoms(s));

    }
    public static  String countOfAtoms(String formula) {
        TreeMap<String, Integer> map = new TreeMap<>();
        recursion(formula, map, 0, formula.length() - 1, 1);
        StringBuilder sb = new StringBuilder();
        for(String key : map.keySet()) {
            int count = map.get(key);
            sb.append(key);
            if(count == 1) continue;
            sb.append(String.valueOf(count));
        }
        return sb.toString();
    }

    private static int getEndDigitIndex(String s, int start) {
        int i = start;
        while(i < s.length() && Character.isDigit(s.charAt(i))) {
            i++;
        }
        return i;
    }

    private static int findRightParenthesis(String s, int start) {
        int left = 0, right = 0;
        int i = start;
        while(left == 0 || left != right) {
            if(s.charAt(i) == '(') {
                left++;
            } else if (s.charAt(i) == ')') {
                right++;
            }
            i++;
        }
        return i - 1;
    }

    private static void recursion(String formula, TreeMap<String, Integer> map, int start, int end, int factor) {
        while(start <= end) {
            if(formula.charAt(start) == '(') {
                // Find relevant right parenthesis
                int nextStart = start + 1;
                int newFactor = factor;
                int i = findRightParenthesis(formula, start);
                // Next char is digit
                if(i + 1 <= end && Character.isDigit(formula.charAt(i + 1))) {
                    int endDigit = getEndDigitIndex(formula, i + 1);
                    newFactor *= Integer.valueOf(formula.substring(i + 1, endDigit));
                    start = endDigit;
                } else {
                    start = i + 1;
                }
                // Pass from ( + 1 to ) - 1 into next recursion
                recursion(formula, map, nextStart, i - 1, newFactor);
            } else if(Character.isLetter(formula.charAt(start))) { //character
                // Find ending index of the Atom
                int i = start + 1;
                while(i <= end && Character.isLowerCase(formula.charAt(i))) {
                    i++;
                }
                // Atom
                String key = formula.substring(start, i);
                int count = map.getOrDefault(key, 0);
                if(i <= end && Character.isDigit(formula.charAt(i))) {
                    int endDigit = getEndDigitIndex(formula, i);
                    // Don't forget factor
                    count += factor * Integer.valueOf(formula.substring(i, endDigit));
                    start = endDigit;
                } else { // Out of bound or '(' or Upper case Letter
                    start = i;
                    count += factor;
                }
                map.put(key, count);
            }
        }

    }

}
