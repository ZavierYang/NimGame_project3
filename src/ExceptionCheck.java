import java.util.*;

public class ExceptionCheck {
    public void commandCheck(String inputCommand) throws Exception{
        boolean valid;
        String [] command = {"startgame", "addplayer", "addaiplayer","removeplayer", "editplayer", 
                             "resetstats", "displayplayer", "rankings", "exit"};
        
        valid = Arrays.asList(command).contains(inputCommand);
        if (!valid) {
            throw new Exception("\'" +inputCommand + "\'" + " is not a valid command.");
        }
    }
    
    public void argumentCheck(String inputCommand, String [] input) throws Exception{
        /* Startgame needs 4 arguments. 
           addplayer, addaiplayer, editplayer need 3 arguments.
        */
        if ((((inputCommand.equals("addplayer")  || inputCommand.equals("addaiplayer") || 
              inputCommand.equals("editplayer")) && input.length < 3)) ||
             (inputCommand.equals("startgame") && input.length < 4)){
            
            throw new Exception("Incorrect number of arguments supplied to command.");
        }
    }
    
    public void removeCheck(int removeStone, int initialStone, int upperBound) 
                                                throws Exception{
        
        if((removeStone > upperBound || removeStone < 1) && initialStone > upperBound){
            /*The first case is when total stones are more than upperBound. Additionally,  
              the input of number is bigger than upperBound or smaller than 1.*/
            throw new Exception("\nInvalid move. You must remove between 1 and " 
                                        + upperBound +" stones.");

        }else if((removeStone < 1 || removeStone > initialStone) && initialStone < upperBound){
            /*The second case is when total stones are less than upperBound. Additionally,  
              the input of number is bigger than initialStone or smaller than 1*/
            throw new Exception("\nInvalid move. You must remove between 1 and " 
                                         + initialStone +" stones.");
        }
    }
}
