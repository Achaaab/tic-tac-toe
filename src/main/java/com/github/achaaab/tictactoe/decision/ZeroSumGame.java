package com.github.achaaab.tictactoe.decision;

import java.util.stream.Stream;

/**
 * Zero-sum game.
 *
 * @param <M> type of moves
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public interface ZeroSumGame<M extends Move> {

	/**
	 * @return legal moves
	 * @since 0.0.0
	 */
	Stream<M> getMoves();

	/**
	 * Generally speaking, a game is considered as ended if the last move resulted in a win or a draw.
	 *
	 * @return whether this game is over
	 * @since 0.0.0
	 */
	default boolean isOver() {
		return isWin() || isDraw();
	}

	/**
	 * @return {@code true} if this game is a draw
	 * @since 0.0.0
	 */
	boolean isDraw();

	/**
	 * @return {@code true} if this game is won by the player who played the last move
	 * @since 0.0.0
	 */
	boolean isWin();

	/**
	 * Plays the given move.
	 *
	 * @param move move to play
	 * @since 0.0.0
	 */
	void play(M move);

	/**
	 * Cancels the given move.
	 *
	 * @param move move to cancel
	 * @since 0.0.0
	 */
	void cancel(M move);
}
