package day2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class Day2Part2 {
  static ArrayList<Integer> powerMinCubes = new ArrayList<>();

  public static void main(String ... args) throws Exception {
    var input = Files.readString(Path.of("./day2/day2.txt"));

    input.lines().forEach(line -> {
      String[] reveals = getReveals(line);
      ArrayList<Integer> gameColorMaxes = new ArrayList<>();

      for (String color : colors()) {
        gameColorMaxes.add(getColorMax(reveals, color));
      }

      powerMinCubes.add(gameColorMaxes.stream().reduce(1, (a, b) -> a * b));
    });

    System.out.println(powerMinCubes.stream().collect(Collectors.summingInt(Integer::intValue)));
  }

  private static String[] getReveals(String gameString) {
    return gameString.split(":")[1].split(";");
  }

  private static Integer getColorMax(String[] reveals, String color) {
    ArrayList<Integer> colorReveals = new ArrayList<>();

    for (String reveal : reveals) {
      String[] counts = reveal.split(",");

      for (String count : counts) {
        if (!count.contains(color)) {
          continue;
        }

        colorReveals.add(Integer.parseInt(count.replaceAll("\\D", "")));
      }
    }

    return Collections.max(colorReveals);
  }

  private static String[] colors() {
    String[] colors = {"red", "blue", "green"};

    return colors;
  }
}
