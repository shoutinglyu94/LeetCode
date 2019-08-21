package graph.graphrepresentation;

import java.util.*;

public class CrossRiver {
    public List<List<String>> crossRiver(Set<String> cargo) {
        List<List<String>> res =  new ArrayList<>();
        List<String> left = new ArrayList<>(cargo);
        List<String> right = new ArrayList<>();
        List<String> move = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        dfs(true, left, right, visited, move, res);
        return res;
    }

    private void dfs(boolean fromLeft, List<String> left, List<String> right, Set<String> visited,  List<String> move, List<List<String>> res) {
        if(checkVisited(visited, left, fromLeft)) {
            return;
        }
        if((!fromLeft && !valid(left)) || (fromLeft && !valid(right))) return;
        if(!fromLeft && left.isEmpty()) {
            res.add(new ArrayList<>(move));
            return;
        }
        if(fromLeft){
            move.add("From Left: Empty");
            dfs(false, left, right, visited, move, res);
            move.remove(move.size() - 1);
            for(int i = 0; i < left.size(); i++) {
                String l = left.get(i);
                left.remove(i);
                right.add(l);
                move.add("From Left: " + l);
                dfs(false, left, right, visited, move, res);
                move.remove(move.size() - 1);
                right.remove(l);
                left.add(i, l);
            }
        } else {
            move.add("From Right: Empty");
            dfs(true, left, right, visited, move, res);
            move.remove(move.size() - 1);
            for(int i = 0; i < right.size(); i++) {
                String r = right.get(i);
                right.remove(i);
                left.add(r);
                move.add("From Right: " + r);
                dfs(true, left, right, visited, move, res);
                move.remove(move.size() - 1);
                left.remove(r);
                right.add(i, r);
            }
        }

    }

    private boolean checkVisited(Set<String> visited, List<String> left, boolean fromLeft) {
        Collections.sort(left);
        String s = "";
        for(String l : left) {
            s += l;
        }
        String node = fromLeft ? s : ("-" + s);
        if(visited.add(node)) {
            return false;
        } else {
            return true;
        }
    }

    private boolean valid(List<String> coach) {
        if(coach.contains("W") && coach.contains("S")) {
            return false;
        }
        if(coach.contains("S") && coach.contains("G")) {
            return false;
        }
        return true;
    }

}
