package fr.n1g.aoc22.days;

import com.google.common.collect.Lists;
import fr.n1g.aoc22.utils.Utils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day5 {

    private static final List<String> lignes = Utils.getStringList("src/main/resources/puzzles/puzzle5.txt");

    public static void main(String[] args) {

        System.out.println("Nombres de lignes dans le fichier : " + lignes.size());
        List<String> moves=readMoves(lignes);

        System.out.println("*************************************");
        System.out.println("Step 1");
        System.out.println("*************************************");

        List<Deque<String>> stacksDeque = readStacks(lignes);
        computeMoves(moves,stacksDeque);
        StringBuilder result = new StringBuilder();
        for (Deque<String> stack : stacksDeque){
            result.append(stack.peekLast());
        }
        System.out.println("Top crates : " + result);


        System.out.println("*************************************");
        System.out.println("Step 2");
        System.out.println("*************************************");

        List<List<String>> stacksList = readStacksList(lignes);
        computeMovesList(moves,stacksList);
        StringBuilder resultPart2 = new StringBuilder();
        for (List<String> stack : stacksList){
            resultPart2.append(stack.get(stack.size()-1));
        }
        System.out.println("Top crates : " + resultPart2);

    }

    private static List<Deque<String>> readStacks(List<String> input){
        List<String> inputStacks = input.stream().filter(line-> line.startsWith("[")).collect(Collectors.toList());
        List<Deque<String>> stacks = new ArrayList<>();
        for (int i=0;i<9;i++){
            stacks.add(new ArrayDeque<>());
        }
        for (String line: inputStacks){
            for (int i=0;i<line.length();i+=4){
                if(line.charAt(i)=='['){
                    stacks.get(i/4).addFirst(String.valueOf(line.charAt(i+1)));
                }
            }
        }
        return stacks;
    }

    private static List<String> readMoves(List<String> input){
        return input.stream().filter(line->line.startsWith("move")).collect(Collectors.toList());
    }

    private static void computeMoves(List<String> moves, List<Deque<String>> inputStacks){
        moves.stream().forEach(move-> computeMove(move,inputStacks));
    }

    private static void computeMove(String move, List<Deque<String>> inputStacks){
        String[] instructions = move.split("\\s");
        for (int i =0;i<Integer.parseInt(instructions[1]);i++){
            inputStacks.get(Integer.parseInt(instructions[5])-1).addLast(inputStacks.get(Integer.parseInt(instructions[3])-1).pollLast());
        }
    }

    private static List<List<String>> readStacksList(List<String> input){
        List<String> inputStacks = input.stream().filter(line-> line.startsWith("[")).collect(Collectors.toList());
        List<List<String>> stacks = new ArrayList<>();
        for (int i=0;i<9;i++){
            stacks.add(new ArrayList<>());
        }
        for (String line: inputStacks){
            for (int i=0;i<line.length();i+=4){
                if(line.charAt(i)=='['){
                    stacks.get(i/4).add(0,String.valueOf(line.charAt(i+1)));
                }
            }
        }
        return stacks;
    }

    private static void computeMovesList(List<String> moves, List<List<String>> inputStacks){
        moves.stream().forEach(move-> computeMoveList(move,inputStacks));
    }

    private static void computeMoveList(String move, List<List<String>> inputStacks){
        String[] instructions = move.split("\\s");
        int indexFrom=inputStacks.get(Integer.parseInt(instructions[3])-1).size()-Integer.parseInt(instructions[1]);
        for (int i =0;i<Integer.parseInt(instructions[1]);i++){
            inputStacks.get(Integer.parseInt(instructions[5])-1).add(inputStacks.get(Integer.parseInt(instructions[3])-1).get(indexFrom));
            inputStacks.get(Integer.parseInt(instructions[3])-1).remove(indexFrom);
        }
    }

}
