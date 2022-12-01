package kakao;

import util.Debug;

public class Solution4 {
    public static void main(String[] args) {
        Solution4 solution4 = new Solution4();
        solution4.solution(new long[] {7, 5});
        solution4.solution(new long[] {63, 111, 95});
        solution4.solution(new long[] {1000000000000L, 111, 95});
    }

    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            long number = numbers[i];
            String binary = getAppendedLength(Long.toBinaryString(number));
            System.out.println(Long.toBinaryString(number) + ", binary --> " + binary);
            answer[i] = Integer.parseInt(String.valueOf(binary.charAt(binary.length() / 2)));
        }
        Debug.print(answer);
        return answer;
    }

    private String getAppendedLength(String binary) {
        int[] lengths = new int[] {1,3,7,15,31,63};
        for (int i = 1; i < lengths.length; i++) {
            int length = lengths[i];
            if (binary.length() <= length) {
                System.out.println("length --> " + length);
                return repeat("0", length - binary.length()) + binary;
            }
        }
        return binary;
    }

    private String repeat(String s, int repeat) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < repeat; i++) {
            str.append(s);
        }
        return str.toString();
    }
}
