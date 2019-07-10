package string;

public class StringBenchmark {
    public static void main(String[] args) {
        BraceExpansion tester = new BraceExpansion();
        //System.out.println(tester.braceExpansionII("{a,b}{c{d,e}}"));
        //System.out.println(tester.braceExpansionII("{{a,z},a{b,c},{ab,z}}"));
        System.out.println(tester.braceExpansionII("{a,b}c{d,e}f"));
    }
}
