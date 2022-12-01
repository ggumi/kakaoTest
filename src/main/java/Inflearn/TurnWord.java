package Inflearn;

import java.util.ArrayList;
import java.util.Scanner;

public class TurnWord {
    public static void main(String[] args){
        Scanner in=new Scanner(System.in);

        int input1 = in.nextInt();

        String arrWord[] = new String[input1];

        for (int i=0; i<arrWord.length; i++) {//5
            arrWord[i] = in.next();
        }

        for (String word : arrWord) {
            char[] charArr=word.toCharArray();
            System.out.println();
            for (int i = 0; i < charArr.length ; i++) {
                System.out.print(charArr[charArr.length-1-i]);
            }
        }

        //1. 숫자 입력 받음
        //2. 입력한 수 만큼 단어 입력 받음
        //3. 그 단어를 뒤집어서 출력

    }


}