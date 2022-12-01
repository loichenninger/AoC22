package fr.n1g.aoc22.days;

import fr.n1g.aoc22.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.EMPTY_LIST;
import static java.util.Collections.emptyList;

public class Day1 {

    private static final List<String> lignes = Utils.getStringList("src/puzzle1.txt");

    public static void main(String[] args) {

        System.out.println("Nombres de lignes dans le fichier : " + lignes.size());
        List<Integer> caloriesByElf = getCaloriesByElf(lignes);

        System.out.println("*************************************");
        System.out.println("Step 1");
        System.out.println("*************************************");


        System.out.println(countCaloriesMax(caloriesByElf) +" calories max");

        System.out.println("*************************************");
        System.out.println("Step 2");
        System.out.println("*************************************");

        System.out.println("Top 3 : "+countTop3(caloriesByElf)+" calories");
    }

    private static Integer countCaloriesMax(List<Integer> input){;
        return input.stream().max(Integer::compare).get();
    }

    private static Integer countTop3(List<Integer> input){
        List<Integer> top3 = input.stream().sorted(Comparator.reverseOrder()).limit(3).collect(Collectors.toList());
        return top3.stream().reduce(0, Integer::sum);
    }

    private static List<Integer> getCaloriesByElf(List<String> input) {
        List<List<String>> result = getListsOfCalories(input);
        List<Integer> sumsCalories = result.stream().map(listCalories -> countCalories(listCalories)).collect(Collectors.toList());
        return sumsCalories;
    }

    private static List<List<String>> getListsOfCalories(List<String> input) {
        List<List<String>> initial = new ArrayList<>();
        initial.add(new ArrayList<>());
        List<List<String>> result = input.stream().reduce(initial, (subtotal, element) -> {
            if (element.trim().isEmpty()) {
                subtotal.add(new ArrayList<>());
            } else {
                subtotal.get(subtotal.size() - 1).add(element);
            }
            return subtotal;

        },(lists, lists2) -> emptyList());
        return result;
    }

    private static Integer countCalories(List<String> input){
        Integer res=0;
        for (String cal : input){
            res += Integer.parseInt(cal);
        }
        return res;
    }

}
