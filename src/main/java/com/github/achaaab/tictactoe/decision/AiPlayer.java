package com.github.achaaab.tictactoe.decision;

/**
 * @param <M> move that the AI does
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class AiPlayer<M extends Move> implements Player<M> {

	private final DecisionAlgorithm<M> decisionAlgorithm;

	/**
	 * Creates a new AI player based on the given algorithm.
	 *
	 * @param decisionAlgorithm decision algorithm
	 * @since 0.0.0
	 */
	public AiPlayer(DecisionAlgorithm<M> decisionAlgorithm) {
		this.decisionAlgorithm = decisionAlgorithm;
	}

	@Override
	public M getMove() {
		return decisionAlgorithm.getRandomBestMove();
	}
}
