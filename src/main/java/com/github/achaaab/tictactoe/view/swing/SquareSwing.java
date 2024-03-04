package com.github.achaaab.tictactoe.view.swing;

import com.github.achaaab.tictactoe.controller.SquareController;
import com.github.achaaab.tictactoe.view.SquareView;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;

import static com.github.achaaab.tictactoe.view.swing.SwingUtility.scale;
import static com.github.achaaab.tictactoe.view.swing.SwingUtility.scaleFloat;
import static java.awt.BorderLayout.CENTER;
import static java.util.Arrays.stream;

/**
 * Basic swing view for a Tic-tac-toe square.
 * Displays either a button if the square is empty, or a symbol if the square is played.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class SquareSwing extends JPanel implements SquareView {

	private final TicTacToeSwing ticTacToe;

	private final JButton button;
	private final JLabel label;

	private SquareController controller;
	private boolean allowed;

	/**
	 * Creates a view for a square.
	 *
	 * @since 0.0.0
	 */
	public SquareSwing(TicTacToeSwing ticTacToe) {

		this.ticTacToe = ticTacToe;

		button = new JButton();
		label = new JLabel();

		label.setFont(label.getFont().deriveFont(scaleFloat(48)));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);
		button.addActionListener(event -> controller.play());

		setPreferredSize(new Dimension(scale(64), scale(64)));
		setLayout(new BorderLayout());

		// Since we are hot-swapping components, repaints from the operating system can trigger various exceptions.
		setIgnoreRepaint(true);

		allowed = true;
	}

	@Override
	public void setController(SquareController controller) {

		this.controller = controller;

		stream(button.getActionListeners()).
				forEach(button::removeActionListener);

		button.addActionListener(event -> play());

		update();
	}

	@Override
	public SquareController getController() {
		return controller;
	}

	@Override
	public void update() {

		removeAll();

		if (controller.isEmpty()) {

			add(button, CENTER);

		} else {

			var symbol = controller.getSymbol();
			label.setText(Character.toString(symbol));
			label.setForeground(getSymbolColor());

			add(label, CENTER);
		}

		// It seems to be the best practice when adding or removing components: validate() then repaint().
		validate();
		repaint();
	}

	/**
	 * Allows the user to play in this square.
	 *
	 * @since 0.0.0
	 */
	public void allow() {
		allowed = true;
	}

	/**
	 * Prevents the user to play in this square.
	 *
	 * @since 0.0.0
	 */
	public void disallow() {
		allowed = false;
	}

	/**
	 * Plays in this square, if allowed.
	 *
	 * @since 0.0.0
	 */
	private void play() {

		if (allowed) {
			ticTacToe.setPlayedSquare(this);
		}
	}
}
