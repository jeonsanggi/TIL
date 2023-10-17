import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class LevelOnePrivacy {
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();

        Map<String, Integer> termsMap = convertTermsMap(terms);

        for (int i = 0; i < privacies.length; i++) {
            String[] splitPrivacy = privacies[i].split(" ");
            String collectionDate = splitPrivacy[0];
            String term = splitPrivacy[1];

            String[] todayList = today.split("\\.");
            String[] collectionDateList = collectionDate.split("\\.");

            int diffYear = diff(todayList[0], collectionDateList[0]);
            int diffMonth = diff(todayList[1], collectionDateList[1]);
            int diffDay = diff(todayList[2], collectionDateList[2]);
            int result = (diffYear * 12 * 28) + (diffMonth * 28) + diffDay;

            if (result >= termsMap.get(term) * 28) {
                answer.add(i+1);
            }

        }
        return answer.stream().mapToInt(i -> i).toArray();
    }

    private HashMap<String, Integer> convertTermsMap(String[] terms) {
        HashMap<String, Integer> termsMap = new HashMap<>();

        for (String term : terms) {
            String[] splitTerm = term.split(" ");
            termsMap.put(splitTerm[0], Integer.parseInt(splitTerm[1]));
        }

        return termsMap;
    }

    private int diff(String t, String c) {

        return Integer.parseInt(t) - Integer.parseInt(c);
    }

}