package com.github.elhamjamshidpey.africanBoardGame.strategy;

import com.github.elhamjamshidpey.africanBoardGame.component.Player;
import com.github.elhamjamshidpey.africanBoardGame.exception.InvalidMoveException;

public interface GameStrategy {

//	void play(Board board);
	void play(String src, String des) throws InvalidMoveException;
    boolean movingValidation(String src, String des) throws InvalidMoveException;
	boolean gameIsFinish();
	Player findWinner();
}
