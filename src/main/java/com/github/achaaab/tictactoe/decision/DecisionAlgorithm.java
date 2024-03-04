package com.github.achaaab.tictactoe.decision;

import java.util.Random;
import java.util.TreeMap;

import static java.util.Comparator.comparingDouble;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * Decision algorithm for a zero-sum game.
 *
 * @param <M> move type
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public interface DecisionAlgorithm<M extends Move> {

	Random RANDOM = new Random();

	/**
	 * @return game to play
	 * @since 0.0.0
	 */
	ZeroSumGame<M> game();

	/**
	 * @return single best move or a random one amongst the best moves
	 * @since 0.0.0
	 */
	default M getRandomBestMove() {

		var bestMoves = game().getMoves().
				collect(groupingBy(this::evaluate, TreeMap::new, toList())).
				lastEntry().getValue();

		return bestMoves.get(RANDOM.nextInt(bestMoves.size()));
	}

	/**
	 * @return single best move or an arbitrary one amongst the best moves (deterministic behavior)
	 * @since 0.0.0
	 */
	default M getArbitraryBestMove() {

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
