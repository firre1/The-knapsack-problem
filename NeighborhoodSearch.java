/**
 * The NeighborhoodSearch class attempts to improve a given solution using local search.
 *
 * The idea of local (neighborhood) search is:
 * 1. Start from a given solution (from greedy or any method).
 * 2. Look at "neighboring" solutions that differ slightly (e.g., swapping one item).
 * 3. If a neighbor has a better total value, move to that neighbor.
 * 4. Repeat until no better neighbor is found.
 *
 * Here we implement a simple neighborhood:
 * - We try swapping an item currently chosen in a knapsack with one that is not chosen.
 * - If the swap increases the total value, we do it; otherwise, we revert and try others.
 *
 * More sophisticated neighborhoods can be considered, but this simple approach
 * often improves the solution from the greedy step.
 */
public class NeighborhoodSearch {

    /**
     * Attempt to improve the solution by checking all pairs of "in-solution" and "out-of-solution" items.
     * If replacing one with the other increases total value and fits in the knapsack, do it.
     *
     * @param current The current solution we are trying to improve.
     * @return True if an improvement was made, false if not.
     */
    public static boolean improveSolution(Solution current) {
        double currentValue = current.computeTotalValue();
        Item[] items = current.items;

        // Consider every item currently in the knapsack solution
        for (int iOut = 0; iOut < items.length; iOut++) {
            int outK = current.getKnapsackOfItem(iOut);
            if (outK == -1) continue; // skip items not in the solution

            // Consider every item not currently chosen
            for (int iIn = 0; iIn < items.length; iIn++) {
                if (iIn == iOut) continue; // don't compare the same item
                if (current.getKnapsackOfItem(iIn) != -1) continue; // iIn must be out of the solution

                // Check if swapping iOut with iIn improves the solution
                double oldValue = currentValue;
                double gain = items[iIn].value - items[iOut].value;

                // Only attempt this swap if it can improve total value
                if (gain <= 0) {
                    continue;
                }

                // Temporarily remove iOut to free capacity
                current.unassignItem(iOut);

                // Check if iIn now fits into outK
                if (current.canAssign(iIn, outK)) {
                    // Assign iIn in the spot of iOut
                    current.assignItem(iIn, outK);
                    double newValue = current.computeTotalValue();
                    if (newValue > oldValue) {
                        // Improvement found, keep it!
                        return true;
                    } else {
                        // Not actually better, revert changes
                        current.unassignItem(iIn);
                        current.assignItem(iOut, outK);
                    }
                } else {
                    // iIn doesn't fit, revert iOut removal
                    current.assignItem(iOut, outK);
                }
            }
        }
        // If we reach here, no improvement was found
        return false;
    }

    /**
     * Keep running the neighborhood search until no improvement is found.
     * This repeats improveSolution until we get stuck in a local optimum
     * (no single swap can yield a better solution).
     *
     * @param sol The solution to improve.
     */
    public static void runNeighborhoodSearch(Solution sol) {
        boolean improved = true;
        // Repeatedly try to improve until it's no longer possible
        while (improved) {
            improved = improveSolution(sol);
        }
    }
}
