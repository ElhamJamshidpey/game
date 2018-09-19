package game.com.bol.strategy;

import game.com.bol.component.Board;
import game.com.bol.component.Player;

public class GeneralStrategy implements GameStrategy {

	public boolean checkRules(Board board, Integer src, Integer des) {
		return false;
	}

	public void play(Board board, Integer src, Integer des) {
		
	}

	public boolean gameIsFinish(Board board) {
		return false;
	}

	public Player findWinner(Board board) {
		return null;
	}

	@Override
	public void play(Board board) {
		
	}

}
