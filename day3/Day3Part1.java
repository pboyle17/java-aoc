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
    ArrayList<ArrayList<Integer>> symbolCoordinates = new ArrayList<ArrayList<Integer>>();
    ArrayList<Integer> partNumbers = new ArrayList<Integer>();
    Set<String> uniqueSymbols = new HashSet<>();

    input.lines().forEach(line -> {
      ArrayList<String> lineList = new ArrayList<>(Arrays.asList(line.split("")));

      engineSchematic.add(lineList);

      lineList.forEach(character -> {
        if (!character.contains(".") && !character.matches("\\d")) {
          uniqueSymbols.add(character);
        }
      });
    });

   symbolCoordinates = processSymbolCoordinates(engineSchematic, uniqueSymbols);

    System.out.println("DONE!");
  }

  private static ArrayList<ArrayList<Integer>> processSymbolCoordinates(ArrayList<ArrayList<String>> engineSchematic,
    Set<String> uniqueSymbols) {
      ArrayList<ArrayList<Integer>> symbolCoordinates = new ArrayList<ArrayList<Integer>>();

      for (int row = 0; row < engineSchematic.size(); row++) {
        for (int column = 0; column < engineSchematic.get(row).size(); column++) {
          if (uniqueSymbols.contains(engineSchematic.get(row).get(column))) {
            ArrayList<Integer> coordinates = new ArrayList<>();

            coordinates.add(row);
            coordinates.add(column);

            symbolCoordinates.add(coordinates);
          }
        }
      }

    return symbolCoordinates;
  }
}
