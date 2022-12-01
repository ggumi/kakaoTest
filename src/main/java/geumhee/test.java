package geumhee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class test {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(9,7,6,9);
        Stream<Integer> stream = list.stream();
        stream.forEach(System.out::println);

    }



}
