package game.com.bol.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import game.com.bol.Game;
import game.com.bol.component.Board;
import game.com.bol.component.Pit;
import game.com.bol.component.Player;
import game.com.bol.exception.InvalidMoveException;

@Component
public class BolDotComStrategy implements GameStrategy {

	@Autowired
	private Game game;

	public void play(String src, String des) throws InvalidMoveException {

		Board board = game.getBoard();
		movingValidation(src, des);

		// play and land the stones into source pit to destination pit(in each pit one stone)
		
		game.reloadBoard(board);

		// switch player
		if (!playerHasAnotherTurn()) {
			if (game.getCurrentPlayer().equals(game.getFirstPlayer()))
				game.setCurrentPlayer(game.getSecondPlayer());
			else
				game.setCurrentPlayer(game.getFirstPlayer());
		}

//		if(gameIsFinish()) 
//			findWinner();
	}

	public boolean movingValidation(String src, String des) throws InvalidMoveException {
		/*
		 * game rules: moving just to right no stone land in opponent's big pit
		 */
		return true;
	}

	private boolean playerHasAnotherTurn() {
		// if last stone land in player big pit return true
		return false;
	}

	public boolean gameIsFinish() {
		Board board = game.getBoard();
		int playerApits = board.getFirstPlayerAPits().stream().map(Pit::getStoneNumber).mapToInt(Integer::intValue)
				.sum();
		int playerBpits = board.getSecondPlayerBPits().stream().map(Pit::getStoneNumber).mapToInt(Integer::intValue)
				.sum();

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
