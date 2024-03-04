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

import static java.lang.Thread.currentThread;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Stream.generate;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;
import static javax.swing.JOptionPane.showConfirmDialog;
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
	private SquareSwing playedSquare;

	/**
	 * Creates a view for a Tic-tac-toe game.
	 *
	 * @since 0.0.0
	 */
	public TicTacToeSwing() {

		squares = generate(() -> new SquareSwing(this)).limit(9).toList();

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
	public synchronized SquareView getPlayedSquare() {

		squares.forEach(SquareSwing::allow);

		playedSquare = null;

		while (playedSquare == null) {

			try {
				wait();
			} catch (InterruptedException interruptedException) {
				currentThread().interrupt();
			}
		}

		squares.forEach(SquareSwing::disallow);

		return playedSquare;
	}

	/**
	 * @param playedSquare square in which the user played
	 * @since 0.0.0
	 */
	public synchronized void setPlayedSquare(SquareSwing playedSquare) {

		this.playedSquare = playedSquare;
		notify();
	}

	@Override
	public void update() {
		squares.forEach(SquareSwing::update);
	}

	@Override
	public boolean showWin(char symbol) {
		return confirm(symbol + " won!");
	}

	@Override
	public boolean showDraw() {
		return confirm("It's a draw.");
	}

	/**
	 * Displays the result and waits for the user decision.
	 *
	 * @param result result to display
	 * @return whether to play another round
	 * @since 0.0.0
	 */
	private boolean confirm(String result) {
		return showConfirmDialog(this, result + "Continue?", "Continue?", YES_NO_OPTION) == YES_OPTION;
	}
}
