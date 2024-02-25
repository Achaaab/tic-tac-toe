package com.github.achaaab.tictactoe.view;

import com.github.achaaab.tictactoe.controller.SquareController;
import com.github.achaaab.tictactoe.controller.TicTacToeController;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Basic view for a Tic-tac-toe game.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class TicTacToeView extends JPanel {

	private final List<SquareView> squares;

	/**
	 * Creates a view for a Tic-tac-toe game.
	 *
	 * @param ticTacToe controller of the Tic-tac-toe game to display
	 * @since 0.0.0
	 */
	public TicTacToeView(TicTacToeController ticTacToe) {

		setLayout(new GridLayout(3, 3));

		squares = ticTacToe.getSquares().stream().
				map(SquareController::getView).
				toList();

		squares.forEach(this::add);
	}

	/**
	 * Updates this view.
	 *
	 * @since 0.0.0
	 */
	public void update() {
		squares.forEach(SquareView::update);
	}

	/**
	 * Displays a win message and waits for the user to confirm it.
	 *
	 * @since 0.0.0
	 */
	public void showWin() {
		showMessage("You won!");
	}

	/**
	 * Displays a loss message and waits for the user to confirm it.
	 *
	 * @since 0.0.0
	 */
	public void showLoss() {
		showMessage("You lost...");
	}

	/**
	 * Displays a draw message and waits for the user to confirm it.
	 *
	 * @since 0.0.0
	 */
	public void showDraw() {
		showMessage("It's a draw.");
	}

	/**
	 * Displays a message and waits for the user to confirm it.
	 *
	 * @param message message to display
	 * @since 0.0.0
	 */
	private void showMessage(String message) {
		showMessageDialog(null, message);
	}
}
