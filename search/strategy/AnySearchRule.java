package search.strategy;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Fulkin
 * Created on 02.02.2022
 */

public class AnySearchRule implements SearchRule {
    @Override
    public void execute(List<String> personArray, Map<String, List<Integer>> invertedIndex, String query) {
        String[] words = query.split("\\s+");
        Set<Integer> anySet = new HashSet<>();
        for (String word : words) {
            word = word.toLowerCase();
            if (invertedIndex.containsKey(word)) {
                anySet.addAll(invertedIndex.get(word));
            }
        }
        if (anySet.isEmpty()) {
            System.out.println("No matching person found.");
        } else {
            System.out.println(anySet.size() + " persons found");
            for (Integer i : anySet) {
                System.out.println(personArray.get(i));
            }
            System.out.println();
        }
    }
}
