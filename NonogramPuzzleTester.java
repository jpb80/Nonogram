/** Jordan Buttkevitz
 *  University of Pittsburgh
 *  Data Structures
 *  Project: Nonogram
 * */

import java.io.IOException;
import java.util.Date;

public class NonogramPuzzleTester {
        public static void main(String[] args) throws IOException  {
            long lStartTime = new Date().getTime();
            if (args.length < 1) {
                System.out.printf("\n...Please enter the name of the file on the command line \n");
                System.exit(0);
                           
            }         
            NonogramPuzzle np = new NonogramPuzzle(args[0]);    
            System.out.println("\nInput File: " + args[0]);
            System.out.println("\nThe solution to the " + np.printDimensions() + " X " + np.printDimensions() + " puzzle:"); 
            np.solve();
       
            long lEndTime = new Date().getTime();       
            long difference = lEndTime - lStartTime;  
            System.out.println();    
            System.out.println("Elapsed milliseconds: " + difference + "\n");	
        }//end of main
}//end of NonogramPuzzleTester 

