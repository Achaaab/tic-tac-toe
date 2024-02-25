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

	private SquareView view;

	/**
	 * Creates a controller for the given square.
	 * It initially has no view. A view must be set before this controller is used.
	 *
	 * @param ticTacToe Tic-tac-toe controller
	 * @param model square model
	 * @since 0.0.0
	 */
	public SquareController(TicTacToeController ticTacToe, Square model) {

		this.ticTacToe = ticTacToe;
		this.model = model;
	}

	/**
	 * @return view of this Tic-tac-toe square
	 * @since 0.0.0
	 */
	public SquareView getView() {
		return view;
	}

	/**
	 * @param view view of this Tic-tac-toe square
	 * @since 0.0.0
	 */
	public void setView(SquareView view) {
		this.view = view;
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
