package com.github.achaaab.tictactoe.view.terminal;

import com.github.achaaab.tictactoe.controller.SquareController;
import com.github.achaaab.tictactoe.view.SquareView;

/**
 * Terminal view for a Tic-tac-toe square.
 * Actually does not display anything,
 * it just acts as an intermediate between the main Tic-tac-toe view and the square controller.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class SquareTerminal implements SquareView {

	private final TicTacToeTerminal ticTacToe;
	private SquareController controller;

	/**
	 * Creates a terminal view
	 * @param ticTacToe Tic-tac-toe view
	 * @since 0.0.0
	 */
	public SquareTerminal(TicTacToeTerminal ticTacToe) {
		this.ticTacToe = ticTacToe;
	}

	@Override
	public void update() {
		ticTacToe.update();
	}

	@Override
	public SquareController getController() {
		return controller;
	}

	@Override
	public void setController(SquareController controller) {
		this.controller = controller;
	}
}
