package day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day4Part2 {
  public static void main(String[] args) throws IOException {
    String input = Files.readString(Path.of("./day4/day4.txt"));
    ArrayList<Integer> matches = new ArrayList<>();
    ArrayList<Integer> totalCardsArrayWithCopies = new ArrayList<>();

    input.lines().forEach(line -> {
      List<Integer> cardNumbers = getCardNumbers(line);
      List<Integer> winningNumbers = getWinningNumbers(line);
      ArrayList<Integer> matchingNumbers = getMatchingNumbers(cardNumbers, winningNumbers);

      matches.add(matchingNumbers.size());
    });

    int index = 0;
    for (Integer match: matches) {
      int cardId = index + 1;

      totalCardsArrayWithCopies.add(cardId);

      for (int i = 1; i <= match; i++) {
        for(int j = 1; j <= Collections.frequency(totalCardsArrayWithCopies, cardId); j++) {
          totalCardsArrayWithCopies.add(cardId + i);
          System.out.println(String.format("Got copy of Card %s", cardId + i));
        }
      }

      index++;
    }

    System.out.println(Arrays.toString(totalCardsArrayWithCopies.toArray()));
    System.out.println(Arrays.toString(matches.toArray()));
    System.out.println(totalCardsArrayWithCopies.size());
  }


  private static ArrayList<Integer> getMatchingNumbers(List<Integer> cardNumbers, List<Integer> winningNumbers) {
    Set<Integer> cardNumbersSet = new HashSet<Integer>(cardNumbers);
    Set<Integer> winningNumbersSet = new HashSet<Integer>(winningNumbers);

    winningNumbersSet.retainAll(cardNumbersSet);

    return new ArrayList<Integer>(winningNumbersSet);
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
