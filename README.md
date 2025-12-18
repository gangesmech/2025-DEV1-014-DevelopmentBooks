# Development Books Kata

This project is a solution to the "Development Books" Kata, implemented in Java using Test Driven Development (TDD).

## Problem Description
The goal is to calculate the lowest possible price for a shopping basket of books from a specific series.
- **Base Price:** 50 EUR per book.
- **Discounts:** Applied based on the number of *distinct* books in a set.
    - 2 distinct books: 5%
    - 3 distinct books: 10%
    - 4 distinct books: 20%
    - 5 distinct books: 25%
- **Optimization:** The logic must maximize the total discount by grouping books into the most advantageous sets (e.g., two sets of 4 books are better than one set of 5 and one set of 3).

## Build and Run

This project uses Java 21 and Maven.

### Run Tests
```bash
mvn test
```

### Build Project
```bash
mvn clean install
```

## Commit Message Style
This project follows the **[Udacity Git Commit Message Style Guide](https://udacity.github.io/git-styleguide/)**.
Commits are structured as `<type>: <subject>`, using the imperative mood (e.g., `feat: Add new pricing logic`).

Common types used:
- `feat`: New feature
- `docs`: Documentation changes
- `chore`: Maintenance tasks
- `refactor`: Code refactoring

## TDD Approach
The solution was strictly built using the Red-Green-Refactor cycle:
1.  **Red:** Write a failing test for a small slice of functionality (e.g., empty basket, single book, simple discount).
2.  **Green:** Implement the minimal code to pass the test.
3.  **Refactor:** Improve code structure (e.g., introduce `Book` enum, optimize algorithms) without changing behavior.
4.  **Repeat:** Iteratively handle complex scenarios like recursion for optimal pricing.

## Design Decisions

### 1. Domain Model (`com.bnppf.kata.developmentbooks.domain`)
-   **`Book` Record:** To align with OpenAPI-generated models and support easier mapping while maintaining immutability.
-   **`ShoppingBasket`:** Encapsulates the core pricing logic. It maintains the state of selected books and exposes a `calculate()` method.

### 2. Pricing Algorithm
-   **Recursive Optimization:** The problem is a variation of the set cover/knapsack problem. A simple greedy approach (always taking the largest set) fails in specific edge cases (e.g., 2 sets of 4 vs. 1 set of 5 + 1 set of 3).
-   **Implementation:** The `calculateLowestPricePossible` method recursively explores all valid set sizes to find the global minimum price.
-   **Complexity Management:** Helper methods (like `calculatePriceForSet`) were extracted to keep the recursive logic readable and explicitly named.

### 3. Testing
-   **Parameterized Tests:** Used for verifying standard discount rules (3, 4, 5 books).
-   **Edge Cases:** Specific tests cover duplicate handling and the complex optimization scenarios.

## Code Coverage
JaCoCo is configured to enforce a **minimum of 90% instruction coverage**. The build will fail if this threshold is not met.
**Report Location:** `target/site/jacoco/index.html`

## API Reference

### 1. Get Available Books
Retrieves a list of all 5 Development Books available for purchase.

- **URL:** `/books`
- **Method:** `GET`
- **Success Response:**
  - **Code:** 200 OK
  - **Content:**
    ```json
    [
      {
        "title": "Clean Code",
        "author": "Robert C. Martin",
        "year": 2008
      },
      {
        "title": "The Clean Coder",
        "author": "Robert C. Martin",
        "year": 2011
      }
      // ... other books
    ]
    ```

### 2. Calculate Price
Calculates the lowest possible price for a submitted list of books, applying all discount rules optimally.

- **URL:** `/price`
- **Method:** `POST`
- **Request Body:**
  - A list of book objects (structure matching the `GET /books` response).
  ```json
  [
    {
      "title": "Clean Code",
      "author": "Robert C. Martin",
      "year": 2008
    },
    {
      "title": "The Clean Coder",
      "author": "Robert C. Martin",
      "year": 2011
    }
  ]
  ```
- **Success Response:**
  - **Code:** 200 OK
  - **Content:** `95.0` (Price as a double)
