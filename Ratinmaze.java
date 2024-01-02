package project;


import java.util.Random;
import java.util.Scanner;

public class MazeSolver {
    private static final char WALL = '█';
    private static final char OPEN_SPACE = ' ';
    private static final char START = 'S';
    private static final char END = 'E';
    private static final char PATH = '◍';

    private static char[][] generateMaze(int size, double wallPercentage) {
        char[][] maze = new char[size][size];

        // Initialize maze with open spaces
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                maze[i][j] = OPEN_SPACE;
            }
        }

        // Set walls
        int numWalls = (int) (wallPercentage / 100 * size * size);
        Random random = new Random();
        for (int i = 0; i < numWalls; i++) {
            int row = random.nextInt(size);
            int col = random.nextInt(size);
            maze[row][col] = WALL;
        }

        // Set start and end points
        maze[0][0] = START;
        maze[size - 1][size - 1] = END;

        return maze;
    }

    private static void printMaze(char[][] maze) {
        for (char[] row : maze) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    private static boolean findPath(char[][] maze, int row, int col) {
        if (row < 0 || row >= maze.length || col < 0 || col >= maze[0].length || maze[row][col] != OPEN_SPACE) {
            return false;
        }

        maze[row][col] = PATH;

        if (row == maze.length - 1 && col == maze[0].length - 1) {
            return true; // Reached the end
        }

        // Explore neighbors
        if (findPath(maze, row + 1, col) || findPath(maze, row - 1, col) ||
                findPath(maze, row, col + 1) || findPath(maze, row, col - 1)) {
            return true;
        }

        maze[row][col] = OPEN_SPACE; // Backtrack
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        while (true) {
            System.out.print("Enter the size of the maze: ");
            int size = scanner.nextInt();

            System.out.print("Enter the percentage of walls (0-100): ");
            double wallPercentage = scanner.nextDouble();

            char[][] maze = generateMaze(size, wallPercentage);

            System.out.println("\nGenerated Maze:");
            printMaze(maze);

            if (findPath(maze, 0, 0)) {
                System.out.println("\nMaze with Path:");
                printMaze(maze);
            } else {
                System.out.println("\nNo path found.");
            }

            System.out.print("\nOptions:\n1. Print Path\n2. Generate Another Puzzle\n3. Exit\nEnter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nMaze with Path:");
                    printMaze(maze);
                    break;
                case 2:
                    break; // Continue to the next iteration
                case 3:
                    System.out.println("Exiting Maze Solver. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Exiting Maze Solver. Goodbye!");
                    System.exit(1);
            }
        }
    }
}
