/**
 * The MultipleKnapsack class orchestrates the entire solving process.
 *
 * It doesn't implement the algorithms itself; instead, it uses:
 * - GreedySolver to get an initial solution quickly.
 * - NeighborhoodSearch to improve that solution.
 *
 * Steps:
 * 1. Call GreedySolver to get a feasible starting solution.
 * 2. Print the initial solution value.
 * 3. Call NeighborhoodSearch to try to improve the solution by small changes.
 * 4. Print the improved solution value.
 */
public class MultipleKnapsack {

    Item[] items;     // The items available
    double[] capacities; // The capacities of each knapsack
    Solution solution;   // The current best solution found

    /**
     * Constructor for the MultipleKnapsack solver.
     * @param items The set of items we can choose from.
     * @param capacities The capacities of each knapsack.
     */
    public MultipleKnapsack(Item[] items, double[] capacities) {
        this.items = items;
        this.capacities = capacities;
        this.solution = new Solution(items, capacities);
    }

    /**
     * Solve the MKP by:
     * - Running the greedy solver to get an initial solution.
     * - Running neighborhood search to improve it.
     */
    public void solve() {
        // Get initial solution using a greedy approach
        GreedySolver.solveGreedy(items, solution);
        System.out.println("Initial solution value (greedy): " + solution.computeTotalValue());

        // Improve the solution using neighborhood search
        NeighborhoodSearch.runNeighborhoodSearch(solution);
        System.out.println("Improved solution value: " + solution.computeTotalValue());
    }

    /**
     * Get the final solution after improvements.
     * @return The final improved solution.
     */
    public Solution getSolution() {
        return solution;
    }
}
