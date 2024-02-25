package com.github.achaaab.tictactoe.decision;

import static java.util.Comparator.comparingDouble;

/**
 * Decision algorithm for a zero-sum game.
 *
 * @param <M> move type
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public interface DecisionAlgorithm<M extends Move> {

	/**
	 * @return game to play
	 * @since 0.0.0
	 */
	ZeroSumGame<M> game();

	/**
	 * @return best move or one of the best if multiple moves have the same value
	 * @since 0.0.0
	 */
	default M getBestMove() {

		return game().getMoves().
				max(comparingDouble(this::evaluate)).
				orElseThrow();
	}

	/**
	 * Evaluates a move.
	 *
	 * @param move move to evaluate
	 * @return move value
	 * @since 0.0.0
	 */
	double evaluate(M move);
}
