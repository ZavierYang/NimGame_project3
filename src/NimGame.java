import java.util.InputMismatchException;
import java.util.Scanner;

public class NimGame {
        /*
        Variables
            playerTurn: Current player who pick stones.
            removeStone: The number of stones that player wants to remove.
        */
        private NimPlayer playerTurn;
        private int removeStone;
        private ExceptionCheck exceptionCheck = new ExceptionCheck();
        
        public void game(int initialStone, int upperBound,
                         NimPlayer NimPlayer1, NimPlayer NimPlayer2, Scanner keyboard){   
          
        System.out.printf("\nInitial stone count: %d\n", initialStone);
        System.out.printf("Maximum stone removal: %d\n", upperBound);
        System.out.printf("Player 1: %s %s\n", NimPlayer1.getGivenName(), 
                                              NimPlayer1.getFamilyName());
        System.out.printf("Player 2: %s %s\n", NimPlayer2.getGivenName(), 
                                              NimPlayer2.getFamilyName());

        //Initialize first player.
        this.playerTurn = NimPlayer1; 

        //The while loop control whether or not this turn is finish.
        while(0 < initialStone){
            System.out.printf("\n%d stones left:", initialStone);

            //Display stones quatity.
            for(int i = 0; i < initialStone; i++){
                System.out.print(" *");
            }
                
            try {
                System.out.printf("\n%s's turn - remove how many?\n", this.playerTurn.getGivenName());
                
                this.removeStone = this.playerTurn.removeStone(keyboard, initialStone, upperBound);
                
                //Check whether or not the input is valid.
                exceptionCheck.removeCheck(this.removeStone, initialStone, upperBound);

                //Calculate how many stones are left.
                initialStone = initialStone - this.removeStone; 
                
                //Change next turn player.
                this.playerTurn = (this.playerTurn.equals(NimPlayer1)) ? NimPlayer2 : NimPlayer1;
                
            }catch(Exception exception){
                if(exception.getClass() == InputMismatchException.class){
                    if(initialStone > upperBound){
                        System.out.println("\nInvalid move. You must remove between 1 and " 
                                            + upperBound +" stones.");
                    }else if(initialStone < upperBound){
                        System.out.println("\nInvalid move. You must remove between 1 and " 
                                            + initialStone +" stones.");
                    }
                    
                    //clear the value in scanner if the input is not integer.
                    keyboard.next();
                    
                }else{
                    System.out.println(exception.getMessage());
                }  
            } 
        }
        
        System.out.printf("\nGame Over\n%s %s wins!\n", this.playerTurn.getGivenName(),  
                                                      this.playerTurn.getFamilyName());
        
        //To catch the \n after keyboard.nextInt().
        keyboard.nextLine();
        
        //Increase players' statistics.
        this.playerTurn.setGamesWon(this.playerTurn.getGamesWon() + 1);
        NimPlayer1.setGamesPlayed(NimPlayer1.getGamesPlayed() + 1);
        NimPlayer2.setGamesPlayed(NimPlayer2.getGamesPlayed() + 1);
    }
}