package fr.n1g.aoc22.days;

import fr.n1g.aoc22.utils.Utils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day6 {

    private static final List<String> lignes = Utils.getStringList("src/main/resources/puzzles/puzzle6.txt");

    public static void main(String[] args) {

        System.out.println("Nombres de lignes dans le fichier : " + lignes.size());
        String input=lignes.get(0);

        System.out.println("*************************************");
        System.out.println("Step 1");
        System.out.println("*************************************");

        System.out.println("First maker after character "+searchFirstMarker(input));


        System.out.println("*************************************");
        System.out.println("Step 2");
        System.out.println("*************************************");

        System.out.println("");



    }

    private static Integer searchFirstMarker(String input){
        Integer maxNumberOfChecks = input.length()-4+1;
        Integer res = 0;
        for (int i=0;i<maxNumberOfChecks;i++){
            if (areAllUnique(input.substring(i,i+4))){
                res=i+4;
                break;
            }
        }
        return res;
    }

    private static boolean areAllUnique(String input){
        return input.chars().filter(elt-> Collections.frequency(input.chars().boxed().collect(Collectors.toList()), elt)>1).count() > 1 ? false :true;
    }

}
