import java.lang.Math;

class LevelOneTemplarWeapon {
    public int solution(int number, int limit, int power) {
        int answer = 0;

        for (int i = 1; i <= number; i++) {
            answer += getPower(i, limit, power);
        }
        return answer;
    }

    public int getPower(int num, int limit, int power) {

        int cnt = 0;
        for (int i = 1; i*i <= num; i++) {
            if (i * i == num) cnt++;
            else if (num % i == 0) cnt += 2;
        }

        return cnt > limit ? power : cnt;
    }
}