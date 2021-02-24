/*********************************************************************
 *  Author:       ZavierYang                                         *
 *                                                                   *
 *  Description:  This algorithm is created for Nim game             *
 *                                                                   *
 *  Written:      09/05/2019                                         *
 *  Update:       23/05/2019                                         *
 *                                                                   *
 ********************************************************************/

import java.util.*;
import java.io.*;

public class Nimsys{
    static final int PLAYER_NOT_FOUND = -1;
    static final String FILENAME = "players.dat.txt";
    
    public static void main(String [] args){
        /*
        Variables
            playerindex: The index in players array where the target player is.
            nimGame: Instance of Nim game.
            players: This is an arraylist which store all players.
            exceptionCheck: Object of ExceptionCheck to check invalid input
            nimsys: Object of Nimsys in order to call function by object oriented.
            input: The whole string of the input.
            command: An array. After input separated by " ", put into command array.
                     Therefore, the operations(add player, remove player) and the input infomations
                     (player's name, displayer order) will be separated.
            nameInfo: An array which store the player's name from command[1](input infomations) and 
                      separated by ",".
            gameInfo:An array which game's information from command[1](input infomations) and 
                      separated by ",".
            file: a file to store player statistic.
        */
        
        Scanner keyboard = new Scanner(System.in);
        int playerindex;
        NimGame nimGame = null;
        ArrayList<NimPlayer> players = new ArrayList<NimPlayer>();
        ExceptionCheck exceptionCheck = new ExceptionCheck();
        Nimsys nimsys = new Nimsys();
        String input;
        String [] command, nameInfo, gameInfo;
        File file;
        
        System.out.println("Welcome to Nim");
        
        //Load data.
        try {
            file = new File(FILENAME);
            
            //If file not exists create one.
            if (!file.exists()) {
		file.createNewFile();
            }
            
            nimsys.loadFile(players);
        } catch (Exception e) { 
        }
        
        while (true) {            
            System.out.print("\n$");
            input = keyboard.nextLine();
            
            /*After this, command[0] is operation and command[1] will be the information 
              which want to input.
            */
            command = input.split(" ");

            try {
                //Check the command is valid or not.
                exceptionCheck.commandCheck(command[0]);
                
                //Whether or not the operation is to start a game.
                if(command[0].equals("startgame")){
                    gameInfo = command[1].split(",");
                    
                    //Check the number of input arguments is right or not.
                    exceptionCheck.argumentCheck(command[0], gameInfo);
                    
                    //Start game.
                    nimsys.startgame(nimGame, players, gameInfo, keyboard);
                    
                }else{
                    /*If the length of command is 1, it means people want to operate whole data. So 
                      nameInfo will be null.
                      If not, it means that people want to operate specific data and nameInfo 
                      should store username,family_name,given_name from command[1].
                    */
                    if(command.length != 1){
                        nameInfo = command[1].split(",");

                    }else{
                        nameInfo = null;
                    }
                    
                    //Check the number of input arguments is right or not.
                    exceptionCheck.argumentCheck(command[0], nameInfo);
                    
                    /*If the lengths of players and command are 1 and operation is not rankings.
                      command[1] might be player's name. Therefore, use playerCheck function.
                      If the lengths of player is 0, set playerindex to -1 because it means no 
                      players finded.
                      If not previous two situations, it means the operation is for all players. 
                      Therefore, playerindex won't be important.
                    */
                    if(!players.isEmpty() && command.length != 1 
                                           && !command[0].equals("rankings")){

                        playerindex = nimsys.playerCheck(players, nameInfo[0]);
                    }else if(players.isEmpty()){
                        playerindex = PLAYER_NOT_FOUND;
                    }else{
                        playerindex = players.size();
                    }

                    if(command[0].equals("addplayer") || command[0].equals("addaiplayer")){ 
                        nimsys.addplayer(players, command[0], nameInfo, playerindex);  
                        
                    }else if(!command[0].equals("addplayer") && !command[0].equals("addaiplayer")
                                      && playerindex == PLAYER_NOT_FOUND && command.length != 1){
                       /*If playerindex is -1 and operation is not addplayer and addaiplayer. 
                         The operation cannot be executed, because player does not exist.
                         But if the operation is for all player, do not execute this sentence.
                       */
                        System.out.println("The player does not exist.");

                    }else if(command[0].equals("removeplayer")){
                        nimsys.removeplayer(players, command.length, playerindex, keyboard);

                    }else if(command[0].equals("editplayer")){                      
                        nimsys.editplayer(players.get(playerindex), nameInfo);
                        
                    }else if(command[0].equals("resetstats")){
                        nimsys.resetstats(players, command.length, playerindex, keyboard);

                    }else if(command[0].equals("displayplayer")){
                        nimsys.displayplayer(players, command.length, playerindex);
                        
                    }else if(command[0].equals("rankings")){ 
                        if(command.length == 1){
                             nimsys.rankings(players, command.length, "desc");
                        }else{
                             nimsys.rankings(players, command.length, command[1]);
                        }
                        
                    }else if(command[0].equals("exit")){
                        nimsys.saveFile(players);
                        
                        System.out.println("");
                        keyboard.close();
                        System.exit(0);
                    }
                }
            }catch(Exception exception){
                if(exception.getClass() == NullPointerException.class){
                    System.out.println("Incorrect number of arguments supplied to command.");
                }else{
                    System.out.println(exception.getMessage());
                }
            }
        }
    }
    
