package com.github.achaaab.tictactoe.model;

import static com.github.achaaab.tictactoe.model.TicTacToe.EMPTY;

/**
 * Tic-tac-toe square. It contains the symbol {@link TicTacToe#EMPTY} if nobody played in it, then it contains one
 * of the following symbol:
 * <ul>
 *     <li>{@link TicTacToe#CIRCLE}</li>
 *     <li>{@link TicTacToe#CROSS}</li>
 * </ul>
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class Square {

	private final int index;
	private char symbol;

	/**
	 * Creates an initially empty square.
	 *
	 * @param index square index, between 0 and 8 included
	 * @since 0.0.0
	 */
	public Square(int index) {

		this.index = index;

		symbol = EMPTY;
	}

	/**
	 * @return square index, between 0 and 8 included
	 * @since 0.0.0
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @return symbol in this square
	 * @since 0.0.0
	 */
	public char getSymbol() {
		return symbol;
	}

	/**
	 * @param symbol symbol to set in this square
	 * @since 0.0.0
	 */
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}

	/**
	 * Clears this square by setting the symbol {@link TicTacToe#EMPTY}.
	 *
	 * @since 0.0.0
	 */
	public void clear() {
		symbol = EMPTY;
	}

	/**
	 * @return whether this square is empty
	 * @since 0.0.0
	 */
	public boolean isEmpty() {
		return symbol == EMPTY;
	}

	/**
	 * @return whether a player played in this square
	 * @since 0.0.0
	 */
	public boolean isPlayed() {
		return symbol != EMPTY;
	}
}
