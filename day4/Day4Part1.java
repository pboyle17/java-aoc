package day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day4Part1 {
  public static void main(String[] args) throws IOException {
    String input = Files.readString(Path.of("./day4/day4.txt"));
    ArrayList<Integer> totalPoints = new ArrayList<>();

    input.lines().forEach(line -> {
      List<Integer> cardNumbers = getCardNumbers(line);
      List<Integer> winningNumbers = getWinningNumbers(line);
      ArrayList<Integer> matchingNumbers = getMatchingNumbers(cardNumbers, winningNumbers);
      Integer cardScore = getCardPoints(matchingNumbers);

      totalPoints.add(cardScore);
    });

    System.out.println(totalPoints.stream().collect(Collectors.summingInt(Integer::intValue)));
  }


  private static ArrayList<Integer> getMatchingNumbers(List<Integer> cardNumbers, List<Integer> winningNumbers) {
    Set<Integer> cardNumbersSet = new HashSet<Integer>(cardNumbers);
    Set<Integer> winningNumbersSet = new HashSet<Integer>(winningNumbers);

    winningNumbersSet.retainAll(cardNumbersSet);

    return new ArrayList<Integer>(winningNumbersSet);
  }

  private static Integer getCardPoints(ArrayList<Integer> matchingNumbers) {
    if (matchingNumbers.size() == 0) return 0;

    return (int) Math.pow(2, matchingNumbers.size() - 1);
  }

  private static List<Integer> getWinningNumbers(String line) {
    String winningNumbersString = String.join("",line.split(":")[1].split("\\|")[0]);
    List<Integer> winningNumbersList = Pattern.compile("\\d*\\s").matcher(winningNumbersString)
                                              .results()
                                              .map(MatchResult::group)
                                              .map(num -> num.trim())
                                              .filter(num -> !num.isEmpty())
                                              .map(num -> Integer.parseInt(num))
                                              .collect(Collectors.toList());


    return winningNumbersList;
  }

  private static List<Integer> getCardNumbers(String line) {
    String cardNumberString = String.join("",line.split(":")[1].split("\\|")[1]) + " ";
    List<Integer> cardNumbersList = Pattern.compile("\\d*\\s").matcher(cardNumberString)
                                              .results()
                                              .map(MatchResult::group)
                                              .map(num -> num.trim())
                                              .filter(num -> !num.isEmpty())
                                              .map(num -> Integer.parseInt(num))
                                              .collect(Collectors.toList());


    return cardNumbersList;
  }
}