    public void startgame(NimGame nimGame, ArrayList<NimPlayer> players, String[] gameInfo, 
                                                                        Scanner keyboard){
        /*Variables
            player1Check
            player2CheckCheck: whether or not two players who want to play game are exist.
        */
        
        int player1Check, player2Check;
        
        //Check whether or not two players exist.
        player1Check = playerCheck(players, gameInfo[2]);
        player2Check = playerCheck(players, gameInfo[3]);

        if(player1Check == PLAYER_NOT_FOUND || player2Check == PLAYER_NOT_FOUND){
            System.out.println("One of the players does not exist.");
        }else{
            nimGame = new NimGame();
            nimGame.game(Integer.parseInt(gameInfo[0]), Integer.parseInt(gameInfo[1]), 
                         players.get(player1Check), players.get(player2Check), keyboard);
        }
    }
    
    public void addplayer(ArrayList players, String playerType, String[] nameInfo, int playerFound){
        if(playerFound == PLAYER_NOT_FOUND){
            /*If playerindex is -1, it means player not exists. 
              Therefore, player can be added.
            */
            if(playerType.equals("addplayer")){
                //Add human player.
                players.add(new NimHumanPlayer(nameInfo[0], nameInfo[1], nameInfo[2], 0, 0));
            }else{
                //add AI player.
                players.add(new NimAIPlayer(nameInfo[0], nameInfo[1], nameInfo[2], 0, 0));
            }
        }else{
            /*If playerindex is not -1, it means player already exists. 
            Therefore, don't add it.*/
            System.out.println("The player already exists.");
        }
    }
    
    public void removeplayer(ArrayList<NimPlayer> players, int commandLength, int playerindex, 
                                                                             Scanner keyboard){
        /*If the length of command is 1, remove all players. 
          If not, remove the specific data.
        */
        if(commandLength == 1){
            System.out.println("Are you sure you want to remove all players? (y/n)");

            if(keyboard.next().equalsIgnoreCase("y")){
                //To catch the \n after keyboard.next().
                keyboard.nextLine();

                players.clear();
            }
        }else{      
            players.remove(playerindex);
        }
    }
    
    public void editplayer(NimPlayer player, String[] nameInfo){
        //Edit a player's new information.
        player.setFamilyName(nameInfo[1]);
        player.setGivenName(nameInfo[2]);
    }
    
    public void resetstats(ArrayList<NimPlayer> players, int commandLength, int playerindex, 
                                                                         Scanner keyboard){
        /*If the length of command is 1, reset all players' statistics. 
          If not, reset the specific player's statistics.
        */
        if(commandLength == 1){
            System.out.println("Are you sure you want to reset all player statistics? (y/n)");

            if(keyboard.next().equalsIgnoreCase("y")){
                //To catch the \n after keyboard.next().
                keyboard.nextLine();

                for(NimPlayer player : players){
                    player.reset();
                }
            }
        }else{
            players.get(playerindex).reset(); 
        }
    }
    
