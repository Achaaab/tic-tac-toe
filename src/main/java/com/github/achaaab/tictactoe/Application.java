package com.github.achaaab.tictactoe;

import com.github.achaaab.tictactoe.controller.TicTacToeController;
import com.github.achaaab.tictactoe.model.ComputerPlayer;
import com.github.achaaab.tictactoe.model.HumanPlayer;
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
	 * @param arguments <ol start=0>
	 * <li>View type
	 *         <ul>
	 *             <li>s: Swing (default)</li>
	 *             <li>t: Terminal</li>
	 *         </ul>
	 *     </li>
	 *     <li>Mode
	 *         <ul>
	 *             <li>hh: Human vs. human</li>
	 *             <li>cc: Computer vs. computer</li>
	 *             <li>ch: Computer vs. human</li>
	 *         </ul>
	 *     </li>
	 * </ol>
	 * @since 0.0.0
	 */
	public static void main(String... arguments) {

		var viewArgument = arguments[0];
		var modeArgument = arguments[1];

		var view = switch (viewArgument) {

			case "s" -> new TicTacToeSwing();
			case "t" -> new TicTacToeTerminal();
			default -> throw new IllegalArgumentException("unknown view type: " + viewArgument);
		};

		var model = new TicTacToe();

		var controller = new TicTacToeController(model, view);

		switch (modeArgument) {

			case "hh" -> {

				controller.setCrossPlayer(new HumanPlayer(controller));
				controller.setCirclePlayer(new HumanPlayer(controller));
			}

			case "cc" -> {

				controller.setCrossPlayer(new ComputerPlayer(model));
				controller.setCirclePlayer(new ComputerPlayer(model));
			}

			case "ch" -> {

				controller.setCrossPlayer(new HumanPlayer(controller));
				controller.setCirclePlayer(new ComputerPlayer(model));
			}

			default -> throw new IllegalArgumentException("unknown mode: " + modeArgument);
		}

		controller.play();
	}
}
