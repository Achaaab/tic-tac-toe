package com.github.achaaab.tictactoe.model;

import com.github.achaaab.tictactoe.decision.Move;

/**
 * Tic-tac-toe move : drawing a symbol in a square.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public record DrawSymbol(Square square, char symbol) implements Move {

}
