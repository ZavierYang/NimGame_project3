import java.util.Scanner;

public class NimHumanPlayer extends NimPlayer{
    public NimHumanPlayer(String userName, String familyName, String givenName, 
                                                              int gamesPlayed, int gamesWon){
        
        super(userName, familyName, givenName, gamesPlayed, gamesWon);
    }

    @Override
    public int removeStone(Scanner keyboard, int totalStone, int upperBound) {
        return keyboard.nextInt();
    }

}
