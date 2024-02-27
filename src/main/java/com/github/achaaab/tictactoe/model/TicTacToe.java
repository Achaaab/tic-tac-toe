package com.github.achaaab.tictactoe.model;

import com.github.achaaab.tictactoe.decision.ZeroSumGame;

import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.stream;
import static java.util.stream.IntStream.range;

/**
 * A simple Tic-tac-toe game, with squares numbered as follows:
 * <p>0 1 2</p>
 * <p>3 4 5</p>
 * <p>6 7 8</p>
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class TicTacToe implements ZeroSumGame<DrawSymbol> {

	public static final char CIRCLE = 'O';
	public static final char CROSS = 'X';
	public static final char EMPTY = ' ';

	/**
	 * Gets the other symbol.
	 *
	 * @param symbol any symbol
	 * @return the other symbol
	 * @since 0.0.0
	 */
	private static char other(char symbol) {
		return symbol == CIRCLE ? CROSS : CIRCLE;
	}

	private final List<Square> squares;
	private char currentSymbol;

	/**
	 * Creates a new Tic-tac-toe game.
	 *
	 * @since 0.0.0
	 */
	public TicTacToe() {

		squares = range(0, 9).
				mapToObj(Square::new).
				toList();

		currentSymbol = CROSS;
	}

	/**
	 * Clears the squares but keeps the current symbol.
	 *
	 * @since 0.0.0
	 */
	public void reset() {
		squares.forEach(Square::clear);
	}

	/**
	 * Gets the square at specified index.
	 *
	 * @param index square index
	 * @return square at specified index
	 * @since 0.0.0
	 */
	public Square getSquare(int index) {
		return squares.get(index);
	}

	@Override
	public Stream<DrawSymbol> getMoves() {

		return squares.stream().
				filter(Square::isEmpty).
				map(square -> new DrawSymbol(square, currentSymbol));
	}

	@Override
	public boolean isDraw() {
		return !isWin() && squares.stream().allMatch(Square::isPlayed);
	}

	@Override
	public boolean isWin() {

		var previousSymbol = other(currentSymbol);

		return checkAlignment(previousSymbol, 0, 1, 2) ||
				checkAlignment(previousSymbol, 3, 4, 5) ||
				checkAlignment(previousSymbol, 6, 7, 8) ||
				checkAlignment(previousSymbol, 0, 3, 6) ||
				checkAlignment(previousSymbol, 1, 4, 7) ||
				checkAlignment(previousSymbol, 2, 5, 8) ||
				checkAlignment(previousSymbol, 0, 4, 8) ||
				checkAlignment(previousSymbol, 2, 4, 6);
	}

	/**
	 * Checks the given symbol has been played in all the given squares.
	 *
	 * @param symbol symbol to check
	 * @param squareIndices indices of the square to check
	 * @return {@code true} if the given symbol has been played in all the given squares
	 * @since 0.0.0
	 */
	private boolean checkAlignment(char symbol, int... squareIndices) {

		return stream(squareIndices).
				mapToObj(squares::get).
				allMatch(square -> square.getSymbol() == symbol);
	}

	/**
	 * Plays in a square.
	 *
	 * @param square square in which to play
	 * @since 0.0.0
	 */
	public void play(Square square) {
		play(new DrawSymbol(square, currentSymbol));
	}

	@Override
	public void play(DrawSymbol move) {

		move.square().setSymbol(move.symbol());
		currentSymbol = other(currentSymbol);
	}

	@Override
	public void cancel(DrawSymbol move) {

		move.square().setSymbol(EMPTY);
		currentSymbol = other(currentSymbol);
	}
}
