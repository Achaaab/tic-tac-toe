package com.github.achaaab.tictactoe.view.terminal;

import com.github.achaaab.tictactoe.view.SquareView;
import com.github.achaaab.tictactoe.view.TicTacToeView;

import java.awt.Color;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.function.Supplier;

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

	/**
	 * Asks a question and keeps prompting for answers until a valid one is provided.
	 *
	 * @param question question to display
	 * @param answerSupplier answer supplier (usually based on scanner)
	 * @param condition condition that answers must verify in order to be accepted
	 * @return valid answer
	 * @param <T> answer type
	 * @since 0.0.0
	 */
	public <T> T ask(String question, Supplier<T> answerSupplier, Predicate<T> condition) {

		T answer;
		boolean validAnswer;

		do {

			terminal.print(question + " ");
			answer = answerSupplier.get();
			validAnswer = condition.test(answer);

			if (!validAnswer) {

				terminal.print(CURSOR_UP_COMMAND);
				terminal.print(CLEAR_LINE_COMMAND);
				terminal.print("\r");
			}

		} while (!validAnswer);

		return answer;
	}

	@Override
	public SquareView getPlayedSquare() {

		var squareIndex = ask(
				"Your move?",
				this::readInteger,
				index -> index >= 0 && index <= 8 && getSquare(index).isEmpty());

		return getSquare(squareIndex);
	}

	/**
	 * Reads an integer from the standard input and consumes the following new line delimiter.
	 *
	 * @return integer read from the standard input
	 * @since 0.0.0
	 */
	private int readInteger() {

		var integer = scanner.nextInt();

		// Consumes the new line delimiter.
		scanner.nextLine();

		return integer;
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

			var symbol = square.getSymbol();
			var color = square.getSymbolColor();
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
	public boolean showWin(char symbol) {
		return confirm(symbol + " won!");
	}

	@Override
	public boolean showDraw() {
		return confirm("It's a draw.");
	}

	@Override
	public SquareView getSquare(int index) {
		return squares.get(index);
	}

	/**
	 * Asks the user to confirm a message.
	 *
	 * @param message message to confirm
	 * @return whether to play another round
	 * @since 0.0.0
	 */
	private boolean confirm(String message) {

		terminal.println(message);

		return "yes".equals(ask(
				"Continue yes / no?",
				scanner::nextLine,
				answer -> answer.equals("yes") || answer.equals("no")));
	}
}
