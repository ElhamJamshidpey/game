package game.com.bol.strategy;

import game.com.bol.component.Board;
import game.com.bol.component.Player;

public interface GameStrategy {

	void play(Board board);
	
    boolean movingValidation(Board board, String src, String des);

    void move(Board board, String src, String des);

	boolean gameIsFinish(Board board);
	
	Player findWinner(Board board);
}
