package fr.n1g.aoc22.days;

import fr.n1g.aoc22.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day4 {

    private static final List<String> lignes = Utils.getStringList("src/main/resources/puzzles/puzzle4.txt");

    public static void main(String[] args) {

        System.out.println("Nombres de lignes dans le fichier : " + lignes.size());

        System.out.println("*************************************");
        System.out.println("Step 1");
        System.out.println("*************************************");

        System.out.println("Number of overlaping groups : " + sumOverlapingGroup(lignes));


        System.out.println("*************************************");
        System.out.println("Step 2");
        System.out.println("*************************************");

        System.out.println("Sum of overlaping sections : " + sumOverLappingSections(lignes));

    }

    private static List<List<Integer>> readSections (String pair){
        String[] sections = pair.split(",");
        List<List<Integer>> res = new ArrayList<>();
        for (int i=0; i <2; i++){
            String[] bornes=sections[i].split("-");
            res.add(IntStream.range(Integer.parseInt(bornes[0]),Integer.parseInt(bornes[1])+1).boxed().collect(Collectors.toList()));
        }
        return res;
    }

    private static Integer isTotallyOverlaped (List<List<Integer>> assignments){
        List<Integer> smallerList = new ArrayList<>();
        List<Integer> biggerList = new ArrayList<>();
        if (assignments.get(0).size()<=assignments.get(1).size()){
            smallerList=assignments.get(0);
            biggerList=assignments.get(1);
        } else {
            smallerList=assignments.get(1);
            biggerList=assignments.get(0);
        }
        return biggerList.containsAll(smallerList) ? 1 : 0;
    }

    private static Integer isPartiallyOverlaped (List<List<Integer>> assignments){
        List<Integer> smallerList = new ArrayList<>();
        List<Integer> biggerList = new ArrayList<>();
        if (assignments.get(0).size()<=assignments.get(1).size()){
            smallerList=assignments.get(0);
            biggerList=assignments.get(1);
        } else {
            smallerList=assignments.get(1);
            biggerList=assignments.get(0);
        }
        List<Integer> finalBiggerList = biggerList;
        return smallerList.stream().map(section -> finalBiggerList.contains(section) ? 1:0).reduce(0,Integer::sum)>0 ? 1 : 0;
    }

    private static Integer sumOverlapingGroup(List<String> input){
        return input.stream()
                .map(pair->readSections(pair))
                .map(assignments->isTotallyOverlaped(assignments))
                .reduce(0,Integer::sum);
    }

    private static Integer sumOverLappingSections(List<String> input){
        return input.stream()
                .map(pair->readSections(pair))
                .map(assignments->isPartiallyOverlaped(assignments))
                .reduce(0,Integer::sum);
    }



}
