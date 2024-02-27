package com.github.achaaab.tictactoe.view.swing;

import com.github.achaaab.tictactoe.view.SquareView;
import com.github.achaaab.tictactoe.view.TicTacToeView;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.List;

import static java.util.stream.Stream.generate;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Basic swing view for a Tic-tac-toe game.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class TicTacToeViewSwing extends JPanel implements TicTacToeView {

	private final List<SquareViewSwing> squares;

	/**
	 * Creates a view for a Tic-tac-toe game.
	 *
	 * @since 0.0.0
	 */
	public TicTacToeViewSwing() {

		setLayout(new GridLayout(3, 3));

		squares = generate(SquareViewSwing::new).limit(9).toList();
		squares.forEach(this::add);
	}

	@Override
	public SquareView getSquare(int index) {
		return squares.get(index);
	}

	@Override
	public void update() {
		squares.forEach(SquareViewSwing::update);
	}

	@Override
	public void showWin() {
		showMessage("You won!");
	}

	@Override
	public void showLoss() {
		showMessage("You lost...");
	}

	@Override
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
