package com.github.achaaab.tictactoe.view.swing;

import com.github.achaaab.tictactoe.Application;
import com.github.achaaab.tictactoe.view.SquareView;
import com.github.achaaab.tictactoe.view.TicTacToeView;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Stream.generate;
import static javax.swing.JOptionPane.showMessageDialog;
import static javax.swing.SwingUtilities.invokeLater;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * Basic swing view for a Tic-tac-toe game.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class TicTacToeSwing extends JPanel implements TicTacToeView {

	/**
	 * Loads the Tic-tac-toe icon image from resources.
	 *
	 * @return Tic-tac-toe icon image
	 * @since 0.0.0
	 */
	private static Image getIcon() {

		var classLoader = Application.class.getClassLoader();
		var url = classLoader.getResource("tictactoe.png");
		requireNonNull(url);

		try {
			return ImageIO.read(url);
		} catch (IOException cause) {
			throw new UncheckedIOException(cause);
		}
	}

	private final List<SquareSwing> squares;

	/**
	 * Creates a view for a Tic-tac-toe game.
	 *
	 * @since 0.0.0
	 */
	public TicTacToeSwing() {

		squares = generate(SquareSwing::new).limit(9).toList();

		invokeLater(() -> {

			setLayout(new GridLayout(3, 3));
			squares.forEach(this::add);

			var frame = new JFrame("Tic-tac-toe");
			frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
			frame.setIconImage(getIcon());
			frame.setContentPane(this);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		});
	}

	@Override
	public SquareView getSquare(int index) {
		return squares.get(index);
	}

	@Override
	public void waitUserMove() {
		squares.forEach(SquareSwing::enableMove);
	}

	@Override
	public void update() {
		squares.forEach(SquareSwing::update);
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
