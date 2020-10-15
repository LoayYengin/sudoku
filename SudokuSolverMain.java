import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Recursive Sudoku Solver
 *
 * Author: Yengin Loay
 * Version: 22 May 2020
 *
 * PURPOSE: For any valid sized grid solve the Sudoku puzzle with recursion.
 * To solve a puzzle each blank cell must contain the numbers from 1 up to the grid length.
 */

public class SudokuSolverMain {
    public static void main(String[] args) throws IOException {
        System.out.println("Please enter the sudoku board");

        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        String line = userInput.readLine();

        readInput(line);

        System.out.println("Thank you for playing!");
    }

    public static void readInput(String line) {
        String[] tokens = line.trim().split("\\s+");

        Sudoku mySudoku = new Sudoku(tokens);

        System.out.println("The original board is:\n" + mySudoku);

        if (mySudoku.recursiveSolve()) {
            System.out.println("The solution is:\n" + mySudoku);
        } else {
            System.out.println("There is no solution");
        }
    }
}