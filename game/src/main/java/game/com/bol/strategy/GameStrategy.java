package game.com.bol.strategy;

import game.com.bol.component.Player;
import game.com.bol.exception.MovingException;

public interface GameStrategy {

//	void play(Board board);
	void play(String src, String des) throws MovingException;
    boolean movingValidation(String src, String des) throws MovingException;
	boolean gameIsFinish();
	Player findWinner();
}
