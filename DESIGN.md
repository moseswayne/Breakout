##DESIGN
###Design Goals:
*provide modularity in the design of the program to allow future additions of functionality and features
*allow autonomous action of objects while allowing for the game to dictate interaction
*allow for smooth use of all features regardless of condition

###How to Add New Features:
####Powerup:
To add a powerup to the game, create a new class that extends Powerup and implements the method mainPower(). Use mainPower to alter any parameters within various classes depending on the powerup. In the Breakout.java file, alter the ArrayList to add an additional element containing the new powerup as the new element. Use this index as the reference for this powerup.

####Bricks:
After adding the "masterpiece" component, to add new functions to bricks, extend the abstract class Brick and implement the methods getScore() and isBroken(Ball myBouncer). Afterwards, go to the Breakout.java file to add this new handle case for the brick in the level initialization method.

####Levels:
Add a new text files to the datafiles folder using the naming format BREAKOUT\_LEVEL#.txt with each line of test formatted as (x coordinate) (y coordinate) (hardness of brick) (power up of brick).

###Major Design Choices:
My design largely focused on modularity. In order to fit the modularity of powerups, the main class had to implement an array list of these powerups. The only workaround would have been if statements of a switch statement, both of which would have been poor design choices due to extendability problems and readability problems.
Most collision calculations were done within the Ball class. This was to simplify the location of each of the collisions instead of having to search through each structure for the collision detection algorithm. In regards to the masterpiece, the collision detection was given to the bricks instead of the balls for chaining of actions. This allowed for the classes to do more work for the main function without it having to piece together dependent information. 
In regards to the brick masterpiece, the use of powerups required casting the object because all of the bricks needed to be in the same ArrayList. If this design choice was not chosen, then numerous different data structures would have been needed to fit each of the new bricks into.

###Assumptions and Decisions:
The program assumes that the ordering of the main functions calls will remain consistent, otherwise, dependencies may cause the program to crash.
The lack of a splash screen means that instructions for how to play the game must be read from the REAMME.md file.
The non differentiation of the different powerup icons obscures the function of each of the powerups from the player.
A magic number was incorporated into the determination of the height of the screen in order to add statistics for the game.
The program assumes that modifying functions with the ball will carry over conditional statements that determine action for what the ball does when it has not been launched.