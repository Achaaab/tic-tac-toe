package com.github.achaaab.tictactoe;

import com.github.achaaab.tictactoe.controller.TicTacToeController;
import com.github.achaaab.tictactoe.decision.NegaMax;
import com.github.achaaab.tictactoe.model.TicTacToe;
import com.github.achaaab.tictactoe.view.swing.TicTacToeSwing;
import com.github.achaaab.tictactoe.view.terminal.TicTacToeTerminal;

/**
 * Tic-tac-toe desktop application.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class Application {

	/**
	 * Starts the Tic-tac-toe application.
	 *
	 * @param arguments
	 * <ol start=0>
	 *     <li>View type
	 *         <ul>
	 *             <li>0: Swing (default)</li>
	 *             <li>1: Terminal</li>
	 *         </ul>
	 *     </li>
	 * </ol>
	 * @since 0.0.0
	 */
	public static void main(String... arguments) {

		var terminal = arguments.length > 0 && arguments[0].equals("1");

		var model = new TicTacToe();

		var view = terminal ?
				new TicTacToeTerminal() :
				new TicTacToeSwing();

		var decisionAlgorithm = new NegaMax<>(model);
		var controller = new TicTacToeController(model, view, decisionAlgorithm);

		controller.playAi();
	}
}
