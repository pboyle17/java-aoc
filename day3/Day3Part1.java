package day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Day3Part1 {
  public static void main(String[] args) throws IOException {
    var input = Files.readString(Path.of("./day3/day3.txt"));
    ArrayList<ArrayList<String>> engineSchematic = new ArrayList<>();
    ArrayList<Integer> symbolCoordinates = new ArrayList<Integer>();
    ArrayList<Integer> partNumbers = new ArrayList<Integer>();
    Set<Character> uniqueSymbols = new HashSet<>();

    input.lines().forEach(line -> {
      ArrayList<String> lineList = new ArrayList<>(Arrays.asList(line.split("")));

      engineSchematic.add(lineList);

      lineList.forEach(character -> {
        if (!character.contains(".") && !character.matches("\\d")) {
          uniqueSymbols.add(character.toCharArray()[0]);
        }
      });
    });

    for (int column = 0; column < engineSchematic.size(); column++) {
      for (int row = 0; row < engineSchematic.get(row).size(); row++) {
        System.out.println("inside loopception");
      }
    }

    System.out.println(uniqueSymbols);
    System.out.println("DONE!");
  }
}
