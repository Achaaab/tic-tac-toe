package com.github.achaaab.tictactoe.model;

import com.github.achaaab.tictactoe.controller.TicTacToeController;
import com.github.achaaab.tictactoe.decision.Player;

/**
 * Human Tic-tac-toe player.
 *
 * @since 0.0.0
 */
public class HumanPlayer implements Player<DrawSymbol> {

	private final TicTacToeController controller;

	/**
	 * Creates a human player.
	 *
	 * @param controller Tic-tac-toe controller
	 * @since 0.0.0
	 */
	public HumanPlayer(TicTacToeController controller) {
		this.controller = controller;
	}

	@Override
	public DrawSymbol getMove() {
		return controller.getUserMove();
	}
}
