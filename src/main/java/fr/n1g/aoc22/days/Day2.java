package fr.n1g.aoc22.days;

import fr.n1g.aoc22.utils.Utils;

import java.util.List;

public class Day2 {

    private static final List<String> lignes = Utils.getStringList("src/main/resources/puzzles/puzzle2.txt");

    public static void main(String[] args) {

        System.out.println("Nombres de lignes dans le fichier : " + lignes.size());

        System.out.println("*************************************");
        System.out.println("Step 1");
        System.out.println("*************************************");

        System.out.println("Score obtenu " + computeScore(lignes));


        System.out.println("*************************************");
        System.out.println("Step 2");
        System.out.println("*************************************");

        System.out.println("Score obtenu " + computeScore2(lignes));

    }

    private static Integer computeScore(List<String> rounds){
        return rounds.stream().map(Day2::computeRound).reduce(0,Integer::sum);
    }
    private static Integer computeRound(String round){
        Integer res=0;
        String[] bets = round.split("\\s");
        switch(bets[1]){
            case "X":
                res +=1;
                if (bets[0].equals("C")) {
                    res+=6;
                } else if(bets[0].equals("A")){
                    res+=3;
                }
                break;
            case "Y":
                res +=2;
                if (bets[0].equals("A")) {
                    res+=6;
                } else if(bets[0].equals("B")){
                    res+=3;
                }
                break;
            case "Z":
                res +=3;
                if (bets[0].equals("B")) {
                    res+=6;
                } else if(bets[0].equals("C")){
                    res+=3;
                }
                break;
            default:
                throw new RuntimeException();
        }
        return res;
    }

    private static Integer computeScore2(List<String> rounds){
        return rounds.stream().map(Day2::computeRound2).reduce(0,Integer::sum);
    }
    private static Integer computeRound2(String round){
        Integer res=0;
        String[] bets = round.split("\\s");
        switch(bets[1]){
            case "X":
                if (bets[0].equals("A")) {
                    res+=3;
                } else if(bets[0].equals("B")){
                    res+=1;
                } else if (bets[0].equals("C")) {
                    res+=2;
                }
                break;
            case "Y":
                if (bets[0].equals("A")) {
                    res+=4;
                } else if(bets[0].equals("B")){
                    res+=5;
                } else if (bets[0].equals("C")) {
                    res+=6;
                }
                break;
            case "Z":
                if (bets[0].equals("A")) {
                    res+=8;
                } else if(bets[0].equals("B")){
                    res+=9;
                } else if (bets[0].equals("C")) {
                    res+=7;
                }
                break;
            default:
                throw new RuntimeException();
        }
        return res;
    }
}
