import java.util.Random;
import java.util.Scanner;

/*
	NimAIPlayer.java
	
	This class is provided as a skeleton code for the tasks of 
	Sections 2.4, 2.5 and 2.6 in Project C. Add code (do NOT delete any) to it
	to finish the tasks. 
*/

public class NimAIPlayer extends NimPlayer implements Testable {
	// you may further extend a class or implement an interface
	// to accomplish the tasks.	
    public NimAIPlayer(){
    }
    
    public NimAIPlayer(String userName, String familyName, String givenName, 
                                                          int gamesPlayed, int gamesWon){

        super(userName, familyName, givenName, gamesPlayed, gamesWon);
    }
        
    @Override
    public int removeStone(Scanner keyboard, int totalStone, int upperBound) {
        int removeStone;
        Random random = new Random();
        
        removeStone = (totalStone - 1) % (upperBound + 1);
        
        if(removeStone != 0){
            // AI find number which satisfies guaranteed to win.
            return removeStone;
        }else{
            /*AI couldn't find number which satisfies guaranteed to win. 
              So return valid move randomly.
            */
            if (totalStone > upperBound) {
                while(true){
                    removeStone = random.nextInt(upperBound);
                    
                    if (removeStone != 0) {
                        break;
                    }
                }
            }else{
                while(true){
                    removeStone = random.nextInt(totalStone);
                    
                    if (removeStone != 0) {
                        break;
                    }
                }
            }
            
            return removeStone;
        }
    }
	
    public String advancedMove(boolean[] available, String lastMove) {
        // the implementation of the victory
        // guaranteed strategy designed by you
        String move = "";

        return move;
    }
}
