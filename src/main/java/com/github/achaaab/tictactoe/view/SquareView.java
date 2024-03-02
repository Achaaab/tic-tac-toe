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
	 * Sets the controller for this view.
	 *
	 * @param controller square controller
	 * @since 0.0.0
	 */
	void setController(SquareController controller);

	/**
	 * @return the controller for this view
	 * @since 0.0.0
	 */
	SquareController getController();

	/**
	 * Updates this Tic-tac-toe square.
	 *
	 * @since 0.0.0
	 */
	void update();

	/**
	 * @return whether this square is empty
	 * @since 0.0.0
	 */
	default boolean isEmpty() {
		return getController().isEmpty();
	}

	/**
	 * @return symbol played in this Tic-tac-toe square
	 * @since 0.0.0
	 */
	default char getSymbol() {
		return getController().getSymbol();
	}

	/**
	 * Makes the user play in this square.
	 *
	 * @since 0.0.0
	 */
	default void play() {
		getController().play();
	}
}
