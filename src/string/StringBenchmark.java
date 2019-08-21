package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringBenchmark {
    public static void main(String[] args) {
        testGoupAllAnagram();
    }

    private static void testGoupAllAnagram() {
        GroupAllAnagram anagram = new GroupAllAnagram();
        List<List<String>> res = anagram.group(new ArrayList<>(Arrays.asList("eat", "tea", "tan", "ate", "nat", "bat")));
        for(List<String> list : res) {
            System.out.println("---------");
            for(String s : list) {
                System.out.println(s);
            }
        }

    }

    private static void testBracket() {
        BraceExpansion tester = new BraceExpansion();
        //System.out.println(tester.braceExpansionII("{a,b}{c{d,e}}"));
        //System.out.println(tester.braceExpansionII("{{a,z},a{b,c},{ab,z}}"));
        System.out.println(tester.braceExpansionII("{a,b}c{d,e}f"));
    }
}
