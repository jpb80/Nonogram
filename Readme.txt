Usage:

Use the command line to enter the name of the file.   


Solve1 recursive method:  

If the column is less than the number of columns as well as the isComplete method is true 

then the solution to the puzzle has been found.  If the row number is greater than or 

equal to the number of rows then you will return.  The isLegal checks that the current 

block, based on the col and row constraints, if that position on the board can be marked 

as true.  If the constraints are the same as length of the column constraints then call 

the solve1 method recursively and move over one column.  Otherwise, recursively call solve 

to move down a row as well as increase it by the blockSize, increase the constraints, but 

remain in the same column.   If the is found is false then call solve1 recursively with 

using the current row, col, and blocksize numbers.  If the current block is not legal then 

call solve1 recursively by moving down a row with the current column and constraints.
