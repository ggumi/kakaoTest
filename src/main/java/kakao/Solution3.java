package kakao;

import util.Debug;

import java.util.ArrayList;
import java.util.List;

public class Solution3 {
    public static void main(String[] args) {
        Solution3 solution3 = new Solution3();
        int[] array = solution3.solution(new int[][] {
                {40, 10000},
                {25, 10000}
        }, new int[] {7000, 9000});
        Debug.print(array);
        Debug.print(solution3.solution(new int[][] {
                {40, 2900},
                {23, 10000},
                {11, 5200},
                {5, 5900},
                {40, 3100},
                {27, 9200},
                {32, 6900},
        }, new int[] {1300, 1500, 1600, 4900}));
    }

    private List<List<Double>> salesList = new ArrayList<>();

    public int[] solution(int[][] users, int[] emoticons) {
        salesList = new ArrayList<>();
        double[] sales = new double[] {0.9, 0.8, 0.7, 0.6};
        for (int i = 0; i < emoticons.length; i++) {
            List<Double> saleIf = new ArrayList<>();
            recurse(i, sales, emoticons, saleIf);
        }
        int maxCount = Integer.MIN_VALUE;
        int maxMoney = 0;
        for (int i = 0; i < salesList.size(); i++) {
            List<Double> salesIf = salesList.get(i);
            int count = 0;
            int money = 0;
            for (int[] user : users) {
                double userSale = (double) (100 - user[0]) / 100;
                int userMoney = user[1];
                int buySum = 0;
                for (int j = 0; j < salesIf.size(); j++) {
                    double emoticonSale = salesIf.get(j);
                    if (emoticonSale <= userSale) {
                        buySum += emoticons[j] * emoticonSale;
                    }
                }
                if (userMoney <= buySum) {
                    count++;
                } else {
                    money += buySum;
                }
            }
            if (maxCount < count) {
                maxCount = count;
                maxMoney = money;
            } else if (maxCount == count) {
                maxMoney = Math.max(money, maxMoney);
            }
        }
        return new int[] {maxCount, maxMoney};
    }

    private void recurse(int index, double[] sales, int[] emoticons, List<Double> saleIf) {
        if (index >= emoticons.length) {
            salesList.add(new ArrayList<>(saleIf));
            return;
        }
        for (double sale : sales) {
            saleIf.add(sale);
            recurse(index + 1, sales, emoticons, saleIf);
            saleIf.remove(sale);
        }
    }
}
