package day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Day5Part1 {
  public static void main(String[] args) throws IOException {
    String input = Files.readString(Path.of("./day5/day5.txt"));
    ArrayList<Integer> seeds = getSeeds(input);
  }

  private static ArrayList<Integer> getSeeds(String input) {
    String seedsString = input.lines().filter(s -> s.contains("seeds")).collect(Collectors.joining(""));

    List<Integer> seedsList = Arrays.stream(seedsString.split(":")[1].split(" "))
                                    .filter(s -> !s.isEmpty())
                                    .collect(Collectors.toList())
                                    .stream()
                                    .map(num -> Integer.parseInt(num));

    return new ArrayList<Integer>(seedsList);
  }


}
