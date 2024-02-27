package com.github.achaaab.tictactoe;

import com.github.achaaab.tictactoe.controller.TicTacToeController;
import com.github.achaaab.tictactoe.decision.NegaMax;
import com.github.achaaab.tictactoe.model.TicTacToe;
import com.github.achaaab.tictactoe.view.swing.TicTacToeViewSwing;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.Image;
import java.io.IOException;
import java.io.UncheckedIOException;

import static java.util.Objects.requireNonNull;
import static javax.swing.SwingUtilities.invokeLater;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

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
	 * @param arguments none
	 * @since 0.0.0
	 */
	public static void main(String... arguments) {

		invokeLater(() -> {

			var model = new TicTacToe();
			var decisionAlgorithm = new NegaMax<>(model);
			var view = new TicTacToeViewSwing();
			var controller = new TicTacToeController(model, view, decisionAlgorithm);

			controller.playAi();

			var frame = new JFrame("Tic-tac-toe");
			frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
			frame.setIconImage(getIcon());
			frame.setContentPane(view);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		});
	}

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
}
