package com.github.achaaab.tictactoe.decision;

import static java.lang.Math.max;

/**
 * Negamax algorithm without depth limit (only for very simple games).
 *
 * @param <M> move type
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public record NegaMax<M extends Move>(ZeroSumGame<M> game) implements DecisionAlgorithm<M> {

	private static final double WIN_VALUE = 1.0;
	private static final double DRAW_VALUE = 0.0;
	private static final double LOSE_VALUE = -1.0;

	@Override
	public double evaluate(M move) {

		double value;
		game.play(move);

		if (game.isWin()) {

			value = WIN_VALUE;

		} else if (game.isDraw()) {

			value = DRAW_VALUE;

		} else {

			var nextValue = LOSE_VALUE;
			var moves = game.getMoves().iterator();

			while (nextValue != WIN_VALUE && moves.hasNext()) {
				nextValue = max(nextValue, evaluate(moves.next()));
			}

			value = -nextValue;
		}

		game.cancel(move);
		return value;
	}
}
