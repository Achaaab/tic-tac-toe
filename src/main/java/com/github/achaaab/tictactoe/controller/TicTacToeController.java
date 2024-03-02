package com.github.achaaab.tictactoe.controller;

import com.github.achaaab.tictactoe.decision.DecisionAlgorithm;
import com.github.achaaab.tictactoe.model.DrawSymbol;
import com.github.achaaab.tictactoe.model.Square;
import com.github.achaaab.tictactoe.model.TicTacToe;
import com.github.achaaab.tictactoe.view.TicTacToeView;

import static java.util.stream.IntStream.range;

/**
 * Tic-tac-toe game controller.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class TicTacToeController {

	private final TicTacToe model;
	private final TicTacToeView view;
	private final DecisionAlgorithm<DrawSymbol> decisionAlgorithm;

	/**
	 * Creates a controller for the given Tic-tac-toe game.
	 *
	 * @param model Tic-tac-toe game model
	 * @param view Tic-tac-toe game view
	 * @param decisionAlgorithm AI decision algorithm
	 * @since 0.0.0
	 */
	public TicTacToeController(TicTacToe model, TicTacToeView view, DecisionAlgorithm<DrawSymbol> decisionAlgorithm) {

		this.model = model;
		this.view = view;

		range(0, 9).forEach(this::createSquareController);

		this.decisionAlgorithm = decisionAlgorithm;
	}

	/**
	 * Creates a controller for the specified square.
	 *
	 * @param index index of the square
	 * @since 0.0.0
	 */
	private void createSquareController(int index) {

		var squareModel = model.getSquare(index);
		var squareView = view.getSquare(index);

		new SquareController(this, squareModel,squareView);
	}

	/**
	 * Waits for the user to make his move.
	 *
	 * @since 0.0.0
	 */
	public void waitUserMove() {
		view.waitUserMove();
	}

	/**
	 * Plays in the given square then plays the AI move if the game is not ended.
	 *
	 * @param square square to play
	 * @since 0.0.0
	 */
	public void play(Square square) {

		model.play(square);

		var win = model.isWin();
		var draw = model.isDraw();

		if (win || draw) {

			view.update();

			if (win) {
				view.showWin();
			} else {
				view.showDraw();
			}

			model.reset();
			view.update();
			view.waitUserMove();

		} else {

			playAi();
		}
	}

	/**
	 * Plays the AI move.
	 *
	 * @since 0.0.0
	 */
	public void playAi() {

		model.play(decisionAlgorithm.getBestMove());
		view.update();

		var win = model.isWin();
		var draw = model.isDraw();

		if (win || draw) {

			if (win) {
				view.showLoss();
			} else {
				view.showDraw();
			}

			model.reset();
			view.update();
		}

		waitUserMove();
	}
}
