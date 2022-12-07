package fr.n1g.aoc22.days.day7;

import java.nio.file.Path;

public class FileSystemElement {

    private final int size;

    private final String name;
    private final Path path;

    public FileSystemElement(String fileName, Path path) {
        if (Character.isDigit(fileName.charAt(0))) {
            this.size = Integer.parseInt(fileName.split(" ")[0]);
        } else {
            this.size = 0;
        }
        this.path = path;
        this.name=fileName.split("\\s")[1];
    }

    public long getSize() {
        return this.size;
    }

    public Path getPath() {
        return this.path;
    }
}
