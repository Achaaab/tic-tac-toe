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
	 * @since 0.0.0
	 */
	void showWin();

	/**
	 * Displays a loss message and waits for the user to confirm it.
	 *
	 * @since 0.0.0
	 */
	void showLoss();

	/**
	 * Displays a draw message and waits for the user to confirm it.
	 *
	 * @since 0.0.0
	 */
	void showDraw();

	/**
	 * Gets the view for the square at specified index.
	 *
	 * @param index square index
	 * @return view for the square et specified index
	 * @since 0.0.0
	 */
	SquareView getSquare(int index);

	/**
	 * Waits for the user to make his move.
	 *
	 * @since 0.0.0
	 */
	void waitUserMove();
}
