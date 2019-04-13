# Blockade-Snake-Game
An implementation of the blockade snake game

The Blockade game is one of the oldest arcade games . But you know
surely more the Snake game derived from Blockade and widely declined on
cellphone.

The classic version of the game Blockade is to grow one or more snakes
in an arena (game grid), so that snakes become obstacles.
When the game starts, the snake is of minimal size and moves in a straight line. The player can turn it right or left, but can not stop it: if it does not
do not turn, the snake continues to move forward. The snake increases periodically in size, whether turning or continuing in a straight line. If the snake comes out from the arena or collides with another snake or even with his own body, the game stops.

## Basic game for the project
General principles
The rules here are those of Blockade:
1. There are two snakes.
2. There is no apple, the size of each snake increases automatically.
3. There is no score, the winner is the surviving snake.

The arena of your project is a square grid composed of N × NN × N squares and occupied
by two serpents, of which one can differentiate the head and the tail. The movement of snakes is calculated periodically. Initially, a snake starts from the center of the upper quarter-left of the screen, and the other snake by the opposite position (center of the lower-right quarter)
 With each cycle of calculation, each snake grows.

## Types of movements realized

Each snake moves in one of the following modes for the duration of the
game.
1. Using the indication of a player, with the keys of direction of the keyboard.
2. Or independently, following a random movement or following an intelligent movement.
These modes of movements are detailed at the following sections.
