package com.github.achaaab.tictactoe.view;

import java.awt.Color;

import static java.awt.Color.BLUE;
import static java.awt.Color.RED;

/**
 * View for a Tic-tac-toe game.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public interface TicTacToeView {

	Color CIRCLE_COLOR = RED;
	Color CROSS_COLOR = BLUE;

	/**
	 * Updates this view.
	 *
	 * @since 0.0.0
	 */
	void update();

	/**
	 * Displays a win message and waits for the user to confirm it.
	 *
	 * @param symbol winning symbol
	 * @return whether to play another round
	 * @since 0.0.0
	 */
	boolean showWin(char symbol);

	/**
	 * Displays a draw message and waits for the user to confirm it.
	 *
	 * @return whether to play another round
	 * @since 0.0.0
	 */
	boolean showDraw();

	/**
	 * Gets the view for the square at specified index.
	 *
	 * @param index square index
	 * @return view for the square et specified index
	 * @since 0.0.0
	 */
	SquareView getSquare(int index);

	/**
	 * Gets user move.
	 *
	 * @return user move
	 * @since 0.0.0
	 */
	SquareView getPlayedSquare();
}
