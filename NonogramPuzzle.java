/** Jordan Buttkevitz
 *  University of Pittsburgh
 *  Data Structures
 *  Project: Nonogram
 * */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Date;

public class NonogramPuzzle {
	
	private int[][] rows;
	private int[][] cols;
	private boolean[][] board;
	private boolean isFound = false;
	private int numberOfRows;
	private int numberOfCols;
	
	public NonogramPuzzle(String fileName) throws IOException {	

                Scanner infile = new Scanner(new File(fileName));		
		String[] inputSize = infile.next().split(",");
		
		numberOfRows = Integer.parseInt(inputSize[0]);
		numberOfCols = Integer.parseInt(inputSize[1]);
		rows = new int[numberOfRows][];
		cols = new int[numberOfCols][];
		
		board = new boolean[numberOfRows][numberOfCols];
    		
		//creating row labels
		for (int i = 0; i < numberOfRows; i++) {
			String[] rowConstraints = infile.next().split(",");
			rows[i] = new int[rowConstraints.length];
			for (int j = 0; j < rowConstraints.length; j++) {
				rows[i][j] = Integer.parseInt(rowConstraints[j]);
				}
		}
		
		//creating col labels	
		for (int i = 0; i < numberOfCols; i++) {
			String[] colConstraints = infile.next().split(",");
			cols[i] = new int[colConstraints.length];
			for (int j = 0; j < colConstraints.length; j++) {
				cols[i][j] = Integer.parseInt(colConstraints[j]);
			}
		}
		infile.close();
	}//end of NonogramPuzzle
			
	public void solve() {
		solve1(0,0,0);
		printBoard(board);
	}//end of solve
	
	public void solve1(int row, int col, int c) {
		if ( col >= numberOfCols) {	
			if ( isComplete()) {  
				isFound = true;
			} 
				return;
		}
		if (row >= numberOfRows ) {
			return;
		}
		int blockSize = cols[col][c];   
		if (isLegal(row, col, blockSize)) { 
			insertBlock(row, col, blockSize); 
			if (c == cols[col].length - 1) { 
				solve1(0, col + 1, 0);
			} else {
				solve1(row + 1 + blockSize, col, c + 1);
				
			}
			if (!isFound) {					
				removeBlock(row, col, blockSize);
			}
		} if (!isFound) {
			solve1(row + 1, col, c);
		}
	}//end of solve1
		
	private void removeBlock(int row, int col, int blockSize) {
		for (int i = board.length - 1; i >= 0; i--) {
			if (board[i][col]) {
				for (int j = i; j >= 0; j--) {
					if (!board[j][col]) {
						return;
					} else {
					board[j][col] = false;
					}
				}
			}

		}
		
	}//end of removeBlock

	private void insertBlock(int row, int col, int blockSize) {
		for (int i = 0; i < blockSize; i++) {
			board[row + i][col] = true;
		}
	}//end of insertBlock
	
	public boolean isComplete() {
		for (int i = 0; i < numberOfRows; i++) {
			String rowStr = "";
			for (int j = 0; j < numberOfCols; j++) {
				if (board[i][j]) {
					rowStr += "x";
				} else {
					rowStr += " ";
				}
			} //end of second for loop
				String[] blockArray = rowStr.trim().split("((?<=x)[ ]+)");
				
				if (blockArray.length != rows[i].length) { 
					return false;
				} else {
					for(int j = 0; j < blockArray.length && j < rows[i].length; j++) { 
						if (rows[i][j] != blockArray[j].length() ) {
							return false;
						}
					}
				}	
		} 	 
		return true;	
	}//end of isComplete
	
	public boolean isLegal(int row, int col, int blockSize) { 
		if ( blockSize + row > numberOfRows) {
			return false;
		}
		if (row > 0 ) {   
                    if (board[row - 1][col]) { 
			return false;
		    }
                }
		for (int i = row; i < board.length; i++) {
		    String rowStr = "";
		    for (int j = 0; j < numberOfCols; j++) {
		        if (board[i][j]) {
				rowStr += "x";
			    } else {
				rowStr += " ";
			    }
		} //end of second for loop

		String[] blockArray = rowStr.trim().split("((?<=x)[ ]+)");
                if (blockArray.length > rows[i].length) {
                    return false;
                } 

                int length = 0;
                for (String tempString : blockArray) {
                    length += tempString.length();
                }

                int sum = 0;
                for (int k = 0; k < rows[i].length; k++) {
                    sum += rows[i][k];
                }
                
                if (length > sum) {
                    return false;
                }
                if ((sum - length) > (board.length - col)) {
                    return false;
                }

                for (int n = 0; n < blockArray.length - 1; n++) {
                    if (blockArray[n].length() != rows[i][n]) {
                        return false;
                    }
                
                }

               if (blockArray[blockArray.length - 1].length() < rows[i][blockArray.length - 1]) {
                    int lastTrue = -1;
                    for (int s = 0; s < col; s++) {
                        if(board[i][s]) {
                            lastTrue = s;
                        }
                    }
                
                    if (lastTrue != -1 && lastTrue + 1 != col) {
                    	return false;
                    }
                }  else if (blockArray[blockArray.length - 1].length() > rows[i][blockArray.length - 1]) {
                        return false;
                    } 
                 }
               
	    return true;
	}//end of Islegal

       public int printDimensions() {
            return numberOfCols;
       }

       private void printBoard(boolean[][] board ) {
            System.out.println("    ");
            for(int c = 0; c < board.length; c++) {
                if (c == 0) {
                    System.out.print("    " + c + " ");
                } else if (c > 9) {
           
                System.out.print((c - 10) + " ") ;
                } else {
                    System.out.print(c + " ");
                }
            }  
            System.out.print( "\n    "); 
            
            for(int c = 0; c < board.length; c++) {
                System.out.print("- ");
            }
            System.out.print( "\n");

            for(int r = 0; r < board.length; r++) {
                if (r > 9) {
                    System.out.print((r - 10) + " | ");
                } else {
                    System.out.print(r + " | "); 
                }
            for(int c = 0; c < board[r].length; c++)
                 if (board[r][c]) { 
                    System.out.print("@" + " ");
                } else {
                    System.out.print("*" + " ");
                }
            System.out.println("|");
            }
            System.out.print( "   ");
            for(int c = 0; c < board.length; c++)
                System.out.print("- ");
                System.out.print( "\n              ");
        }
}//EOF





