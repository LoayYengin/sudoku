/**
 * This class will store a game board and contains the recursiveSolve() method
 */

class Sudoku {
    private String[] original;
    private int[][] grid;
    private int size;
    private int col;
    private int row;
    private boolean solved = false;

    public Sudoku(String[] tokens) {
        this.size = (int) Math.sqrt(tokens.length);
        this.row = -1;
        this.col = -1;

        fillGrid(tokens);
    }

    /**
     * Fills the grid with a valid game board from user input
     * If the board is invalid fill a 9x9 grid with "-"
     */
    public void fillGrid(String[] tokens) {
        if (validInput(tokens)) {
            this.grid = new int[size][size];
            int[] copy = new int[tokens.length];
            int index = 0;
            setOriginal(tokens);

            for (int i = 0; i < tokens.length; i++) {
                if (tokens[i].charAt(0) != '-') {
                    copy[i] = Integer.parseInt(tokens[i]);
                } else {
                    copy[i] = 0;
                }
            }

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    grid[i][j] = copy[index];
                    index++;
                }
            }
        } else {
            this.size = 9;
            this.grid = new int[size][size];
            setOriginal();

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    this.grid[i][j] = 0;
                }
            }
        }
    }

    /**
     * Returns a boolean indicating whether the sudoku puzzle was successfully solved or not
     */
    public boolean recursiveSolve() {
        boolean result = false;
        int testNum = 1;
        int curRow;
        int curCol;

        if (!checkBlank()) {
            this.solved = true;
            result = true;
        } else {
            getBlank();
            curRow = this.row;
            curCol = this.col;

            for (int i = 0; i < grid.length; i++) {
                if (!checkConflict(testNum, curRow, curCol)) {
                    this.grid[curRow][curCol] = testNum;

                    if (recursiveSolve()) {
                        return true;
                    } else {
                        this.grid[row][col] = 0;
                        this.grid[curRow][curCol] = 0;
                    }
                }
                testNum++;
            }
        }
        return result;
    }

    /**
     * Checks if user input is valid
     */
    private boolean validInput(String[] tokens) {
        double n = (Math.sqrt(tokens.length));

        return (Math.sqrt(n) - Math.floor((Math.sqrt(n))) == 0) && n != 0 && n != 1;
    }

    /**
     * Sets the original grid data from the user input
     */
    private void setOriginal(String[] tokens) {
        this.original = new String[tokens.length];

        for (int i = 0; i < tokens.length; i++) {
            this.original[i] = tokens[i];
        }
    }

    /**
     * Sets the Original grid data to a 9x9 grid of "-"
     */
    private void setOriginal() {
        this.original = new String[size * size];

        for (int i = 0; i < size * size; i++) {
            this.original[i] = "-";
        }
    }

    /**
     * Checks the grid for a blank cell
     */
    private boolean checkBlank() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Set row and col to the coordinates of a blank cell
     */
    private void getBlank() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    this.row = i;
                    this.col = j;
                    return;
                }
            }
        }
    }

    /**
     * Checks if the test number results in a conflict i.e testNum is found in the row, column, or block of the blank cell
     */
    private boolean checkConflict(int testNum, int curRow, int curCol) {
        return checkRow(testNum, curRow) || checkCol(testNum, curCol) || checkBlock(testNum, curRow, curCol);
    }

    /**
     * Checks the current row for the test number
     */
    private boolean checkRow(int testNum, int curRow) {
        for (int i = 0; i < grid.length; i++) {
            if (grid[curRow][i] == testNum) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks the current column for the test number
     */
    private boolean checkCol(int testNum, int curCol) {
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][curCol] == testNum) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks the current block for the test number
     */
    private boolean checkBlock(int testNum, int curRow, int curCol) {
        int n = (int) Math.sqrt(grid.length);
        int rowBlock = curRow - curRow % n;
        int colBlock = curCol - curCol % n;

        for (int i = rowBlock; i < rowBlock + n; i++) {
            for (int j = colBlock; j < colBlock + n; j++) {
                if (grid[i][j] == testNum) {
                    return true;
                }
            }
        }
        return false;
    }

    public String toString() {
        String output = "";

        if (!this.solved) {
            int newLine = 1;

            for (int i = 0; i < original.length; i++) {
                output += original[i] + " ";

                if (newLine % (int) Math.sqrt(original.length) == 0) {
                    output += "\n";
                }
                newLine++;
            }
        } else {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    output += this.grid[i][j] + " ";
                }
                output += "\n";
            }
        }
        return output;
    }
}