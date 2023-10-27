import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;


class LevelOneNearestChar {
    public int[] solution(String s) {
        Map<Character, Integer> charIndex = new HashMap();
        List<Integer> answer = new ArrayList();

        for (int i = 0; i < s.length(); i++){
            int index = charIndex.getOrDefault(s.charAt(i), -1);
            charIndex.put(s.charAt(i), i);
            boolean result = (index == -1) ? answer.add(index) : answer.add(i - index);
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}