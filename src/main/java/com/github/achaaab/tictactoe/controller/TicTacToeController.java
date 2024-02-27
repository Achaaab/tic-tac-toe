package com.github.achaaab.tictactoe.controller;

import com.github.achaaab.tictactoe.decision.DecisionAlgorithm;
import com.github.achaaab.tictactoe.model.DrawSymbol;
import com.github.achaaab.tictactoe.model.Square;
import com.github.achaaab.tictactoe.model.TicTacToe;
import com.github.achaaab.tictactoe.view.TicTacToeView;

import java.util.List;

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
	private final List<SquareController> squares;
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

		squares = range(0, 9).
				mapToObj(this::createSquareController).
				toList();

		this.decisionAlgorithm = decisionAlgorithm;
	}

	/**
	 * Creates a controller for the specified square.
	 *
	 * @param index index of the square
	 * @return created controller
	 * @since 0.0.0
	 */
	private SquareController createSquareController(int index) {

		var squareModel = model.getSquare(index);
		var squareView = view.getSquare(index);

		return new SquareController(this, squareModel,squareView);
	}

	/**
	 * Plays in the given square then plays the AI move if the game is not ended.
	 *
	 * @param square square to play
	 * @since 0.0.0
	 */
	public void play(Square square) {

		model.play(square);
		updateSquareView(square);

		if (model.isWin()) {

			view.showWin();
			model.reset();
			view.update();

		} else if (model.isDraw()) {

			view.showDraw();
			model.reset();
			view.update();

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

		var bestMove = decisionAlgorithm.getBestMove();
		model.play(bestMove);
		updateSquareView(bestMove.square());

		if (model.isWin()) {

			view.showLoss();
			model.reset();
			view.update();

		} else if (model.isDraw()) {

			view.showDraw();
			model.reset();
			view.update();
		}
	}

	/**
	 * Updates the view for the given square.
	 *
	 * @param square square whose view must be updated
	 * @since 0.0.0
	 */
	private void updateSquareView(Square square) {

		var squareIndex = square.getIndex();
		var squareController = squares.get(squareIndex);
		squareController.updateView();
	}
}
