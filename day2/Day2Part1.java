package day2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class Day2Part1 {
  static ArrayList<Integer> impossibleGames = new ArrayList<>();

  public static void main(String ... args) throws Exception {
    var input = Files.readString(Path.of("./day2/day2.txt"));

    input.lines().forEach(line -> {
      Integer id = getIdFromGameString(line);
      String[] reveals = getReveals(line);

      for (String color : colors()) {
        Integer colorMax = getColorMax(reveals, color);

        if (colorMax > mapColorToMax(color)) {
          impossibleGames.add(id);

          break;
        }
      }
    });

    System.out.println(String.format("%s", sumOfPossibleGames(impossibleGames)));
  }

  private static Integer getIdFromGameString(String gameString) {
    return Integer.parseInt(gameString.split(":")[0].replaceAll("\\D", ""));
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

  private static Integer mapColorToMax(String color) {
    Integer max;

    switch (color) {
      case "red":
        max = 12;
        break;
      case "green":
        max = 13;
        break;
      case "blue":
        max = 14;
        break;
      default:
        max = 0;
    }

    return max;
  }

  private static Integer sumOfPossibleGames(ArrayList<Integer> impossibleGames) {
    Integer sumOfImpossibleGames = impossibleGames.stream().collect(Collectors.summingInt(Integer::intValue));

    return 5050 - sumOfImpossibleGames;
  }
}
