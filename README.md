##BREAKOUT
Author: Moses Wayne
netID: msw38
Date Started: January 14
Date Finished: January 22
Total Time Spent: 40+ Hours
Resources:  a lot of Java Docs, used Professor Duvall's starter code for the project, consulted TAs Kelly Cochran and Matthew Faw on various aspects of the project
File used to start the project: Breakout.java
Data Files needed for Project: found in the datafiles folder and the images folder. Images were provided couresy of Professor Duvall
Known bugs: pressing "M" too many times during the game can cause errors and a frozen ball on the paddle
Extra features: dynamic angle calculation off the paddle, this ensures that every brick can be hit


NOTE: datafiles and images MUST be set to source folders for proper function
Run Breakout.java to initiate the game.

Cheat codes:
M - activates multiball cheat/power up
P - activates extend paddle cheat/power up
S - activates the strongball cheat/power up

A simple game of Breakout using JavaFX. 

There are 5 powerups, but they all use the same icon. Bricks that drop powerups
are yellow. The three different kinds of bricks are single hit, multi hit, and 
ones that drop various powerups. The paddle has multiple abilities as well. 
Powerups can extend the paddle temporarily, make the bouncers stick to the paddle 
for a fixed amount of time, and angles for how the balls bounce are calculated 
based on where on the paddle they hit, allowing for all possible areas of the 
screen to be hit. Levels are read in from a file reader and they can be added into
the game by adding text files of bricks into the datafiles folder.

Note: In its current version, the game does not include a splash screen.