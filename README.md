# The MAZE (Java)

A console-based maze navigation and logic game developed in Java. This project demonstrates the fundamental principles of **Data Structures** and **Object-Oriented Programming (OOP)** by implementing core mechanics from scratch without relying on built-in Java collection libraries.

## 🚀 Key Technical Highlights

* **Custom Data Structures:** The project features manual, array-based implementations of `Stack.java` and `Queue.java`. This demonstrates a deep understanding of memory management and the "under-the-hood" logic of linear data structures.
* **Game Engine & Logic:** `Game.java` manages the 25x55 game field, handling collision detection, scoring, and real-time console rendering.
* **Pathfinding & Interaction:** `Player.java` manages coordinate-based movement and inventory logic using the custom-built stack and queue systems.
* **Decoupled Architecture:** Follows OOP principles by separating data management, game logic, and the execution entry point (`GameTest.java`).

---

## 🛠 Technology Stack

* **Language:** Java
* **Concepts:** Data Structures (Custom Stacks & Queues), Algorithm Design, OOP.
* **Environment:** Console/Terminal

---

## 📂 Project Structure

| File | Description |
| :--- | :--- |
| `Game.java` | Main engine handling the game field, rendering, and rules. |
| `Player.java` | Logic for player movement, input handling, and scoring. |
| `Stack.java` | Custom-built stack implementation for inventory/game-state management. |
| `Queue.java` | Custom-built circular queue implementation for input processing. |
| `GameTest.java` | The entry point for starting the application. |

---

## 🎮 How to Run

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/dogaece-koca/The-MAZE.git](https://github.com/dogaece-koca/The-MAZE.git)
    ```
2.  **Compile the source files:**
    ```bash
    javac *.java
    ```
3.  **Run the game:**
    ```bash
    java GameTest
    ```

---

## 📝 Learning Objectives

This project was developed to master:
1.  **Manual implementation** of abstract data types.
2.  Handling **circular buffers** in queue structures.
3.  Managing **LIFO and FIFO** logic in a dynamic, real-time environment.
4.  Console-based **User Interface (UI)** design in Java.
