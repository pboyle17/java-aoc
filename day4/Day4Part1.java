package day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day4Part1 {
 public static void main(String[] args) throws IOException {
  String input = Files.readString(Path.of("./day4/day4.txt"));

  input.lines().forEach(line -> {
    System.out.println(line);
  });

 }
}
