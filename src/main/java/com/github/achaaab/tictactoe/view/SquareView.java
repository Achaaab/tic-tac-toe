package com.github.achaaab.tictactoe.view;

import com.github.achaaab.tictactoe.controller.SquareController;

/**
 * View for a Tic-tac-toe square.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public interface SquareView {

	/**
	 * Updates this Tic-tac-toe square.
	 *
	 * @since 0.0.0
	 */
	void update();

	/**
	 * Sets the controller for this view.
	 *
	 * @param controller square controller
	 * @since 0.0.0
	 */
	void setController(SquareController controller);
}
