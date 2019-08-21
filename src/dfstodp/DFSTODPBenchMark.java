package dfstodp;

public class DFSTODPBenchMark {
    public static void main(String[] args) {
//        DecodeWays decodeWays = new DecodeWays();
//        decodeWays.numDecodings("1231");
//        System.out.println(Integer.parseInt("00123"));

        CampusBike campusBike = new CampusBike();
        campusBike.assignBikes(new int[][] {
                {0,0},{2,1}
        },  new int[][] {
                {1,2},{3,3}
        });

    }
}
