package com.github.achaaab.tictactoe.view;

import com.github.achaaab.tictactoe.controller.SquareController;

import java.awt.Color;

import static com.github.achaaab.tictactoe.model.TicTacToe.CIRCLE;
import static com.github.achaaab.tictactoe.view.TicTacToeView.CIRCLE_COLOR;
import static com.github.achaaab.tictactoe.view.TicTacToeView.CROSS_COLOR;

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
	 * Only when a symbol is played in this square.
	 *
	 * @return symbol color
	 * @since 0.0.0
	 */
	default Color getSymbolColor() {

		var symbol = getSymbol();
		return symbol == CIRCLE ? CIRCLE_COLOR : CROSS_COLOR;
	}
}
