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
