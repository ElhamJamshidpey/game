package game.com.bol.strategy;

import game.com.bol.component.Player;
import game.com.bol.exception.MovingException;

public class GeneralStrategy implements GameStrategy {

	@Override
	public void play(String src, String des) throws MovingException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean movingValidation(String src, String des) throws MovingException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean gameIsFinish() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Player findWinner() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
