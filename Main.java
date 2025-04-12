/**
 * The Main class is the entry point of the program.
 *
 * In this example, we:
 * 1. Define a small multiple knapsack problem with:
 *    - A set of items (each with a value and a weight)
 *    - A set of knapsacks (each with a certain capacity)
 *
 * 2. Use a greedy algorithm to find an initial feasible solution.
 *    The greedy algorithm sorts items by their value/weight ratio (density)
 *    and tries to put the most "valuable per unit weight" items in the knapsacks first.
 *
 * 3. Use a neighborhood search algorithm to improve the initial solution.
 *    The neighborhood search tries to find better solutions by making small changes
 *    (e.g., swapping items) to see if the total value can be increased.
 *
 * Finally, we print the results, including:
 * - The initial solution's value.
 * - The improved solution's value after local search.
 * - Which items ended up in which knapsack.
 *
 */
public class Main {
    public static void main(String[] args) {
        // Define a small example problem instance.
        // Here, we have 6 items:
        //  - Each item has an ID, a value, and a weight.
        //  - For example, item 0 has a value of 50 and a weight of 10.

        Item[] items = new Item[] {
                new Item(0, 35, 10),
                new Item(1, 50, 12),
                new Item(2, 45, 15),
                new Item(3, 60, 10),
                new Item(4, 55, 11),
                new Item(5, 30, 6)
        };

        // We have 2 knapsacks, each with a capacity of 20.
        double[] capacities = new double[] {20, 20};

        // Create a solver that will:
        // 1. Construct a solution using a greedy algorithm.
        // 2. Attempt to improve that solution using neighborhood search.
        MultipleKnapsack solver = new MultipleKnapsack(items, capacities);

        // The solve() method will first run the greedy solution and print its result,
        // then run the neighborhood search and print the improved result.
        System.out.println("Starting the Multiple Knapsack Problem Solver...");
        System.out.println("-------------------------------------------------");

        System.out.println("Problem Setup:");
        System.out.println("Number of items: " + items.length);
        System.out.println("Number of knapsacks: " + capacities.length);
        System.out.println("Knapsack capacities: ");
        for (int j = 0; j < capacities.length; j++) {
            System.out.println("  Knapsack " + j + ": Capacity " + capacities[j]);
        }

        System.out.println("\nItems (ID, Value, Weight):");
        for (Item it : items) {
            System.out.println("  Item " + it.id + ": Value=" + it.value + ", Weight=" + it.weight);
        }

        System.out.println("\nRunning the greedy algorithm to find an initial feasible solution...");
        solver.solve(); // This prints initial and improved solution values inside

        // Retrieve the final solution after improvements.
        Solution finalSol = solver.getSolution();

        System.out.println("\nFinal solution assignments (item -> knapsack):");
        System.out.println("(If the assignment is -1, the item is not placed in any knapsack.)");
        for (int i = 0; i < finalSol.assignment.length; i++) {
            System.out.println("  Item " + i + " assigned to knapsack " + finalSol.assignment[i]);
        }

        System.out.println("\nProcess complete!");
        System.out.println("You have successfully run the greedy and neighborhood search algorithms.");
    }
}
