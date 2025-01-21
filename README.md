# Snake Game with Linked List

This repository contains a simple implementation of the classic Snake Game developed in Java, utilizing a linked list to represent the snake's body. The game features basic graphics, food spawning mechanics, and collision detection.

## Features

- **Snake Movement:** The snake moves across a grid, growing longer each time it eats food.
- **Linked List Implementation:** The snake's body is represented using a linked list, making it easier to manage growth and movement.
- **Food Spawning:** Randomly generated food appears on the grid, which the snake consumes to grow in size.
- **Collision Detection:** The game ends if the snake collides with itself or the boundaries.
- **Score Display:** The player's score is dynamically displayed during gameplay.

## How to Play

1. Run the `SnakeGame.java` file to start the game.
2. Use the arrow keys to control the direction of the snake:
   - **Left Arrow:** Move left
   - **Right Arrow:** Move right
   - **Up Arrow:** Move up
   - **Down Arrow:** Move down
3. Eat the red food to grow longer and increase your score.
4. Avoid colliding with the boundaries or your own body.
5. Press **Enter** to restart the game after a "Game Over."

## File Structure

- **SnakeGame.java:** The main class that sets up the game window and initializes the game.
- **GamePanel.java:** Contains the game logic, including rendering the grid, managing the snake's movement, and detecting collisions.
- **Position.java:** A utility class representing positions on the grid.

## Prerequisites

- Java Development Kit (JDK) 8 or higher.
- A Java IDE such as IntelliJ IDEA, Eclipse, or any text editor of your choice.
