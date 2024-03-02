package com.github.achaaab.tictactoe.view.swing;

import com.github.achaaab.tictactoe.controller.SquareController;
import com.github.achaaab.tictactoe.view.SquareView;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;

import static com.github.achaaab.tictactoe.model.TicTacToe.CIRCLE;
import static com.github.achaaab.tictactoe.view.TicTacToeView.CIRCLE_COLOR;
import static com.github.achaaab.tictactoe.view.TicTacToeView.CROSS_COLOR;
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

	private final JButton button;
	private final JLabel label;

	private SquareController controller;

	/**
	 * Creates a view for a square.
	 *
	 * @since 0.0.0
	 */
	public SquareSwing() {

		button = new JButton();
		label = new JLabel();

		label.setFont(label.getFont().deriveFont(scaleFloat(48)));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);
		button.addActionListener(event -> controller.play());
		button.setEnabled(false);

		setPreferredSize(new Dimension(scale(64), scale(64)));
		setLayout(new BorderLayout());
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
			label.setForeground(symbol == CIRCLE ? CIRCLE_COLOR : CROSS_COLOR);

			add(label, CENTER);
		}

		validate();
		repaint();
	}

	/**
	 * Enables the player to move in this square.
	 *
	 * @since 0.0.0
	 */
	public void enableMove() {
		button.setEnabled(true);
	}
}
