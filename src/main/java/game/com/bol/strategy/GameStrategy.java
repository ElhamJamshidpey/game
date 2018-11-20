package game.com.bol.strategy;

import game.com.bol.component.Player;
import game.com.bol.exception.InvalidMoveException;

public interface GameStrategy {

//	void play(Board board);
	void play(String src, String des) throws InvalidMoveException;
    boolean movingValidation(String src, String des) throws InvalidMoveException;
	boolean gameIsFinish();
	Player findWinner();
}
