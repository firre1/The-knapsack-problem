import java.util.Arrays;
import java.util.Comparator;

/**
 * The GreedySolver class builds an initial solution quickly using a simple strategy:
 *
 * The "greedy" strategy here:
 * 1. Compute the density (value/weight) for each item.
 * 2. Sort items in descending order of density (most "valuable per weight" first).
 * 3. Try to place each item in the first knapsack it fits.
 *
 * This does not guarantee the best solution, but it gives a good starting point.
 * Later, the neighborhood search can improve this initial solution.
 */
public class GreedySolver {

    /**
     * Build an initial solution to the MKP using the greedy approach.
     *
     * @param items The items available.
     * @param sol The solution where we'll store the result.
     */
    public static void solveGreedy(Item[] items, Solution sol) {
        // Copy items so we can sort them without altering the original array
        Item[] sortedItems = Arrays.copyOf(items, items.length);

        // Sort items by descending density (value/weight)
        Arrays.sort(sortedItems, new Comparator<Item>() {
            public int compare(Item a, Item b) {
                return Double.compare(b.density, a.density);
            }
        });

        // Place each item in the first knapsack where it fits
        for (Item it : sortedItems) {
            for (int k = 0; k < sol.capacities.length; k++) {
                if (it.weight <= sol.capacities[k]) {
                    sol.assignItem(it.id, k);
                    break; // move on to the next item once placed
                }
            }
        }
    }
}
