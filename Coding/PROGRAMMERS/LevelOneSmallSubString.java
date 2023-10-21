class LevelOneSmallSubString {
    public int solution(String t, String p) {
        int answer = 0;

        for(int i = 0; i <= t.length() - p.length(); i++) {
            if(lessThan(t.substring(i, i + p.length()), p)) {
                answer++;
            }
        }

        return answer;
    }

    private boolean lessThan (String t, String p){
        return Long.parseLong(t) <= Long.parseLong(p);
    }
}