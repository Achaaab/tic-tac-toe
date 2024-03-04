package com.github.achaaab.tictactoe.controller;

import com.github.achaaab.tictactoe.model.Square;
import com.github.achaaab.tictactoe.view.SquareView;

/**
 * Tic-tac-toe square controller.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class SquareController {

	private final TicTacToeController ticTacToe;
	private final Square model;

	/**
	 * Creates a controller for the given square.
	 * It initially has no view. A view must be set before this controller is used.
	 *
	 * @param ticTacToe Tic-tac-toe controller
	 * @param model square model
	 * @param view square view
	 * @since 0.0.0
	 */
	public SquareController(TicTacToeController ticTacToe, Square model, SquareView view) {

		this.ticTacToe = ticTacToe;
		this.model = model;

		view.setController(this);
	}

	/**
	 * @return model of this square
	 * @since 0.0.0
	 */
	public Square getModel() {
		return model;
	}

	/**
	 * @return symbol played in this Tic-tac-toe square
	 * @since 0.0.0
	 */
	public char getSymbol() {
		return model.getSymbol();
	}

	/**
	 * @return {@code true} if this Tic-tac-toe square is empty
	 * @since 0.0.0
	 */
	public boolean isEmpty() {
		return model.isEmpty();
	}

	/**
	 * Plays in this square.
	 *
	 * @since 0.0.0
	 */
	public void play() {
		ticTacToe.play(model);
	}
}
