import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Zen-Laptop on 2/18/2016.
 */

public class RegexTester {

    // Iterator Function for printing first eleven entries in a map entry set.
    // Used twice in main method, so moved out here for reuse.

    public static void firstElevenEntries(Set<Map.Entry<String,Integer>> set) {
        Iterator<Map.Entry<String,Integer>> iter = set.iterator();
        for (int i = 0; i < 11; ++i) {
            Map.Entry<String,Integer> entry = iter.next();
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    // Main Method

    public static void main(String[] args) throws IOException {
        Map<String, Integer> wordCount = new HashMap<>();

        // File Input
        File inFile = new File(args[0]);
        Scanner in = new Scanner(inFile);

        // File Read
        while (in.hasNextLine()) {
            for (String word : in.nextLine().split("\\s+")) {
                if (word.matches(".*[aeiou]{3}.*")) {
                    word = word.replaceAll("[?.,'!;:-]", "").toLowerCase();
                    wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                }
            }
        }

        // Print out first 11 entries of our unordered map.
        System.out.println("Assignment: First 11 Words Listed");
        firstElevenEntries(wordCount.entrySet());

        // Create an ordered map by implementing Comparator.
        System.out.println("\n\n\nExtra Credit: Top 11 Words Used");
        Map<String, Integer> orderedWordCount = new TreeMap<> (
                new Comparator<String>() {
                    public int compare(String a, String b)
                    {
                        if (wordCount.get(a) > wordCount.get(b))
                        {
                            return -1;
                        }
                        return 1;
                    }
                }
        );

        // Copy all entries from unordered map to ordered map.
        orderedWordCount.putAll(wordCount);

        // Print out first 11 entries of ordered map.
        firstElevenEntries(orderedWordCount.entrySet());
    }
}