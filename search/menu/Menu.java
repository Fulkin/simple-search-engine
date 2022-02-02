package search.menu;

import search.strategy.AllSearchRule;
import search.strategy.AnySearchRule;
import search.strategy.NoneSearchRule;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author Fulkin
 * Created on 01.02.2022
 */

public class Menu {
    static Scanner sc;
    static List<String> personArray;
    static Map<String, List<Integer>> invertedIndex;

    static {
        sc = new Scanner(System.in);
        invertedIndex = new HashMap<>();
    }

    public static void start(String[] args) {
        if (args.length > 1 && "--data".equals(args[0])) {
            takeData(args[1]);
        }
        mainMenu();
        System.out.println("Bye!");
    }

    private static void takeData(String fileName) {
        personArray = new ArrayList<>();
        int lineNumber = 0;
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String personLine = scanner.nextLine();
                personArray.add(personLine);
                String[] words = personLine.toLowerCase().split(" ");
                for (String word : words) {
                    if (!invertedIndex.containsKey(word)) {
                        List<Integer> occursLines = new ArrayList<>();
                        occursLines.add(lineNumber);
                        invertedIndex.put(word, occursLines);
                    } else {
                        invertedIndex.get(word).add(lineNumber);
                    }
                }
                lineNumber++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void mainMenu() {
        while (true) {
            System.out.println("" +
                    "=== Menu ===\n" +
                    "1. Find a person\n" +
                    "2. Print all people\n" +
                    "0. Exit");
            int a = Integer.parseInt(sc.nextLine().trim());
            System.out.println();
            if (a == 1) {
                findPerson();
            } else if (a == 2) {
                listPerson();
            } else if (a == 0) {
                return;
            } else {
                System.out.println("Incorrect option! Try again.\n");
            }
        }
    }

    private static void listPerson() {
        StringBuilder sb = new StringBuilder("=== List of people ===\n");
        for (String s : personArray) {
            sb.append(s).append("\n");
        }
        System.out.println(sb);
    }

    private static void findPerson() {
        System.out.println("Select a matching strategy: ALL, ANY, NONE");
        String strategy = sc.nextLine().toUpperCase();
        System.out.println("\nEnter a name or email to search all suitable people.");
        String searchWord = sc.nextLine().toLowerCase();

        switch (strategy) {
            case "ALL":
                new AllSearchRule().execute(personArray, invertedIndex, searchWord);
                break;
            case "ANY":
                new AnySearchRule().execute(personArray, invertedIndex, searchWord);
                break;
            case "NONE":
                new NoneSearchRule().execute(personArray, invertedIndex, searchWord);
                break;
        }
    }
}
