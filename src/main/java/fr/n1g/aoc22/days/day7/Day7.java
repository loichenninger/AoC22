package fr.n1g.aoc22.days.day7;

import fr.n1g.aoc22.utils.Utils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day7 {

    private static final List<String> lignes = Utils.getStringList("src/main/resources/puzzles/puzzle7.txt");

    public static void main(String[] args) throws IOException {

        System.out.println("Nombres de lignes dans le fichier : " + lignes.size());
        FileSystem fileSystem = new FileSystem(lignes);

        System.out.println("*************************************");
        System.out.println("Step 1");
        System.out.println("*************************************");

        Map<Path, Integer> pathSize = new HashMap<>();
        fileSystem.getElfFileSystemPaths().forEach(i -> pathSize.put(i, fileSystem.getPathContentSize(i)));
        int result1 = pathSize.values().stream().filter(i -> i < 100000).reduce(0,Integer::sum);
        System.out.println("Result of part 1 : " + result1);


        System.out.println("*************************************");
        System.out.println("Step 2");
        System.out.println("*************************************");

        // Find the size of root directory (containing everything "/")
        int usedSpace = pathSize.values().stream()
                .sorted(Comparator.reverseOrder())
                .limit(1)
                .findFirst()
                .orElse(-1);

        int spaceToDelete = usedSpace - (70000000 - 30000000);

        int result2 = pathSize.values().stream().filter(i -> i >= spaceToDelete)
                .sorted()
                .limit(1).findFirst().orElse(-1);

        System.out.println("Result of part 2: " + result2);

    }

}
