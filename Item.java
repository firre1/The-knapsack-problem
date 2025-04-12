/**
 * The Item class represents a single item that can potentially be placed in a knapsack.
 * Each item has:
 * - An identifier (id): a unique number assigned to distinguish items.
 * - A value (p_i): how valuable the item is.
 * - A weight (w_i): how heavy the item is.
 *
 * We also store the "density" (value/weight) for convenience, as the greedy algorithm
 * uses this to prioritize items.
 */
public class Item {
    int id;       // Unique identifier for the item
    double value; // The item's value (the "profit" gained if we include this item)
    double weight;// The item's weight (consumes capacity from a knapsack)
    double density; // Computed as value/weight, used by the greedy algorithm

    /**
     * Constructor for an Item.
     * @param id A unique identifier for the item.
     * @param value The value (profit) of the item.
     * @param weight The weight of the item.
     */
    public Item(int id, double value, double weight) {
        this.id = id;
        this.value = value;
        this.weight = weight;
        this.density = value / weight; // Higher density means "better value per unit weight"
    }
}
