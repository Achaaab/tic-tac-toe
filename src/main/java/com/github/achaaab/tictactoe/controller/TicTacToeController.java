package com.github.achaaab.tictactoe.controller;

import com.github.achaaab.tictactoe.decision.Player;
import com.github.achaaab.tictactoe.model.DrawSymbol;
import com.github.achaaab.tictactoe.model.Square;
import com.github.achaaab.tictactoe.model.TicTacToe;
import com.github.achaaab.tictactoe.view.TicTacToeView;

import static com.github.achaaab.tictactoe.model.TicTacToe.CIRCLE;
import static com.github.achaaab.tictactoe.model.TicTacToe.CROSS;
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

	private boolean crossFirst;
	private Player<DrawSymbol> circlePlayer;
	private Player<DrawSymbol> crossPlayer;

	/**
	 * Creates a controller for the given Tic-tac-toe game.
	 *
	 * @param model Tic-tac-toe game model
	 * @param view Tic-tac-toe game view
	 * @since 0.0.0
	 */
	public TicTacToeController(TicTacToe model, TicTacToeView view) {

		this.model = model;
		this.view = view;

		range(0, 9).forEach(this::createSquareController);

		crossFirst = true;
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
	 * Sets the player who plays the circles.
	 *
	 * @param circlePlayer player who plays the circles
	 * @since 0.0.0
	 */
	public void setCirclePlayer(Player<DrawSymbol> circlePlayer) {
		this.circlePlayer = circlePlayer;
	}

	/**
	 * Sets the player who plays the crosses.
	 *
	 * @param crossPlayer player who plays the crosses
	 * @since 0.0.0
	 */
	public void setCrossPlayer(Player<DrawSymbol> crossPlayer) {
		this.crossPlayer = crossPlayer;
	}

	/**
	 * Plays rounds while the user wants to continue.
	 *
	 * @since 0.0.0
	 */
	public void play() {
		while (playRound());
	}

	/**
	 * Starts a new round.
	 *
	 * @return whether to play another round
	 * @since 0.0.0
	 */
	public boolean playRound() {

		model.reset();
		model.setCurrentSymbol(crossFirst ? CROSS : CIRCLE);
		view.update();

		var crossTurn = crossFirst;

		while(!model.isOver()) {

			var player = crossTurn ? crossPlayer : circlePlayer;
			var move = player.getMove();

			model.play(move);
			view.update();

			crossTurn = !crossTurn;
		}

		var anotherRound = model.isWin() ?
				view.showWin(crossTurn ? CIRCLE : CROSS) :
				view.showDraw();

		crossFirst = !crossFirst;
		return anotherRound;
	}

	/**
	 * Gets user move.
	 *
	 * @since 0.0.0
	 */
	public DrawSymbol getUserMove() {

		var squareView = view.getPlayedSquare();
		var squareController = squareView.getController();
		var square = squareController.getModel();

		return new DrawSymbol(square, model.getCurrentSymbol());
	}

	/**
	 * Plays in the given square then plays the AI move if the game is not ended.
	 *
	 * @param square square to play
	 * @since 0.0.0
	 */
	public void play(Square square) {
		model.play(square);
	}
}
