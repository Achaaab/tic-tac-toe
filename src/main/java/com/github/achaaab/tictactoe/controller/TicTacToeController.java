package com.github.achaaab.tictactoe.controller;

import com.github.achaaab.tictactoe.decision.DecisionAlgorithm;
import com.github.achaaab.tictactoe.decision.NegaMax;
import com.github.achaaab.tictactoe.model.DrawSymbol;
import com.github.achaaab.tictactoe.model.Square;
import com.github.achaaab.tictactoe.model.TicTacToe;
import com.github.achaaab.tictactoe.view.SquareView;
import com.github.achaaab.tictactoe.view.TicTacToeView;

import java.util.List;

/**
 * Tic-tac-toe game controller.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class TicTacToeController {

	private final TicTacToe model;
	private final List<SquareController> squares;
	private final DecisionAlgorithm<DrawSymbol> decisionAlgorithm;

	private TicTacToeView view;

	/**
	 * Creates a controller for the given Tic-tac-toe game.
	 *
	 * @param model Tic-tac-toe game
	 * @since 0.0.0
	 */
	public TicTacToeController(TicTacToe model) {

		this.model = model;

		squares = model.getSquares().stream().
				map(this::createSquare).
				toList();

		decisionAlgorithm = new NegaMax<>(model);
	}

	/**
	 * Sets the view for this Tic-tac-toe game.
	 *
	 * @param view Tic-tac-toe view
	 * @since 0.0.0
	 */
	public void setView(TicTacToeView view) {
		this.view = view;
	}

	/**
	 * @return square controllers
	 * @since 0.0.0
	 */
	public List<SquareController> getSquares() {
		return squares;
	}

	/**
	 * Plays in the given square.
	 *
	 * @param square square to play
	 * @since 0.0.0
	 */
	public void play(Square square) {

		model.play(square);
		view.update();

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
	 * Plays best AI move.
	 *
	 * @since 0.0.0
	 */
	public void playAi() {

		var move = decisionAlgorithm.getBestMove();
		model.play(move);
		view.update();

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
	 * Creates a Tic-tac-toe square controller, with a view set.
	 *
	 * @param squareModel square model
	 * @return square controller
	 * @since 0.0.0
	 */
	private SquareController createSquare(Square squareModel) {

		var square = new SquareController(this, squareModel);
		var squareView = new SquareView(square);
		square.setView(squareView);

		return square;
	}
}
