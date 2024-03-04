package com.github.achaaab.tictactoe.decision;

/**
 * A player.
 *
 * @param <M> move that the player does
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public interface Player<M extends Move> {

	/**
	 * @return next move of this player
	 * @since 0.0.0
	 */
	M getMove();
}
