package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class test {

    public static void main(String[] args) {
        Random r = new Random();
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(6);
        list.add(6);
        list.add(7);
        list.add(9);
        list.add(20);
        Integer i1 = list.stream().reduce(0, (a, b) -> a + b);
        List<Integer> list2 = list.stream().map(x -> x + 1).collect(Collectors.toList());
        List<Integer> list3 = list.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
        list3.forEach(elem -> System.out.println(elem));
        System.out.println("-----");
        list.forEach(System.out::println);
        System.out.println("-----");
        for (int i = 0; i < 10; i++) {
            int j = r.nextInt(5) + 1;
            System.out.println(j);
        }
    }
}
