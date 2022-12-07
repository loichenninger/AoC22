package fr.n1g.aoc22.days.day7;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FileSystem {

    private final List<FileSystemElement> elements;
    private Path currentPath;

    public FileSystem(List<String> data) {
        currentPath = Path.of(File.separator);
        elements = new ArrayList<>();
        for (String line : data) {
            if (line.startsWith("$ cd")) {
                setPath(line.split(" ")[2]);
            } else if (!line.startsWith("$ ls")) {
                elements.add(new FileSystemElement(line, currentPath));
            }
        }
    }

    private void setPath(String goTo) {
        if (goTo.equals("..")) {
            currentPath = currentPath.getParent();
        } else if (goTo.equals("/")) {
            currentPath = Path.of(File.separator);
        } else {
            if (currentPath.toString().equals(File.separator)){
                currentPath = Path.of(currentPath.toString() + goTo);
            } else {
                currentPath = Path.of(currentPath.toString() + File.separator + goTo);
            }
        }
    }

    public int getPathContentSize(Path path) {
        int size = 0;
        for (FileSystemElement element : elements) {
            if (element.getPath().toString().contains(path.toString())) {
                size += element.getSize();
            }
        }
        return size;
    }

    public Set<Path> getElfFileSystemPaths() {
        return elements.stream().map(FileSystemElement::getPath).collect(Collectors.toSet());
    }
}
