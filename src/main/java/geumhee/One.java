package geumhee;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class One {

    public static void main(String[] args) throws ParseException {

        String today1 = "2022.05.19";
        String [] terms1 = new String[]{"A 6","B 12","C 3"};
        //System.out.println(Arrays.toString(terms1) );  // [A 6, B 12, C 3]

//        String(Map<String, Integer> terms1 = new HashMap<>();
//        terms1.put("A", 6);
//        terms1.put("B", 12);
//        terms1.put("C", 3);
//        System.out.println("mapTerms"+terms1);

        String[] privacies1 = new String[]{"2021.05.02 A","2021.07.01 B","2022.02.19 C","2022.02.20 C"};
        System.out.println("11"+Arrays.toString(privacies1));

        One one = new One();
        one.solution(today1, terms1, privacies1);
        System.out.println("answer+++" + one.solution(today1, terms1, privacies1));
    }

    public int[] solution(String today, String[] terms, String[] privacies) throws ParseException {

        System.out.println("여긴 solution");
        System.out.println("today"+today); //2022.05.19
        System.out.println("terms"+Arrays.toString(terms)); //[A 6, B 12, C 3]
        System.out.println("privacies"+Arrays.toString(privacies)); //[2021.05.02 A, 2021.07.01 B, 2022.02.19 C, 2022.02.20 C]
        // if. a b c
        // a 이면 MM+6
//        System.out.println("00000"+Arrays.toString(privacies[1].split(" ")));
//        System.out.println("11111"+privacies[1].split(" ")[1]);  B
       // if()
//        for (int i = 0; i < terms.length; i++) {
//
//        }
        int[] answer = {};
        for (int j = 0; j < terms.length; j ++){
            System.out.println("들어가진건지1111--->");

            for (int i = 0; i< privacies.length; i++){
               // System.out.println("privacies[i]"+privacies[i]); //2021.05.02 A
                System.out.println("들어가진건지2222--->");
                System.out.println("privacies[i].split(\" \")[1]--->"+privacies[i].split(" ")[1]);
                System.out.println("terms[j].split(\" \")[0]--->"+terms[j].split(" ")[0]);

                if (privacies[i].split(" ")[1].equals(terms[j].split(" ")[0]) ) {
                    System.out.println("들어가진건333--->");
                    String dateStr = privacies[i].split(" ")[0];
                    System.out.println("dd---"+dateStr);

                    SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");
                    Date d = df.parse(dateStr);
                    System.out.println("eee--->" + d);

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(d);
                    int addMonth = Integer.parseInt(terms[j].split(" ")[1]);
                    System.out.println("addMOnth"+addMonth);
                    cal.add(Calendar.MONTH, addMonth);


//                    int year = cal.get(Calendar.YEAR);
//                    int month = cal.get(Calendar.MONTH) + addMonth; //0부터 시작하기 때문에 1더해준다
//
                    System.out.println("원하는 날짜가 나옴---->" + df.format(cal.getTime()));
//
                    //Date date1 = df.parse(dateStr);
                    System.out.println("String.valueOf(cal.getTime())+++"+String.valueOf(cal.getTime()));
                    Date date1 = new Date();
                    date1 = df.parse(df.format(cal.getTime()));
                    System.out.println("date1" + date1);


                    Date date2 = new Date();
                    date2 = df.parse(today);
                    System.out.println("date2" + date2);


//                    int month = cal.get(Calendar.MONTH); //0부터 시작하기 때문에 1더해준다
//                    System.out.println("year" + year);
//                    System.out.println("month" + month);

                    // 날짜비교해서 배열을 하나 만들어서 배열에다가 .add 넣고 for문 다돌면 return


//                    Date date1 = new Date();
//                    Date date2 = new Date();

                    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
//
//                    date1 = format.parse("20210629");
//                    date2 = format.parse("20110328");
//                    System.out.println("ssss"+date1.compareTo(date2));

                    List<int[]> result = new ArrayList<>(Arrays.asList(answer));


                    if(date1.compareTo(date2) > 0) {
                        System.out.println("date1이 더 미래 날짜입니다.");
                    }else if(date1.compareTo(date2) < 0) {
                        System.out.println("date1이 더 과거 날짜입니다.");
//                        result.add(i, i);
//                        answer = {i+1};
                    }else {
                        System.out.println("같은 날짜입니다.");
                    }


                }



            }
        }
        System.out.println("answer----" + Arrays.toString(answer));
        return answer; // [1,3]
    }


}
