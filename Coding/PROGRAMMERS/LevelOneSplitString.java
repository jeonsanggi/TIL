class LevelOneSplitString {
    public int solution(String s) {
        int answer = 0;

        int xCnt = 0, otherCnt = 0;
        char x = 0;
        for (char c : s.toCharArray()) {
            if (x == 0) x = c;

            if (x == c) xCnt++;
            else otherCnt++;
            if (xCnt == otherCnt) {
                x = 0;
                xCnt = 0;
                otherCnt = 0;
                answer++;
            }
        }

        if (x != 0) answer++;
        return answer;
    }
}