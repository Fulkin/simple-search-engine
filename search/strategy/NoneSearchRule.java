package search.strategy;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Fulkin
 * Created on 02.02.2022
 */

public class NoneSearchRule implements SearchRule {
    @Override
    public void execute(List<String> personArray, Map<String, List<Integer>> invertedIndex, String query) {
        String[] words = query.split("\\s+");
        Set<Integer> noneSet = new HashSet<>();

        for (String word : words) {
            word = word.toLowerCase();
            if (invertedIndex.containsKey(word)) {
                noneSet.addAll(invertedIndex.get(word));
            }
        }

        if (noneSet.isEmpty()) {
            System.out.println("No matching person found.");
        } else {
            System.out.println((personArray.size() - noneSet.size()) + " persons found:");
            for (int i = 0; i < personArray.size(); i++) {
                if (!noneSet.contains(i)) {
                    System.out.println(personArray.get(i));
                }
            }
            System.out.println();
        }
    }
}
