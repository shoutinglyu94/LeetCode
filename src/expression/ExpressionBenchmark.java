package expression;

import java.util.List;

public class ExpressionBenchmark {
    public static void main(String[] args) {
        testAddParentheses();
    }

    private static void testAddParentheses() {
        AddParentheses add = new AddParentheses();
        String test0 = "2-1-1";
        String test1 = "2*3-4*5";
        String test2 = "2-1-1-1-1";
        List<Integer> res = add.diffWaysToCompute(test2);
        for(int r : res) {
            System.out.println(r);
        }
    }

    private static void testBraceExpression() {
        BraceExpression braceExpression = new BraceExpression();
        braceExpression.braceExpansionII("{a,b}c");
    }
}
