package day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day5Part1 {
  public static void main(String[] args) throws IOException {
    String input = Files.readString(Path.of("./day5/day5.txt"));

    input.lines().forEach(line -> {
      System.out.println(line);
    });
  }


}
