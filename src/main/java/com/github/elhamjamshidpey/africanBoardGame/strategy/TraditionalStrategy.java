package com.github.elhamjamshidpey.africanBoardGame.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.elhamjamshidpey.africanBoardGame.Game;
import com.github.elhamjamshidpey.africanBoardGame.component.Board;
import com.github.elhamjamshidpey.africanBoardGame.component.Pit;
import com.github.elhamjamshidpey.africanBoardGame.component.Player;
import com.github.elhamjamshidpey.africanBoardGame.exception.InvalidMoveException;

@Component
public class TraditionalStrategy implements GameStrategy {

	@Autowired
	private Game game;

	public void play(String src, String des) throws InvalidMoveException {

		Board board = game.getBoard();
		movingValidation(src, des);

		// TODO play and land the stones into source pit to destination pit(in each pit one stone)
		
		game.reloadBoard(board);

		// switch player
		if (!playerHasAnotherTurn()) {
			if (game.getCurrentPlayer().equals(game.getFirstPlayer()))
				game.setCurrentPlayer(game.getSecondPlayer());
			else
				game.setCurrentPlayer(game.getFirstPlayer());
		}

	}

	public boolean movingValidation(String src, String des) throws InvalidMoveException {
		/*
		 * TODO game rules: moving just to right no stone land in opponent's big pit
		 */
		return true;
	}

	private boolean playerHasAnotherTurn() {
		// if last stone land in player big pit return true
		return false;
	}

	public boolean gameIsFinish() {
		Board board = game.getBoard();
		int playerApits = board.getFirstPlayerAPits().stream().map(Pit::getStoneNumber)
				.mapToInt(Integer::intValue).sum();
		int playerBpits = board.getSecondPlayerBPits().stream().map(Pit::getStoneNumber)
				.mapToInt(Integer::intValue).sum();

		if (playerApits > 0 && playerBpits > 0)
			return false;

		board.getFirstPlayerLargerPit().addStone(playerApits);
		board.getFirstPlayerAPits().clear();
		board.getSecondPlayerLargerPit().addStone(playerBpits);
		board.getSecondPlayerBPits().clear();

		return true;
	}

	public Player findWinner() {
		if (game.getBoard().getFirstPlayerLargerPit().getStoneNumber() > game.getBoard()
				.getSecondPlayerLargerPit().getStoneNumber())
			return game.getFirstPlayer();
		return game.getSecondPlayer();

	}

}
