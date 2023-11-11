import java.lang.StringBuilder;

class LevelOneFoodFight {
    public String solution(int[] food) {
        StringBuilder sb = new StringBuilder("0");

        for (int i = food.length-1; i > 0; i--) {
            for (int j = 0; j < food[i]/2; j++) {
                sb.insert(0, Integer.toString(i));
                sb.append(Integer.toString(i));
            }
        }

        return sb.toString();
    }
}