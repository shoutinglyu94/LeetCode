package string;

public class CompareVersion {

    public static void main(String[] args) {
        compareVersion("1.0", "1");
    }

    public static int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int i = 0, j = 0;

        while(i < v1.length || j < v2.length) {
            String s1 = i < v1.length ? v1[i] : "0";
            String s2 = j < v2.length ? v2[j] : "0";
            int res = compare(s1, s2);
            if(res == 0) {
                i++;
                j++;
            } else {
                return res;
            }
        }
        return 0;
    }

    private static int compare(String s1, String s2) {
        int n1 = parse(s1), n2 = parse(s2);
        return Integer.compare(n1, n2);
    }

    private static int parse(String s) {
        int i = 0;
        while(s.charAt(i) == '0') {
            i++;
        }
        if(i < s.length()) {
            return Integer.valueOf(s.substring(i));
        } else {
            return 0;
        }
    }
}
