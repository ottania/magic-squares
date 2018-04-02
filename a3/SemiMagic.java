package cs445.a3;

import java.util.*;

import java.io.File;
import java.io.FileNotFoundException;

public class SemiMagic {
	static int max, row, col;
	static int[][] originalSquare;


    public static boolean isFullSolution(int[][] square) {
        for (int i = 0; i < square.length; i++) {
        	for (int j = 0; j < square.length; j++){
        		if (square[i][j] == 0)
        			return false;
        	}
        }
        return true;
    }

    public static boolean reject(int[][] square) {
        int n = square.length;
        int magic = (int)(n*(n*n+1)/2);
        for(int i=0; i<square.length; i++){
        	int count1=0, count2=0, count3=0, count4=0;
        	int orowsum=0, ocolsum=0;
        	int rowsum=0, colsum=0;
        	for(int j=0; j<square.length; j++){
        		if(originalSquare[i][j] < 0){
        			count1++;
        			orowsum += square[i][j];
        		}
        		if(originalSquare[j][i] < 0){
        			count2++;
        			ocolsum += square[j][i];
        		}
        		if(square[i][j] != 0){
        			count3++;
        			rowsum += square[i][j];
        		}
        		if(square[j][i] != 0){
        			count4++;
        			colsum += square[j][i];
        		}
        		for(int k=0; k<square.length; k++){
        			for(int l=0; l<square.length; l++){
        				if(i==k && j==l){}
        				else{
        					if(square[i][j] != 0){
        						if(square[i][j] == square[k][l])
        							return true;
        					}

        				}
        			}
        		}
        	}
        	if(count1 < square.length){
        		if(orowsum >= magic)
        			return true;
        	}
        	else if(count1 == square.length){
        		if(orowsum != magic)
        			return true;
        	}
        	if(count2 < square.length){
        		if(ocolsum >= magic)
        			return true;
        	}
        	else if(count2 == square.length){
        		if(ocolsum != magic)
        			return true;
        	}
        	if(count3 == square.length){
        		if(rowsum != magic)
        			return true;
        	}
        	if(count4 == square.length){
        		if(colsum != magic)
        			return true;
        	}
    }
        return false;
    }

