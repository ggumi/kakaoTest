package kakao;

import util.Debug;

import java.util.*;

public class Solution1 {
    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        int[] answer = solution1.solution("2022.05.19",
                new String[] {"A 6", "B 12", "C 3"},
                new String[] {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"});
        Debug.print(answer);
//        Debug.print(solution1.solution("2020.01.01",
//                new String[] {"Z 3", "D 5"},
//                new String[] {
//                        "2019.01.01 D",
//                        "2019.11.15 Z",
//                        "2019.08.02 D",
//                        "2019.07.01 D",
//                        "2018.12.28 Z"}));
    }

    public int[] solution(String today, String[] terms, String[] privacies) {
        // today를 String -> Int
        int todayNumber = getDateStringAsInt(today);
        // terms를 [] -> 데이터 잘라 -> Map
        Map<String, Integer> termsMap = new HashMap<>();
        for (String term : terms) {
            String[] splits = term.split(" ");
            //System.out.println("splits->"+ Arrays.toString(splits)); // [A, 6] [B, 12] [C, 3]
            termsMap.put(splits[0], Integer.parseInt(splits[1]));
        }
       // System.out.println("termsMap->"+termsMap); {A=6, B=12, C=3}

        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < privacies.length; i++) {
            String privacy = privacies[i]; // 2021.05.02 A
            String date = privacy.split(" ")[0];  // 2021.05.02
            String term = privacy.split(" ")[1]; // A
            int termFullMonth = termsMap.get(term); // 6
            int dateNumber = getDateStringAsInt(date); //20210502
            int year = termFullMonth / 12; // 0.5이 아닌 0
            System.out.println("year->"+year);
            int month = termFullMonth % 12; // 6
            dateNumber += year * 10000; //
            System.out.println("dateNumberYEAR->"+dateNumber);
            dateNumber += month * 100; // 20211102
            System.out.println("dateNumberMONTH->"+dateNumber);
            if (getDateNumberMonth(dateNumber) > 12) {
                dateNumber += 10000; //
                dateNumber -= 1200; //
                System.out.println("dateNumbeer11-->"+dateNumber);
            }
                System.out.println("dateNumbeer22-->"+dateNumber);
            if (isExpiry(todayNumber, dateNumber)) {
                answer.add(i + 1);
            }
        }
        System.out.println("answer->"+answer);
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    private int getDateNumberMonth(int date) {
        return date % 10000 / 100;
    } //11.02

    private boolean isExpiry(int today, int date) {
        return today >= date;
    }

    private int getDateStringAsInt(String dateString) {
        return Integer.parseInt(dateString.replaceAll("\\.", ""));
    }
}
