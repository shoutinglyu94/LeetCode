package recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParseLisp {
    private static final String MUL = "mult";
    private static final String ADD = "add";
    private static final String LET = "let";
    public int evaluate(String expression) {
        return dfs(expression, 1, expression.length() - 1, new HashMap<>());
    }

    private int dfs(String s, int start, int end, Map<String, Integer> map) {
        String operator = null;
        List<String> vars = new ArrayList<>();
        List<Integer> vals = new ArrayList<>();
        Map<String, Integer> newMap = new HashMap<>(map);
        for(int i = start; i < end; i++) {
            char c = s.charAt(i);
            if(c == '(') {
                // Find close parenthesis.
                int j = i;
                i++;
                int l = 1, r = 0;
                while(i < s.length() && l != r) {
                    if(s.charAt(i) == '(') l++;
                    if(s.charAt(i) == ')') r++;
                    i++;
                }
                int next = (dfs(s, j + 1, i - 1, newMap));
                if(operator.equals(LET) && vals.size() == vars.size() - 1) {
                    newMap.put(vars.get(vars.size() - 1), next);
                }
                vals.add(next);
                i--;
            } else if (s.charAt(i) == ' ') {
                continue;
            } else if (Character.isLetter(c)) {
                int j = i;
                while(i < s.length() && (Character.isLetter(s.charAt(i)) || Character.isDigit(s.charAt(i)))) {
                    i++;
                }
                String var = s.substring(j, i);
                if(isOperator(var)) {
                    operator = var;
                } else {
                    vars.add(var);
                }
                i--;
            } else {
                // Digit
                // Could be negative
                int sign = 1;
                if(s.charAt(i) == '-') {
                    sign = -1;
                    i++;
                }
                int j = i;
                while(i < s.length() && Character.isDigit(s.charAt(i))) {
                    i++;
                }
                int val = sign * Integer.valueOf(s.substring(j, i));
                if(operator.equals(LET) && vals.size() == vars.size() - 1) {
                    newMap.put(vars.get(vars.size() - 1), val);
                }
                vals.add(val);
                i--;
            }
        }
        return operate(operator, vars, vals, map, newMap);
    }

    private boolean isOperator(String var) {
        if(var == null) return false;
        return var.equals(MUL) || var.equals(ADD) || var.equals(LET);
    }

    private int operate(String operator, List<String> vars, List<Integer> vals, Map<String, Integer> map, Map<String, Integer> newMap) {
        int res = 0;
        if(operator.equals(ADD)) {
            for(String var : vars) {
                res += map.get(var);
            }
            for(int val : vals) {
                res += val;
            }
        } else if (operator.equals(MUL)) {
            res = 1;
            for(String var : vars) {
                res *= map.get(var);
            }
            for(int val : vals) {
                res *= val;
            }
        } else {
            if(vals.isEmpty()) {
                res = newMap.get(vars.get(vars.size() - 1));
            } else {
                res = vals.get(vals.size() - 1);
            }
        }
        System.out.println("Calculate: " + operator + " " +res);
        return res;
    }
}
