package com.github.achaaab.tictactoe.view.terminal;

import com.github.achaaab.tictactoe.view.SquareView;
import com.github.achaaab.tictactoe.view.TicTacToeView;

import java.awt.Color;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.Scanner;

import static com.github.achaaab.tictactoe.model.TicTacToe.CIRCLE;
import static java.awt.Color.LIGHT_GRAY;
import static java.lang.String.format;
import static java.util.stream.Stream.generate;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class TicTacToeTerminal implements TicTacToeView {

	private static final String CLEAR_LINE_COMMAND = "[2K";
	private static final String CLEAR_SCREEN_COMMAND = "[2J";
	private static final String COLOR_24_COMMAND = "[38;2;%s;%s;%sm";
	private static final String COLOR_OFF_COMMAND = "[0m";
	private static final String MOVE_CURSOR_COMMAND = "[%d;%df";
	private static final String CURSOR_UP_COMMAND = "[A";

	private static final String GRID = """
			╭0────┬1────┬2────╮
			│  _  │  _  │  _  │
			├3────┼4────┼5────┤
			│  _  │  _  │  _  │
			├6────┼7────┼8────┤
			│  _  │  _  │  _  │
			╰─────┴─────┴─────╯
			""";

	private static final Color GRID_COLOR = LIGHT_GRAY;

	/**
	 * Number of terminal rows for a square.
	 *
	 * @since 0.0.0
	 */
	private static final int SQUARE_HEIGHT = 2;

	private final List<SquareTerminal> squares;
	private final PrintStream terminal;
	private final Scanner scanner;

	/**
	 * Creates a new TicTacToe view in the standard output terminal.
	 *
	 * @since 0.0.0
	 */
	public TicTacToeTerminal() {

		squares = generate(() -> new SquareTerminal(this)).limit(9).toList();
		terminal = System.out;
		scanner = new Scanner(System.in);
	}

	@Override
	public void waitUserMove() {

		int index;
		boolean validAnswer;

		do {

			terminal.print("Your move? (9 for exit) > ");
			index = scanner.nextInt();
			validAnswer = index >= 0 && index <= 8 && getSquare(index).isEmpty() || index == 9;

			if (!validAnswer) {

				terminal.print(CURSOR_UP_COMMAND);
				terminal.print(CLEAR_LINE_COMMAND);
				terminal.print("\r");
			}

		} while (!validAnswer);

		if (index != 9) {
			getSquare(index).play();
		}
	}

	/**
	 * Builds an ANSI escape code to change the text color to a given 24-bit color.
	 *
	 * @param color wanted text color
	 * @return built ANSI escape code
	 * @since 0.0.0
	 */
	private String getColor24Command(Color color) {

		var red = color.getRed();
		var green = color.getGreen();
		var blue = color.getBlue();

		return format(COLOR_24_COMMAND, red, green, blue);
	}

	/**
	 * Get a text surrounded by ANSI escape codes for color settings.
	 *
	 * @param text text display in a given color
	 * @param textColor wanted text color
	 * @param baseColor color to restore after the text
	 * @return text surrounded by ANSI escape codes
	 * @since 0.0.0
	 */
	private String getColoredText(String text, Color textColor, Color baseColor) {

		var textColorCommand = getColor24Command(textColor);

		var baseColorCommand = baseColor == null ?
				COLOR_OFF_COMMAND :
				getColor24Command(baseColor);

		return textColorCommand + text + baseColorCommand;
	}

	/**
	 * Moves the cursor to the specified row and column.
	 * Does not work in all terminals, thus we don't rely too much on it for a decent display.
	 *
	 * @param row row in which to move the cursor
	 * @param column column in which to move the cursor
	 * @since 0.0.0
	 */
	private void moveCursor(int row, int column) {
		terminal.printf(MOVE_CURSOR_COMMAND, row, column);
	}

	/**
	 * Completely clears the terminal.
	 * Does not work in all terminals, thus we don't rely too much on it for a decent display.
	 *
	 * @since 0.0.0
	 */
	private void clearScreen() {
		terminal.print(CLEAR_SCREEN_COMMAND);
	}

	/**
	 * Gets the symbol in the specified square with ANSI escape codes for color settings.
	 *
	 * @param index square index
	 * @return symbol in the specified square, surrounded by ANSI escape codes for color settings
	 * @since 0.0.0
	 */
	private String getSymbol(int index) {

		var square = getSquare(index);

		if (square.isEmpty()) {

			return " ";

		} else {

			var symbol = getSquare(index).getSymbol();
			var color = symbol == CIRCLE ? CIRCLE_COLOR : CROSS_COLOR;
			return getColoredText(Character.toString(symbol), color, GRID_COLOR);
		}
	}

	@Override
	public void update() {

		var grid = GRID;

		for (var squareIndex = 0; squareIndex < 9; squareIndex++) {

			var symbol = getSymbol(squareIndex);
			grid = grid.replaceFirst("_", symbol);
		}

		clearScreen();
		moveCursor(0, 0);
		terminal.print(getColoredText(grid, GRID_COLOR, null));
		moveCursor(1 + 3 * SQUARE_HEIGHT + 1, 1);
	}

	@Override
	public void showWin() {
		confirm("You won!");
	}

	@Override
	public void showLoss() {
		confirm("You lost...");
	}

	@Override
	public void showDraw() {
		confirm("It's a draw.");
	}

	@Override
	public SquareView getSquare(int index) {
		return squares.get(index);
	}

	/**
	 * Asks the user to confirm a message.
	 *
	 * @param message message to confirm
	 * @since 0.0.0
	 */
	private void confirm(String message) {

		terminal.print(message);
		terminal.print("\nPress any key to continue...");

		try {
			System.in.read();
		} catch (IOException cause) {
			throw new UncheckedIOException(cause);
		}
	}
}
