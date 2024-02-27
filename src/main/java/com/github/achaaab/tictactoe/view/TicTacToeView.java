package com.github.achaaab.tictactoe.view;

/**
 * View for a Tic-tac-toe game.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public interface TicTacToeView {

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
}
