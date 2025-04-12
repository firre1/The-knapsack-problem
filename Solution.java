import java.util.Arrays;

/**
 * The Solution class represents a feasible solution to the Multiple Knapsack Problem (MKP).
 *
 * A "solution" means we have decided which items go into which knapsacks,
 * or if an item is not included at all.
 *
 * Important details:
 * - assignment[i]: Indicates which knapsack item i goes into, or -1 if it's not used.
 * - capacities[]: Tracks how much capacity remains in each knapsack after placing items.
 * - items[]: A reference to all items, so we know their values and weights.
 * - originalCapacities[]: The initial capacities of the knapsacks before placing any items.
 *
 * This class provides methods to:
 * - Calculate the total value of the solution.
 * - Assign and unassign items to knapsacks (updating remaining capacities).
 * - Check if an item can fit in a knapsack.
 * - Clone the solution (useful for "undoing" changes if needed).
 *
 * This class does not decide how to build or improve the solution. Instead, it provides
 * a convenient interface for managing the current state of the solution.
 */
public class Solution {
    int[] assignment;           // assignment[i] = knapsack id for item i, or -1 if not included
    double[] capacities;        // remaining capacities of each knapsack
    Item[] items;               // reference to the array of all items
    double[] originalCapacities;// store the original knapsack capacities for reference

    /**
     * Creates a new solution object.
     * Initially, no items are assigned (-1 for all).
     *
     * @param items The array of items available.
     * @param knapsackCapacities The capacities for each knapsack.
     */
    public Solution(Item[] items, double[] knapsackCapacities) {
        this.items = items;
        this.assignment = new int[items.length];
        Arrays.fill(assignment, -1); // start with all items unassigned
        this.capacities = Arrays.copyOf(knapsackCapacities, knapsackCapacities.length);
        this.originalCapacities = Arrays.copyOf(knapsackCapacities, knapsackCapacities.length);
    }

    /**
     * Compute the total value of the current solution.
     * This adds up the values of all items that are assigned to any knapsack.
     * @return The sum of the values of all assigned items.
     */
    public double computeTotalValue() {
        double total = 0.0;
        for (int i = 0; i < items.length; i++) {
            if (assignment[i] != -1) { // if the item is assigned
                total += items[i].value; // add its value
            }
        }
        return total;
    }

    /**
     * Assign an item to a specific knapsack.
     * This updates the assignment and decreases the knapsack's remaining capacity.
     * We assume the caller has checked feasibility before calling this.
     *
     * @param itemId The item to be assigned.
     * @param knapsackId The knapsack to which the item should be assigned.
     */
    public void assignItem(int itemId, int knapsackId) {
        // If the item was previously assigned to a different knapsack, unassign it first
        if (assignment[itemId] != -1) {
            int oldK = assignment[itemId];
            capacities[oldK] += items[itemId].weight; // restore old capacity
        }

        // Assign the item to the new knapsack
        assignment[itemId] = knapsackId;
        capacities[knapsackId] -= items[itemId].weight; // reduce the knapsack's capacity
    }

    /**
     * Unassign an item from its current knapsack.
     * This frees up capacity in that knapsack.
     *
     * @param itemId The item to unassign.
     */
    public void unassignItem(int itemId) {
        if (assignment[itemId] != -1) {
            int k = assignment[itemId];
            capacities[k] += items[itemId].weight; // give back the capacity
            assignment[itemId] = -1; // now the item is not in any knapsack
        }
    }

    /**
     * Check if there's enough capacity in a given knapsack for a specific item.
     *
     * @param itemId The item we want to put in the knapsack.
     * @param knapsackId The knapsack we are considering.
     * @return True if the item fits, false if not.
     */
    public boolean canAssign(int itemId, int knapsackId) {
        return items[itemId].weight <= capacities[knapsackId];
    }

    /**
     * Get the knapsack in which an item is currently placed.
     * @param itemId The item in question.
     * @return The id of the knapsack or -1 if the item isn't assigned.
     */
    public int getKnapsackOfItem(int itemId) {
        return assignment[itemId];
    }

    /**
     * Create a copy of the current solution.
     * Cloning is useful if we want to try changes and revert if needed.
     * @return A new Solution object identical to the current one.
     */
    @Override
    public Solution clone() {
        Solution s = new Solution(items, originalCapacities);
        s.assignment = Arrays.copyOf(this.assignment, this.assignment.length);
        s.capacities = Arrays.copyOf(this.capacities, this.capacities.length);
        return s;
    }
}
