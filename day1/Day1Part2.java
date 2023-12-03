import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Day1Part2 {
  public static void main(String ... args) throws Exception {
    var input = Files.readString(Path.of("./day1.txt"));
    AtomicInteger running = new AtomicInteger(0);

    Integer sum = input.lines().map(line -> {
    Map<String, String> numberMap = new HashMap<>() {{
          put("one", "1");
          put("two", "2");
          put("three", "3");
          put("four", "4");
          put("five", "5");
          put("six", "6");
          put("seven", "7");
          put("eight", "8");
          put("nine", "9");
      }};

      Pattern regexPattern = Pattern.compile("(one|two|three|four|five|six|seven|eight|nine)");
      Matcher matcher = regexPattern.matcher(line);
      String mapped = line;

      System.out.println(line);

      while(matcher.find()) {
        mapped = mapped.replace(matcher.group(), numberMap.get(matcher.group()));
      }

      System.out.println(mapped);

      String[] filteredArray = mapped.replaceAll("[^0-9]", "").split("");

      String firstDigit = filteredArray[0];
      String lastDigit = filteredArray[filteredArray.length - 1];

      Integer number = Integer.parseInt(String.format("%s%s", firstDigit, lastDigit));

      running.addAndGet(number);
      System.out.println(number);
      System.out.println(running);

      return number;
    }).reduce(0, Integer::sum);

    System.out.println(sum);
  };
}