    public static int[][] extend(int[][] square) {
        int [][] temp = new int[square.length][square.length];
        for(int i=0; i<square.length; i++){
        	for(int j=0; j<square.length; j++){
        		temp[i][j] = square[i][j];
        		}
        	}
	        	}
	        }	
    	}*/
        for(int i=0; i<square.length; i++){
        	for(int j=0; j<square.length; j++){
        		if(square[i][j]==0){
        			temp[i][j] = 1;
        			row = i;
        			col = j;
        			return temp;
        			}
        	}
        }	
        return null;
    }

    public static int[][] next(int[][] square) {
        int [][] temp = new int[square.length][square.length];
        for(int i=0; i<square.length; i++){
        	for(int j=0; j<square.length; j++){
        		temp[i][j] = square[i][j];
        		}
        	}

        if(originalSquare[row][col] != 0){
        	if(col == 0){
        		if(row != 0){
        			col = square.length-1;
        			row--;
        		}
        		else{
        			return null;
        		}
        	}
        	else{
        		col--;
        	}	
        }
        if(square[row][col] != 0 && square[row][col] < (square.length*square.length)){
        	temp[row][col]++;
        	return temp;
        }
        else{
        	if(col == 0){
        		if(row != 0){
        			col = square.length-1;
        			row--;
        		}
        		else{
        			return null;
        		}
        	}
        	else{
        		col--;
        	}
        }
        return null;
    }

    static void testIsFullSolution() {

        int[][][] fullSolutions = new int[][][] {
            {{4, 9, 2},{3, 5, 7},{8, 1, 6}},
            {{2, 7, 6},{9, 5, 1},{4, 3, 8}},
            {{16, 3, 2, 13},{5, 10, 11,8},{9, 6, 7, 12},{4, 15, 14, 1}},
        };

        int[][][] notFullSolutions = new int[][][] {
            {{0, 0, 0},{0, 0, 0},{0, 0, 0}},
            {{4, 9, 0},{3, 5, 0},{8, 0, 0}},
            {{6, 7, 2},{1, 5, 0},{0, 0, 4}},
            {{2, 7, 6},{0, 0, 0},{0, 3, 8}},
            {{11, 18, 25, 2, 9},{10, 12, 0, 0, 0},{4, 6, 0, 20, 22},{0, 0, 0, 14, 16},{0, 24, 0, 8, 0}},
            {{17, 24, 0, 0, 15},{0, 5, 7, 14, 0},{0, 0, 13, 20, 22},{0, 0, 19, 21, 3},{11, 0, 0, 2, 9}},

        };

        System.err.println("These should be full:");
        for (int[][] test : fullSolutions) {
            if (isFullSolution(test)) {
                System.err.println("\tFull sol'n:\t");
                printSquare(test);
            } else {
                System.err.println("\tNot full sol'n:\t");
                printSquare(test);
            }
        }

        System.err.println("These should NOT be full:");
        for (int[][] test : notFullSolutions) {
            if (isFullSolution(test)) {
                System.err.println("\tFull sol'n:\t");
                printSquare(test);
            } else {
                System.err.println("\tNot full sol'n:\t");
                printSquare(test);
            }
        }
    }

    static void testReject() {
	    
        int[][][] notRejected = new int[][][] {
            {{0, 0, 0},{0, 0, 0},{0, 0, 0}},
            {{4, 9, 0},{3, 5, 0},{8, 0, 0}},
            {{6, 7, 2},{1, 5, 0},{0, 0, 4}},
            {{2, 7, 6},{0, 0, 0},{0, 3, 8}},
        };

        int[][][] rejected = new int[][][] {
            {{2, 0, 0},{0, 7, 0},{0, 0, 6}},
            {{8, 1, 6},{3, 5, 7},{10, 9, 2}},//left most elements equals greater than magic sum
        	{{2, 0, 7},{9, 0, 0},{4, 0, 8}},//right most elements already equal magic sum while missing number
        	{{6, 1, 9},{7, 5, 3},{2, 8, 4}},
        	{{2, 2, 2},{0, 7, 0},{0, 0, 6}},//top row equals less than magic sum
        	{{1, 9, 5},{2, 0, 0},{3, 0, 0}},//left column less than magic sum

        };

        System.err.println("These should NOT be rejected:");
        for (int[][] test : notRejected) {
        	original(test);
            if (reject(test)) {
                System.err.println("\tRejected:\t");
                printSquare(test);
            } else {
                System.err.println("\tNot rejected:\t");
                printSquare(test);
            }
        }

        System.err.println("These should be rejected:");
        for (int[][] test : rejected) {
            if (reject(test)) {
            	original(test);
                System.err.println("\tRejected:\t");
                printSquare(test);
            } else {
            	original(test);
                System.err.println("\tNot rejected:\t");
                printSquare(test);
            }
        }
    }

    static void testExtend() {

        int[][][] noExtend = new int[][][] {
        	{{8, 1, 6},{3, 5, 7},{4, 9, 2}},
        	{{2, 7, 6},{9, 5, 1},{4, 3, 8}},
        	{{6, 1, 8},{7, 5, 3},{2, 9, 4}},
        	{{16, 3, 2, 13},{5, 10, 11, 8},{9, 6, 7, 12},{4, 15, 14, 1}},
        	{{8, 11, 14, 1},{13, 2, 7, 12},{3, 16, 9, 6},{10, 5, 4, 15}},
            
        };

        int[][][] extend = new int[][][] {
        	{{0, 0, 0,},{0, 0, 0},{0, 0, 0}},
        	{{8, 1, 0},{0, 5, 7},{0, 9, 2}},
        	{{8, 1, 6},{3, 5, 7},{0, 9, 2}},
        	{{2, 7, 6},{9, 5, 1},{4, 3, 0}},
        	{{6, 1, 8},{0, 0, 0},{0, 9, 4}},
        	{{16, 3, 2, 13},{5, 0, 0, 8},{9, 6, 7, 0},{4, 0, 0, 0}},
        	{{8, 11, 14, 0},{13, 2, 7, 12},{3, 16, 9, 0},{10, 0, 4, 15}},

        };

        System.err.println("These can NOT be extended:");
        for (int[][] test : noExtend) {
        	original(test);
            System.err.println("\tExtended ");
            printSquare(test);
            System.err.println("\tto ");
            printSquare(extend(test));
        }

        System.err.println("These can be extended:");
        for (int[][] test : extend) {
        	original(test);
            System.err.println("\tExtended ");
            printSquare(test);
            System.err.println("\tto ");
            printSquare(extend(test));
        }
    }

    static void testNext() {

        /* This method was a little tricky for me to figure out because my next method is conditional on extend running correctly.
        In extend I keep track of the index of the array that was changed last and hold that in a global variable. This tells me
        the orientation of where in the original square the other permanent(given) magic numbers are. In order for this testNext
        method to work, I needed to call extend() and original() before executing next because it is necessary in order to get the
        index of the array of the most previously executed change. In the case where the array is not nextable, I needed to set the
        index to the very first number because logically if all other numbers that were tested failed, then the last possible option
        would be to begin the array at the n^2th number and decrement one for each following index, e.g. {{9,8,7},{6,5,4}},{3,2,1} 
        which would then reject and finally cause it to not be nextable.
        */
        int[][][] noNext = new int[][][] {
        	{{8, 1, 6},{3, 5, 7},{4, 9, 2}},
        	{{2, 7, 6},{9, 5, 1},{4, 3, 8}},
        	{{6, 1, 8},{7, 5, 3},{2, 9, 4}},
        	{{16, 3, 2, 13},{5, 10, 11, 8},{9, 6, 7, 12},{4, 15, 14, 1}},
        	{{8, 11, 14, 1},{13, 2, 7, 12},{3, 16, 9, 6},{10, 5, 4, 15}},
            
        };

        int[][][] next = new int[][][] {
            {{0, 0, 0,},{0, 0, 0},{0, 0, 0}},
        	{{8, 1, 0},{0, 5, 7},{0, 9, 2}},
        	{{8, 1, 6},{0, 0, 7},{0, 9, 2}},
        	{{2, 7, 6},{9, 5, 1},{4, 3, 0}},
        	{{6, 1, 8},{0, 0, 0},{0, 9, 4}},
        	{{16, 3, 2, 13},{5, 0, 0, 8},{9, 6, 7, 0},{4, 0, 0, 0}},
        	{{8, 11, 14, 0},{13, 2, 7, 12},{3, 16, 9, 0},{10, 0, 4, 15}},
        };

        System.err.println("These can NOT be next'd:");
        for (int[][] test : noNext) {
        	original(test);        	
        	row = 0;
        	col = 0;
            System.err.println("\tNexted ");
            printSquare(test);
            System.err.println("\tto ");
            printSquare(next(test));
        }

        System.err.println("These can be next'd:");
        for (int[][] test : next) {
        	original(test);
        	int[][] test2 = extend(test);
            System.err.println("\tNexted ");
            printSquare(test2);
            System.err.println("\tto ");
            printSquare(next(test2));
        }
    }

    /**
     * Returns a string representation of a number, padded with enough zeros to
     * align properly for the current size square.
     * @param num the number to pad
     * @param size the size of the square that we are padding to
     * @return the padded string of num
     */
    static String pad(int num, int size) {
        // Determine the max value for a square of this size
        int max = size * size;
        // Determine the length of the max value
        int width = Integer.toString(max).length();
        // Convert num to string
        String result = Integer.toString(num);
        // Pad string with 0s to the desired length
        while (result.length() < width) {
            result = " " + result;
        }
        return result;
    }

    /**
     * Prints a square
     * @param square the square to print
     */
    public static void printSquare(int[][] square) {
        if (square == null) {
            System.out.println("Null (no solution)");
            return;
        }
        int size = square.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(pad(square[i][j], size) + " ");
            }
            System.out.print("\n");
        }
    }

    /**
     * Reads a square of a specified size from a plaintext file
     * @param filename the name of the file to read from
     * @param size the size of the square in the file
     * @return the square
     * @throws FileNotFoundException if the named file is not found
     */
    public static int[][] readSquare(String filename, int size)
                throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        int[][] square = new int[size][size];
        int val = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                square[i][j] = scanner.nextInt();
            }
        }
        return square;
    }

    /**
     * Solves the magic square
     * @param square the partial solution
     * @return the solution, or null if none
     */
    public static int[][] solve(int[][] square) {
        if (reject(square)) return null;
        if (isFullSolution(square)) return square;
        int[][] attempt = extend(square);
        while (attempt != null) {
            int[][] solution;
            solution = solve(attempt);
            if (solution != null) return solution;
            attempt = next(attempt);
        }
        return null;
    }

    public static void original(int[][] square){
//copy square to global variable originalSquare
        originalSquare = new int[square.length][square.length];
        for(int i=0; i<square.length; i++){
        	for(int j=0; j<square.length; j++){
        		if(square[i][j] > 0){
        			originalSquare[i][j] = -1;	
        		}
        		else if(square[i][j] == 0){
        			originalSquare[i][j] = 0;
        		}
        	}
        }
    }

    public static void main(String[] args) {
        if (args.length >= 1 && args[0].equals("-t")) {
            System.out.println("Running tests...");
            testIsFullSolution();
            testReject();
            testExtend();
            testNext();
        } else if (args.length >= 1) {
            try {
                // First get the specified size
                int size = Integer.parseInt(args[0]);

                int[][] square;
                if (args.length >= 2) {
                    // Read the square from the file
                    square = readSquare(args[1], size);
                } else {
                    // Initialize to a blank square
                    square = new int[size][size];
                }

                //copy square to global variable originalSquare
                original(square);

                System.out.println("Initial square:");
                printSquare(square);

                System.out.println("\nSolution:");
                int[][] result = solve(square);
                printSquare(result);
            } catch (NumberFormatException e) {
                // This happens if the first argument isn't an int
                System.err.println("First argument must be the size");
            } catch (FileNotFoundException e) {
                // This happens if the second argument isn't an existing file
                System.err.println("File " + args[1] + " not found");
            }
        } else {
            System.err.println("See usage in assignment description");
        }
    }
}

