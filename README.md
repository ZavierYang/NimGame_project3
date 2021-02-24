# NimGame_project3
The aim of this project is to add some more advanced features to the system developed in Project 2. The features to be added are:
* Sort the players with more specific rules
* Handling of invalid input via Exceptions
* Write (read) the game statistics into (from) a file, i.e., one which is stored on the hard disk between executions of Nimsys
* A new type of player - an AI (Artificial Intelligence) player, whose moves are automatically determined by the computer rather than a game user

The system should still operate as specified in Project 2, but with additional functionality, due to the addition of the aforementioned features. Thus, it is advised that you use your Project 2 solution as a starting point for implementing Project 3.

## Knowledge Coverage
* Exceptions
* File I/O operations
* Polymorphism, you may use either inheritance or interface to design the AI player in addition to the human player

## Requirements
1. Sort the players with more specific rules
In this project, the ranking should still be based on the winning ratio first (i.e., the percentage of games won). However, If there is a tie), sort all players by their **username in ONLY ascending alphabetical order.**

2. Invalid input handling via Exceptions
The system should check inputs for validity. For this task, you will not be required to implement exception handling for all possible invalid inputs - just a subset of them. The range of potential invalid inputs you **are required to address by Exceptions (not via if-then statements)** are listed below, along with the required behaviour of your program.
  * Invalid command - The user enters a command which is not a valid Nimsys command. Here, invalid command suggests the input command is not among the specified commands, i.e., addplayer, editplayer, removeplayer, displayplayer resetstats, rankings, startgame, and exit.
  
    Example:
  
        $createplayer lskywalker,Skywalker,Luke
        ‘createplayer’ is not a valid command.
        
  * Invalid number of arguments - The user enters a valid Nimsys command, but does not provide the correct number of arguments. Note: You only need to check for insufficient number of arguments, and simply ignore any extra arguments, i.e., an insufficient argument count will generate an Exception while an excessive count will not. Different commands may have different number of arguments; your program should be able to check invalid number of arguments for **all commands.**

    Example:
      
      $addplayer lskywalker
      Incorrect number of arguments supplied to command.
      
* Invalid move (during a game) - The player tries to remove an invalid number of stones from the game. For the move to be valid, it must be an integer between 1 and N inclusive, where N is the minimum of the upper bound and the number of stones remaining. Any other inputs (e.g. fractions, decimals, non-numeric entries) should be detected as invalid.

    Example (Upper bound is 3 stones here):
      
      7 stones left: * * * * * * *
      XXX’s turn - remove how many?
      4
      
      Invalid move. You must remove between 1 and 3 stones.
      
      7 stones left: * * * * * * *
      XXX’s turn - remove how many?
      
3.  The player statistics file  
- The task is to store these data upon exiting the program, and to restore them on subsequent executions. Thus, if one was to exit your program (using the **exit** command), and then start it again (by running **java Nimsys** at the shell prompt), your program should be restored to the state it was in immediately before exiting. That is, it should be as if the program never existed at all.

- All player information should be stored, i.e., usernames, given / family names, and number of games played / won. Note that you do not need to store information about games in progress, since a game should never be in progress when the program exits properly, i.e., via the **exit** command.

4. The AI player
- A new type of player is to be added - an AI player. This player type should be controlled by the program, not by a human player. Aside from this, an AI player should be the same as a human player. That is, they should have all the same information associated with them (i.e., username, given/family names, and number of games played/won), and they should be stored in the system and appear in player lists/rankings, just as human players are.

- The only difference between a human player and an AI player is in the way that they make a move. Instead of prompting for a move to be entered via standard input, the AI player should choose their own move, based on the state of the game. Thus, the only difference between a human player and an AI player should be in the method used to make a move. This suggests that polymorphism should be applied here. You can add an abstract **NimPlayer class**, which will be used to represent the behaviour/attributes common to both Human and AI players. You can modify your original **NimPlayer class** used in Project 2 to be the new **abstract NimPlayer class**, and the **human player class (NimHumanPlayer)** and **AI player class (NimAIPlayer)** can extend the abstract player class.

- To allow for AI players to be added to the system, you should create a new command - **addaiplayer**. This command should operate in exactly the same way as **addplayer**. Note that all other commands, e.g., ‘removeplayer’ and ‘editplayer’ should work for both human players and AI players. Provided below is an example of the use of the ‘addaiplayer’ command:

      addaiplayer artoo,D2,R2

- Example of when AI player is in the game ($ means the input)

      $startgame 10,3,lskywalker,artoo

      Initial stone count: 10
      Maximum stone removal: 3
      Player 1: Luke Skywalker
      Player 2: R2 D2

      10 stones left: * * * * * * * * * *
      Luke’s turn - remove how many?
      $3

      7 stones left: * * * * * * *
      R2’s turn - remove how many?

      5 stones left: * * * * *
      Luke’s turn - remove how many?
      $3

      2 stones left: * *
      R2’s turn - remove how many?

      1 stones left: *
      Luke’s turn - remove how many?
      $1

      Game Over
      R2 D2 wins!
      $

## Importance
This is a project from a programming course (subject). If you come to watch because of your course (subject) assignment, do not just copy and paste this code or just modify the variables name otherwise your score is possible to be penalised. Moreover, this project is divided into three parts and is completed with the progress of the course (subject). Therefore, different JAVA concepts will not be implemented in the same part and the difficulty will be different.
