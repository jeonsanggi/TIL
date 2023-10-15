class LevelOneOurPassword {
    public String solution(String s, String skip, int index) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < s.length(); i++) {
            int plus = 0;
            int convertIndex = getConvertIndex(s.charAt(i));

            int cnt = 0;
            while (index != cnt){
                convertIndex++;
                if (skip.indexOf(getConvertChar(convertIndex)) < 0) {
                    cnt++;
                }
            }

            sb.append(getConvertChar(convertIndex));
        }

        return sb.toString();
    }

    private int getConvertIndex(char c) {
        return c - 'a';
    }

    private char getConvertChar(int index) {
        return (char)(index%26 + 'a');
    }


}