package com.github.achaaab.tictactoe.view;

import com.github.achaaab.tictactoe.controller.SquareController;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;

import static com.github.achaaab.tictactoe.model.TicTacToe.CIRCLE;
import static com.github.achaaab.tictactoe.view.ViewUtility.scale;
import static com.github.achaaab.tictactoe.view.ViewUtility.scaleFloat;
import static java.awt.BorderLayout.CENTER;
import static java.awt.Color.BLUE;
import static java.awt.Color.RED;

/**
 * Basic view for a Tic-tac-toe square.
 * Displays either a button if the square is empty, or a symbol if the square is played.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class SquareView extends JPanel {

	private final SquareController square;

	private final JButton button;
	private final JLabel label;

	/**
	 * Creates a view for a square.
	 *
	 * @param square square controller
	 * @since 0.0.0
	 */
	public SquareView(SquareController square) {

		this.square = square;

		button = new JButton();
		label = new JLabel();

		label.setFont(label.getFont().deriveFont(scaleFloat(48)));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);
		button.addActionListener(event -> square.play());

		setPreferredSize(new Dimension(scale(64), scale(64)));
		setLayout(new BorderLayout());

		update();
	}

	/**
	 * @since 0.0.0
	 */
	public void update() {

		var symbol = square.getSymbol();
		var empty = square.isEmpty();

		label.setText(Character.toString(symbol));

		removeAll();

		if (empty) {

			add(button, CENTER);

		} else {

			add(label, CENTER);
			label.setForeground(symbol == CIRCLE ? RED : BLUE);
		}

		validate();
		repaint();
	}
}
