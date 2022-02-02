package search.strategy;

import java.util.List;
import java.util.Map;

public interface SearchRule {
    void execute(List<String> personArray, Map<String, List<Integer>> invertedIndex, String query);
}
