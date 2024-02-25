package com.github.achaaab.tictactoe.decision;

/**
 * Negamax algorithm without depth limit (only for very simple games).
 *
 * @param <M> move type
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public record NegaMax<M extends Move>(ZeroSumGame<M> game) implements DecisionAlgorithm<M> {

	@Override
	public double evaluate(M move) {

		double value;
		game.play(move);

		if (game.isWin()) {

			value = 1;

		} else if (game.isDraw()) {

			value = 0;

		} else {

			value = -game.getMoves().
					map(this::evaluate).
					max(Double::compareTo).
					orElseThrow();
		}

		game.cancel(move);
		return value;
	}
}