    public void displayplayer(ArrayList<NimPlayer> players, int commandLength, int playerindex){
        /*If the length of command is 1, display all players' information. 
          If not, display the specific player's information.
        */
        sortName(players);

        if(commandLength == 1){
            for(int i = players.size()-1 ; i >= 0; i--){
                System.out.println(players.get(i).toString());
            }
        }else{
            System.out.println(players.get(playerindex).toString());
        }
    }
    
    public void rankings(ArrayList<NimPlayer> players, int commandLength, String displayWay){
        /*Use sort function to sort players.
          If the length of command is 1 or command[1] is desc, sort in descending 
          If others, sort in ascending.
        */
        sortName(players);
                        
        if(commandLength == 1){
            sortWin(players, "desc");
        }else{
            sortWin(players, displayWay);
        }
                        
        //If players are more than 10, only displayer the top 10 players
        for(int i = 0; i < 10; i++){
            System.out.printf("%-4s | %02d games | %s %s\n", 
                              Math.round((float)players.get(i).getGamesWon() / 
                                    (float)players.get(i).getGamesPlayed()*100)+"%",
                              players.get(i).getGamesPlayed(),
                              players.get(i).getGivenName(),
                              players.get(i).getFamilyName());

            //If players are less than 10, display the same number of players
            if(i == players.size()- 1){
                break;
            }
        }   
    }
    
    public void saveFile(ArrayList<NimPlayer> players) throws IOException{
        //Store players' statistics.
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME));

        for(NimPlayer player : players){
            writer.write(player.getClass() + "," + player.getUserName() + ","
                        + player.getGivenName() + "," + player.getFamilyName()+ ","
                        + player.getGamesPlayed() + "," + player.getGamesWon()+"\n");
        }
        writer.close();
    }
    
    public void loadFile(ArrayList<NimPlayer> players) throws Exception{
        //Load players' statistics.
        BufferedReader reader = new BufferedReader(new FileReader(FILENAME));
        String input;
        
        while((input = reader.readLine()) != null) {
            String[] playerData = input.split(",");
            
            if (playerData[0].equals(NimHumanPlayer.class.toString())) {
                players.add(new NimHumanPlayer(playerData[1], playerData[3], playerData[2], 
                            Integer.parseInt(playerData[4]), Integer.parseInt(playerData[5])));
            }else{
                players.add(new NimAIPlayer(playerData[1], playerData[3], playerData[2], 
                            Integer.parseInt(playerData[4]), Integer.parseInt(playerData[5])));
            }
            
        }
        
        reader.close();
    }
    
    /*playerCheck function is to check whether or not is the player exist. 
      If find it, return index.
      if not, return -1 means does not find the input player.
    */
    public int playerCheck(ArrayList<NimPlayer> players, String name){
        int index = 0;
        
        for (NimPlayer player : players){
            if (name.equals(player.getUserName())) {
                return index;
            }
            index++;
        } 
        return PLAYER_NOT_FOUND;
    }
    
    //Sort players alphabetically
    public void sortName(ArrayList<NimPlayer> array){      
        Comparator sortName = new Comparator<NimPlayer>(){
            @Override
            public int compare(NimPlayer player1,  NimPlayer player2) { 
                if(player1.getUserName().compareTo(player2.getUserName()) < 0){
                    return 1;
                }else{
                    return -1;
                }
            } 
        };
        
        Collections.sort(array, sortName);      
    }
    
    //Sort players by win percentage  
    public void sortWin(ArrayList<NimPlayer> array, String way){       
        Comparator sortWin = new Comparator<NimPlayer>(){
            @Override
            public int compare(NimPlayer player1,  NimPlayer player2) { 
                if(way.equals("desc")){
                    if (((float) player1.getGamesWon() / (float)player1.getGamesPlayed()* 100)  
                        < ((float) player2.getGamesWon() / (float)player2.getGamesPlayed()* 100)) 
                        return 1; 
                    else
                        return -1; 
                }else{
                    if (((float) player1.getGamesWon() / (float)player1.getGamesPlayed()* 100)  
                        > ((float) player2.getGamesWon() / (float)player2.getGamesPlayed()* 100)) 
                        return 1; 
                    else
                        return -1; 
                } 
            } 
        };
        
        Collections.sort(array, sortWin);
    }  
}