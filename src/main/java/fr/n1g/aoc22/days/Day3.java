package fr.n1g.aoc22.days;

import fr.n1g.aoc22.utils.Utils;

import java.util.List;

public class Day3 {

    private static final List<String> lignes = Utils.getStringList("src/puzzle3.txt");

    public static void main(String[] args) {

        System.out.println("Nombres de lignes dans le fichier : " + lignes.size());

        System.out.println("*************************************");
        System.out.println("Step 1");
        System.out.println("*************************************");

        System.out.println("Sum of priorities : "+ getPrioritiesSum(lignes));


        System.out.println("*************************************");
        System.out.println("Step 2");
        System.out.println("*************************************");

        System.out.println("");

    }


    private static char getCommonCharacter(String input){
        StringBuilder res=new StringBuilder();
        String firsthalf="";
        String secondhalf="";
        if(input.length()%2 == 0)
        {
            firsthalf =input.substring(0, input.length()/2);
            secondhalf = input.substring(input.length()/2);
        }
        else
        {
            System.out.println("The input cannot be divided in half");;
        }
        // Iterating a string char by char
        for (int i = 0; i < firsthalf.length(); i++) {
            // Check for common char
            if (secondhalf.contains(Character.toString(firsthalf.charAt(i)))) {
                // If contains append it to resultant string
                res.append(Character.toString(firsthalf.charAt(i)));
            }
        }
        return res.toString().charAt(0);
    }

    private static Integer getPrioritiesSum(List<String> input){
        return input.stream().map(rucksack -> getCommonCharacter(rucksack)).map(commonChar->getPriority(commonChar)).reduce(0,Integer::sum);
    }

    private static int getPriority(char input){
        int res=0;
        int ascii = (int)input;
        if (ascii<=90){
            res=ascii-38;
        } else if (ascii>=97){
            res=ascii-96;
        }
        return Integer.valueOf(res);
    }
}
