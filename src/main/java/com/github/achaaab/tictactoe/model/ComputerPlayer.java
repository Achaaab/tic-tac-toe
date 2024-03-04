package com.github.achaaab.tictactoe.model;

import com.github.achaaab.tictactoe.decision.AiPlayer;
import com.github.achaaab.tictactoe.decision.NegaMax;

/**
 * Computer Tic-tac-toe player.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class ComputerPlayer extends AiPlayer<DrawSymbol> {

	/**
	 * Creates a new computer player.
	 *
	 * @param ticTacToe Tic-tac-toe game
	 * @since 0.0.0
	 */
	public ComputerPlayer(TicTacToe ticTacToe) {
		super(new NegaMax<>(ticTacToe));
	}
}
