package graph.bfs;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class CheapestFlight {
    class Route {
        int cost;
        int flight;
        int place;

        Route(int cost, int flight, int place) {
            this.cost = cost;
            this.flight = flight;
            this.place = place;
        }
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] graph = new int[n][n];
        for (int[] flight : flights) {
            graph[flight[0]][flight[1]] = flight[2];
        }

        Map<Integer, Integer> best = new HashMap<>();
        PriorityQueue<Route> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        pq.offer(new Route(0, 0, src));
        while (!pq.isEmpty()) {
            Route cur = pq.poll();
            int cost = cur.cost, k = cur.flight, place = cur.place;
            if (k > K + 1 || cost > best.getOrDefault(k * 1000 + place, Integer.MAX_VALUE)) continue;
            if (place == dst) return cost;
            for (int nei = 0; nei < n; nei++) {
                if (graph[place][nei] > 0) {
                    int newCost = cost + graph[place][nei];
                    if (newCost < best.getOrDefault((k + 1) * 1000 + nei, Integer.MAX_VALUE)) {
                        pq.offer(new Route(newCost, k + 1, nei));
                        best.put(nei, newCost);
                    }
                }
            }
        }
        return -1;
    }
}
