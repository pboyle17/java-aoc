package day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Day3Part1 {
  public static void main(String[] args) throws IOException {
    var input = Files.readString(Path.of("./day3/day3.txt"));
    ArrayList<ArrayList<String>> engineSchematic = new ArrayList<>();
    ArrayList<ArrayList<Integer>> symbolCoordinates = new ArrayList<ArrayList<Integer>>();
    Set<Integer> partNumbers = new HashSet<>();
    Set<String> uniqueSymbols = new HashSet<>();
    Integer[][] directions = {
      {-1, -1},
      {-1, 0},
      {-1, 1},
      {0, -1},
      {0, 1},
      {1, -1},
      {1, 0},
      {1, 1}
    };

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
    partNumbers = findPartNumbers(symbolCoordinates, directions, engineSchematic);

    System.out.println(partNumbers.stream().collect(Collectors.summingInt(Integer::intValue)));
  }

  private static HashSet<Integer> findPartNumbers(ArrayList<ArrayList<Integer>> symbolCoordinates,
    Integer[][] directions, ArrayList<ArrayList<String>> engineSchematic) {


    HashSet<Integer> partNumbers = new HashSet<>();

    symbolCoordinates.forEach(coordinate -> {
      Integer yCoordinate = coordinate.get(0);
      Integer xCoordinate = coordinate.get(1);

      for (Integer[] direction : directions) {
        Integer xDirection = direction[0];
        Integer yDirection = direction[1];

        if (isOutOfBounds(yCoordinate, xCoordinate, yDirection, xDirection)) {
          continue;
        }

        Integer yPoint = yCoordinate + yDirection;
        Integer xPoint = xCoordinate + xDirection;

        String character = engineSchematic.get(yPoint).get(xPoint);

        if (character.matches("\\d")) {
          if (yankPartNumber(xPoint, yPoint, engineSchematic) == null) continue;

          partNumbers.add(yankPartNumber(xPoint, yPoint, engineSchematic));
        }
      }
    });

    return partNumbers;
  }

  private static Integer yankPartNumber(Integer xPoint, Integer yPoint, ArrayList<ArrayList<String>> engineSchematic) {
    ArrayList<String> partNumberArray = new ArrayList<>();

    partNumberArray.add(engineSchematic.get(yPoint).get(xPoint));

    partNumberArray = seek("right", engineSchematic, xPoint, yPoint, partNumberArray);
    partNumberArray = seek("left", engineSchematic, xPoint, yPoint, partNumberArray);

    if (partNumberArray.size() == 1) return null;

    return Integer.parseInt(String.join("", partNumberArray.stream().map(Object::toString).toArray(String[]::new)));
  }



  private static ArrayList<String> seek(String direction, ArrayList<ArrayList<String>> engineSchematic, Integer xPoint, Integer yPoint,
      ArrayList<String> partNumberArray) {

    switch(direction) {
      case "left":
        Integer leftIndex = -1;

        if (isOutOfBounds(yPoint, xPoint, 0, -1)) break;

        while (engineSchematic.get(yPoint).get(xPoint + leftIndex).matches("\\d")) {
          partNumberArray.add(0, engineSchematic.get(yPoint).get(xPoint + leftIndex));

          leftIndex -= 1;

          if (isOutOfBounds(yPoint, xPoint, 0, leftIndex)) break;
        }

        break;
      case "right":
        Integer rightIndex = 1;

        if (isOutOfBounds(yPoint, xPoint, 0, 1)) break;

        while (engineSchematic.get(yPoint).get(xPoint + rightIndex).matches("\\d")) {
          partNumberArray.add(engineSchematic.get(yPoint).get(xPoint + rightIndex));

          rightIndex += 1;

          if (isOutOfBounds(yPoint, xPoint, 0, rightIndex)) break;
        }

        break;
    }

    return partNumberArray;
  }

  private static boolean isOutOfBounds(Integer yCoordinate, Integer xCoordinate, Integer yDirection, Integer xDirection) {
    if (xCoordinate + xDirection < 0) return true;
    if (xCoordinate + xDirection > 139) return true;
    if (yCoordinate + yDirection < 0) return true;
    if (yCoordinate + yDirection > 139) return true;

    return false;
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
