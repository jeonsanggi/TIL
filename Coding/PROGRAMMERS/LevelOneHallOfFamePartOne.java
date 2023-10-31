import java.util.PriorityQueue;
import java.util.Queue;
import java.util.List;
import java.util.ArrayList;

class LevelOneHallOfFamePartOne {
    public int[] solution(int k, int[] score) {
        List<Integer> answer = new ArrayList();
        Queue<Integer> priorityQueue = new PriorityQueue();

        for (int sc : score) {
            priorityQueue.offer(sc);
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }

            answer.add(priorityQueue.peek());
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}