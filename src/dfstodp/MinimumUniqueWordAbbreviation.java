package dfstodp;

public class MinimumUniqueWordAbbreviation {

    public static void main(String[] args) {
        System.out.println(conflict("kkkkk", "5"));
    }

    private static boolean conflict(String word, String s) {
        int i = 0, j = 0;
        while(i < s.length()) {
            if (j >= word.length()) return false;
            else if(Character.isDigit(s.charAt(i))) {
                int start = i;
                while(i < s.length() && Character.isDigit(s.charAt(i))) {
                    i++;
                }
                int n = Integer.valueOf(s.substring(start, i));
                j += n;
            } else {
                if(s.charAt(i) != word.charAt(j)) return false;
                i++;
                j++;
            }
        }
        return j == word.length();
    }
}
