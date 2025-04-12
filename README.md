# Multiple Knapsack Problem Solver (Greedy + Neighborhood Search)

This Java project solves a small instance of the **Multiple Knapsack Problem (MKP)** using two techniques:

1. **Greedy Algorithm** – quickly builds an initial solution based on item density (value per weight).
2. **Neighborhood Search** – improves the initial solution by swapping items to increase the total value.

---

## What Is the Multiple Knapsack Problem?

Given multiple knapsacks with specific capacities and a set of items (each with a value and weight), the goal is to assign items to knapsacks such that:
- The total value is maximized.
- The weight of items in each knapsack does not exceed its capacity.
- Each item can be placed in at most one knapsack or left out.

---

## Features

- Greedy algorithm for fast initial solution
- Local neighborhood search to improve results
- Java implementation with detailed logging
- Simple example with 6 items and 2 knapsacks

---

## Project Structure

| File | Purpose |
|------|---------|
| `Main.java` | Runs the solver and prints results |
| `Item.java` | Represents each item (id, value, weight, density) |
| `Solution.java` | Manages assignments and capacity tracking |
| `GreedySolver.java` | Implements the greedy approach |
| `NeighborhoodSearch.java` | Improves solution using local swaps |
| `MultipleKnapsack.java` | Orchestrates the solving process |

