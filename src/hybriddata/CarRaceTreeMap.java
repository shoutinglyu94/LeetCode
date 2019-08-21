package hybriddata;

import java.util.*;

public class CarRaceTreeMap {
    TreeMap<Integer, Set<Integer>> treemap;
    Map<Integer, Integer> map;

    // n players, each has a unique id [0 .. n - 1]
    public CarRaceTreeMap(int n) {
        this.treemap = new TreeMap<>(Collections.reverseOrder());
        treemap.put(0, new HashSet<>());
        Set<Integer> set = treemap.get(0);
        this.map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            map.put(i, 0);
            set.add(i);
        }
        treemap.put(0, set);
    }

    public void check(int id) {
        int t = map.get(id);
        map.put(id, t + 1);
        // Remove from last checking time entry
        Set<Integer> last = treemap.get(t);
        last.remove(id);
        if (last.isEmpty()) {
            treemap.remove(t);
        }

        // Insert to current time entry
        treemap.putIfAbsent(t + 1, new HashSet<>());
        treemap.get(t + 1).add(id);
    }

    public List<Integer> leading(int k) {
        List<Integer> res = new ArrayList<>();
        for(Map.Entry<Integer, Set<Integer>> entry : treemap.entrySet()) {
            Set<Integer> set = entry.getValue();
            for(int player : set) {
                res.add(player);
                if(res.size() == k) return res;
            }
        }
        return res;
    }

}
