import java.nio.file.Files;
import java.nio.file.Path;

class Day1Part1 {
  public static void main(String ... args) throws Exception {
    var input = Files.readString(Path.of("./day1.txt"));

    Integer sum = input.lines().map(line -> {
      String[] filteredArray = line.replaceAll("[^0-9]", "").split("");

      String firstDigit = filteredArray[0];
      String lastDigit = filteredArray[filteredArray.length - 1];

      return Integer.parseInt(String.format("%s%s", firstDigit, lastDigit));
    }).reduce(0, Integer::sum);

    System.out.print(sum);
  };
}
